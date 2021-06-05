package br.com.dogs.controller;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import br.com.dogs.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

    Thread time = new Thread() {
        @Override
        public void run() {
            try {
                //pausa a splashscreen em 3 segundos
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //se der tudo certo, apos os 3 segundos ele inicia a MainActivity
                Intent it = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(it);
            }
        }
    };
        time.start();
 }

    @Override
    protected void onPause() {
        super.onPause();
        finishAffinity();
    }
}
