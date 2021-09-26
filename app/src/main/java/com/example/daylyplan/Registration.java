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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {
    //Объявляем наши переменные из layout'ов
    private TextView email_reg,password_reg,username;
    private DatabaseReference mDataBase;
    private FirebaseAuth mAuth;
    private String USER_KEY = "User";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_layout);
        init();
    }
    //Класс инициализации(Сделан для красоты, можно было и в onCreate все записать)
    private void init(){
    email_reg = findViewById(R.id.email_reg);
    password_reg =findViewById(R.id.fs_password);
    username = findViewById(R.id.username_reg);
    //ОЧЕНЬ ВАЖНО ВСТАВИТЬ ССЫЛКУ НА БАЗУ В getInstance ИНАЧЕ БУДЕТ РУГАТЬСЯ НА РЕГИОН
    mDataBase = FirebaseDatabase.getInstance("https://raccoons-diary-default-rtdb.europe-west1.firebasedatabase.app").getReference(USER_KEY);
    mAuth = FirebaseAuth.getInstance();
    }
    //Кнопка "Регистрация"
    public void reg_reg(View view){
        //Считываем данные из полей для воода
        String id = mDataBase.getKey();
        String userName = username.getText().toString();
        String email = email_reg.getText().toString();
        String password = password_reg.getText().toString();

        //Передаем данные в класс User(записываем в бд)
        User newUser = new User(id,email,password,userName);
        //Проверка на пустые поля
        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()){
                    mDataBase.push().setValue(newUser);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Произошла обшибка заполнения, вероятно такой аккаунт существует!",Toast.LENGTH_SHORT).show();
                }
                }
            });
            //Переход обратно на форму авторизации
            Intent intent = new Intent(this,Login.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this,"Одно из полей пустое! Пожайлуста заполните все поля!",Toast.LENGTH_SHORT).show();
        }
    }
    //Кнопка возвращения на страницу авторизации
    public void return_back(View view){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }
}