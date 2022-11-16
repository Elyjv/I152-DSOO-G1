package com.af.commerces;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthActivity extends AppCompatActivity {
    Button signUpButton;
    TextView logText;
    EditText userNameInput, passwordInput, passConfirmInput, emailInput;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        userNameInput = findViewById(R.id.userNameInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        passConfirmInput = findViewById(R.id.passConfirmInput);
        signUpButton = findViewById(R.id.signUpButton);
        logText = findViewById(R.id.logText);



        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarUsuario();
            }
        });

        logText.setOnClickListener((v) -> {
            startActivity(new Intent(AuthActivity.this, loginActivity.class));
        });
        mAuth = FirebaseAuth.getInstance();

    }

    public void verificarUsuario(){
        String userName = userNameInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        String passConfirm = passConfirmInput.getText().toString();
        if(userName.isEmpty() || userName.length() < 5){
            showError(userNameInput, "usuario no valido");
        }else if (email.isEmpty() || !email.contains("@")){
            showError(emailInput,  "email no valido");
        }else if (password.isEmpty() || password.length() < 7){
            showError(passwordInput, "Clave no valida, minimo 7 caracteres");
        }else if (passConfirm.isEmpty() || !passConfirm.equals(password)){
            showError(passConfirmInput, "Las contraseñas no coinciden");
        }else{
            //Registrar usuario
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){

                        // redireccionar a login
                        Intent intent = new Intent(AuthActivity.this, loginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }else{
                        Toast.makeText(getApplicationContext(), "No se pudo registrar el usuario", Toast.LENGTH_LONG ).show();
                    }

                }
            });

        }

    }
    private void showError(EditText input, String s){
        input.setError(s);
        input.requestFocus();
    }


}