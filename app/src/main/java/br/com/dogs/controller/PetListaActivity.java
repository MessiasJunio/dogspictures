package br.com.dogs.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import br.com.dogs.R;
import br.com.dogs.dao.PetHelper;
import br.com.dogs.model.PetModel;
import br.com.dogs.utils.ListViewPet;

public class PetListaActivity extends AppCompatActivity {
    private ListView listVisivel;
    private Button btnNovoCadastro;
    private PetModel pet;
    private PetHelper petDao;
    private ArrayList<PetModel> arrayListPet;
    private ArrayAdapter<PetModel> arrayAdapterPet;
    private SearchView pesquisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_lista);
        listVisivel =  findViewById(R.id.listPet);
        registerForContextMenu(listVisivel);

        btnNovoCadastro =  findViewById(R.id.petlistaactivity_btnNovoCadastro);
        pesquisa = findViewById(R.id.petlista_serchPequisa);

        btnNovoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PetListaActivity.this,CadastroPetActivity.class);
                startActivity(i);
            }
        });
        listVisivel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PetModel petEnviada = (PetModel) arrayAdapterPet.getItem(position);

                Intent i = new Intent(PetListaActivity.this,CadastroPetActivity.class);
                i.putExtra("pessoa-enviada",petEnviada);
                startActivity(i);
            }
        });

        listVisivel.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                pet = arrayAdapterPet.getItem(position);
                return false;
            }
        });

        pesquisa.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                atualizaLista(newText);
                return false;
            }
        });
    }
    public void populaLista(){

        petDao = new PetHelper(PetListaActivity.this);

       this.arrayListPet = this.petDao.consultar("");
        petDao.close();

        if (listVisivel != null){
            this.arrayAdapterPet = new ArrayAdapter<>(PetListaActivity.this,
                    android.R.layout.simple_list_item_1,this.arrayListPet);
            ListViewPet list = new ListViewPet(this.arrayListPet,this);
            listVisivel.setAdapter(list);
        }
    }
    protected void onResume() {
        super.onResume();
        populaLista();
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem mDelete = menu.add(" Deletar esse Registro ");
        mDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                petDao = new PetHelper(PetListaActivity.this);
                petDao.deletar(pet);
                petDao.close();

                alert("Registro excluido com sucesso!");
                populaLista();
                return false;
            }

        });
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

    private void atualizaLista(String filtro){
        PetHelper petHelper = new PetHelper(this);
        this.arrayListPet = petHelper.consultar(filtro);
        ListViewPet list = new ListViewPet(this.arrayListPet,this);
        listVisivel.setAdapter(list);
    }

}
