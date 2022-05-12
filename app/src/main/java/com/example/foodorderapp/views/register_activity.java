package com.example.foodorderapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodorderapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register_activity extends AppCompatActivity {
    Button btnDangKy;
    EditText txtEmail,txtMK,txtNhapLaiMK;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        addControls();
        addEvents();
    }

    private void addControls() {
        firebaseAuth = FirebaseAuth.getInstance();
        btnDangKy = findViewById(R.id.btnDangKy);
        txtEmail = findViewById(R.id.txtTaoEmail);
        txtMK = findViewById(R.id.txtTaoMK);
        txtNhapLaiMK = findViewById(R.id.txtNhaplaiMK);
    }
    private void addEvents() {
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString();
                String matkhau = txtMK.getText().toString();
                String repeatmatkhau = txtNhapLaiMK.getText().toString();
                if (email.trim().length() == 0)
                    Toast.makeText(register_activity.this, "Chưa nhập email", Toast.LENGTH_SHORT).show();
                else if (matkhau.trim().length() == 0)
                    Toast.makeText(register_activity.this,"Chưa nhập mật khẩu",Toast.LENGTH_SHORT).show();
                else if (!matkhau.equals(repeatmatkhau))
                    Toast.makeText(register_activity.this,"Nhập lại mật khẩu chưa chính xác",Toast.LENGTH_SHORT).show();
                else {
                    firebaseAuth.createUserWithEmailAndPassword(email,matkhau).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                Toast.makeText(register_activity.this,"Đăng ký tài khoản thành công",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(register_activity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}