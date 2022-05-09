package com.example.foodorderapp.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.foodorderapp.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class login_activity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,FirebaseAuth.AuthStateListener {
    Button btnDangNhapGoogle;
    GoogleApiClient apiClient;
    FirebaseAuth   firebaseAuth;
    public static int CODE_DANGNHAP_GOOGLE = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnDangNhapGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DangNhapGoogle();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(this);
    }

    private void addControls() {
        btnDangNhapGoogle = findViewById(R.id.btnDangNhapGoogle);
        InitDangNhapGoogle();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void InitDangNhapGoogle(){
        GoogleSignInOptions options = new GoogleSignInOptions.Builder()
                .requestIdToken("560287993906-10r9jsipb89gj1fdd0hqcgnnbvunpun1.apps.googleusercontent.com")
                .requestEmail().build();
        apiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,options).build();
    }
    private void DangNhapGoogle(){
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(apiClient);
        startActivityForResult(intent,CODE_DANGNHAP_GOOGLE);
    }
    private  void ChungThucDangNhapFireBase(String tokenID){
        AuthCredential authCredential = GoogleAuthProvider.getCredential(tokenID,null);
        firebaseAuth.signInWithCredential(authCredential);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_DANGNHAP_GOOGLE && resultCode == RESULT_OK) {
            GoogleSignInResult signInResult = Auth.GoogleSignInApi
                    .getSignInResultFromIntent(data);
            GoogleSignInAccount account = signInResult.getSignInAccount();
            String tokenID = account.getIdToken();
            Log.d("token", "onActivityResult: " + tokenID);
            ChungThucDangNhapFireBase(tokenID);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            Toast.makeText(this,"success",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"failed",Toast.LENGTH_LONG).show();
        }
    }
}