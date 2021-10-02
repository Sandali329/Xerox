package com.example.xerox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class adminlogin extends AppCompatActivity {

private EditText adminUN,adminPW;
private Button adminlogin;
private FirebaseAuth Adminauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);

        adminUN=findViewById(R.id.etadmin_UN);
        adminPW=findViewById(R.id.etadmin_PW);
        adminlogin=findViewById(R.id.btnadmin_login);
        Adminauth=FirebaseAuth.getInstance();

        adminlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String adminUname=adminUN.getText().toString();
                String adminPword=adminPW.getText().toString();
                if(TextUtils.isEmpty(adminUname) && TextUtils.isEmpty(adminPword)){
                    Toast.makeText(adminlogin.this, "Please enter your credentials", Toast.LENGTH_SHORT).show();
                    return;

                }else{


                    Adminauth.signInWithEmailAndPassword(adminUname,adminPword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull  Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(adminlogin.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(adminlogin.this,Adminpage.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(adminlogin.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}