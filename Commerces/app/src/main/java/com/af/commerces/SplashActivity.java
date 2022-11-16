package com.af.commerces;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.os.Bundle;

import java.util.Timer:
import java.util.TimerTask;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, AuthActivity.class):
                startActivity(intent);
                finish();
            }
        };
        Timer tiempo = new Timer();
        tiempo.schedule(tarea, 2000);

    }
}