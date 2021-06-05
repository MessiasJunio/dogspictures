package br.com.dogs.controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import br.com.dogs.R;

public class Carregamento extends AppCompatActivity {
    private static  final int TEMPO = 2000;
    private boolean isAtivo;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carregamento);
        progressBar = findViewById(R.id.carregamento_proBar);

        final Thread thread = new Thread(){

            @Override
            public void run() {
                isAtivo = true;
                try{
                    int espera = 0;
                    while(isAtivo && (espera < TEMPO)) {
                        sleep(200);
                        if (isAtivo) {
                            espera += 200;
                            atualizaProgresso(espera);

                        }
                    }
                }catch (InterruptedException e){

                }finally{
                    finishAffinity();
                    onContinue();
                }
            }
        };
        thread.start();

    }

    public void atualizaProgresso(int tempoPassado){
        if(progressBar != null){
            final int progress = progressBar.getMax() * tempoPassado / TEMPO;
            progressBar.setProgress(progress);
        }
    }

    public void onContinue(){
        Intent it = new Intent(this, AdministradorActivity.class);
        startActivity(it);
    }


}
