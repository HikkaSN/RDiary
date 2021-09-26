package com.example.daylyplan;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private TextView login,password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser cUser = mAuth.getCurrentUser();

        if (cUser !=null){
            Toast.makeText(this,"Вы зарегестрированны",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Вы не зарегестрированны",Toast.LENGTH_SHORT).show();
        }
    }

    private void init(){
        login = findViewById(R.id.email_reg);
        password = findViewById(R.id.password_login);
        mAuth = FirebaseAuth.getInstance();
    }

    public void login(View view){
        if (!TextUtils.isEmpty(login.getText().toString()) && !TextUtils.isEmpty(password.getText().toString())) {
            mAuth.signInWithEmailAndPassword(login.getText().toString(), password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Вы пошли",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this,MainActivity.class); //Переход на мейн форму
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Неправильно введен логин или пароль",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        else{
            Toast.makeText(this,"Одно из полей пустое! Пожайлуста заполните все поля!",Toast.LENGTH_SHORT).show();
        }
    }

    public void reg_log(View view){
        Intent intent = new Intent(this,Registration.class);
        startActivity(intent);
    }

}
