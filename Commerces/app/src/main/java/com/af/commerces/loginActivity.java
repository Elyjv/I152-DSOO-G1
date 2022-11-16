package com.af.commerces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {
    Button loginButton;
    EditText emailInput2;
    EditText passwordInput2;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailInput2 = findViewById(R.id.emailInput2);
        passwordInput2 = findViewById(R.id.passwordInput2);
        loginButton = findViewById(R.id.loginButton);
    }
    });

        loginButton.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick (View view){
                verificarCredenciales();
          }
        });


public void verificarCredenciales(){
    String email = emailInput2.getText().toString();
    String password = emailInput2.getText().toString();
        if(email.isEmpty() || !email.contains("@")){
            showError(emailInput2, "Email no valido");
        }else if(password.isEmpty()|| password.length()<7){
            showError(password, "Password invalida");
        }else{
       //redireccionar a login
        Intent intent = new Intent(loginActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        }

}
private void showError(EditText input, String s){
                input.setError(s);
                input.requestFocus();
            }
}