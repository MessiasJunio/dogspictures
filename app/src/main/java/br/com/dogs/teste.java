package br.com.dogs;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import br.com.dogs.dao.PetHelper;
import br.com.dogs.model.PetModel;
import br.com.dogs.utils.ListViewPet;

public class teste extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personalizada);

        ArrayList<PetModel> arrayListPet = new ArrayList<>();

      //  ListView listVisivel = (ListView) findViewById(R.id.listaV);


        PetHelper petHelper = new PetHelper(this);

        arrayListPet = petHelper.consultar("");
        petHelper.close();
        ListViewPet adapter =
                new ListViewPet(arrayListPet, this);


        //listVisivel.setAdapter(adapter);

    }

}
