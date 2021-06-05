package br.com.dogs.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import br.com.dogs.R;
import br.com.dogs.utils.SlidePersonalizado;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin, btnSair, btnBulldog, btnGolden, btnTerrier, btnPitbull, btnDoberman, btnPoodle;
    private SlidePersonalizado slidePersonalizado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapearComponente();
        this.slidePersonalizado = new SlidePersonalizado(this);
    }


    private void mapearComponente() {
        this.btnLogin = findViewById(R.id.main_btnLogin);
        this.btnSair = findViewById(R.id.main_btnSair);
        this.btnBulldog = findViewById(R.id.mainactivity_btnBulldog);
        this.btnGolden= findViewById(R.id.mainactivity_btnGolden);
        this.btnTerrier = findViewById(R.id.mainactivity_btnTerrier);
        this.btnPitbull = findViewById(R.id.mainactivity_btnPitBull);
        this.btnDoberman = findViewById(R.id.mainactivity_btnDoberman);
        this.btnPoodle = findViewById(R.id.mainactivity_btnPoodle);
    }

    public void loginClique(View view){
        if(view == this.btnLogin) {
            Intent it = new Intent(this, LoginActivity.class);
            startActivity(it);
        }
    }

    public void sairClique(View view){
            MainActivity.this.finish();
    }

    public void cliqueRaca(View view){
        SlideShow slide = new SlideShow();
        Intent it;
        if(view == btnBulldog){
            slide.escolhaRaca("bulldog");
            it = new Intent(this, slide.getClass());
            it.putExtra("slide", slide.getRaca());
            startActivity(it);
        }else if(view == btnGolden){
            slide.escolhaRaca("golden");
            it = new Intent(this, slide.getClass());
            it.putExtra("slide", slide.getRaca());
            startActivity(it);
        }else if(view == btnTerrier){
            slide.escolhaRaca("terrier");
            it = new Intent(this, slide.getClass());
            it.putExtra("slide", slide.getRaca());
            startActivity(it);
        }else if(view == btnPitbull){
            slide.escolhaRaca("pitbull");
            it = new Intent(this, slide.getClass());
            it.putExtra("slide", slide.getRaca());
            startActivity(it);
        }else if(view == btnDoberman){
            slide.escolhaRaca("doberman");
            it = new Intent(this, slide.getClass());
            it.putExtra("slide", slide.getRaca());
            startActivity(it);
        }else if(view == btnPoodle){
            slide.escolhaRaca("poodle");
            it = new Intent(this, slide.getClass());
            it.putExtra("slide", slide.getRaca());
            startActivity(it);
        }


    }
}
