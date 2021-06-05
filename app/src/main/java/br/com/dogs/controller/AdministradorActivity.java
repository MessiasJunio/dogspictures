package br.com.dogs.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import br.com.dogs.R;

public class AdministradorActivity extends AppCompatActivity {
    private Button btnPessoa, btnPet, btnHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);
        mapearComponente();

    }
    public void cliqueAdmin(View view){
        if(view == this.btnPessoa){
            Intent i = new Intent(this, PessoaListaActivity.class);
            startActivity(i);

        }else if(view == this.btnPet){
            Intent i = new Intent(this, PetListaActivity.class);
            startActivity(i);

        }else if(view == this.btnHome){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);

        }
    }

    public void mapearComponente(){
        this.btnPessoa = findViewById(R.id.adminactivity_btnPessoa);
        this.btnPet = findViewById(R.id.adminactivity_btnPet);
        this.btnHome = findViewById(R.id.adminactivity_btnHome);

    }

}
