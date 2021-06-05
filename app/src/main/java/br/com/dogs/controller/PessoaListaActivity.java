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
import br.com.dogs.dao.PessoaHelper;
import br.com.dogs.model.PessoaModel;
import br.com.dogs.utils.ListViewPessoa;

public class PessoaListaActivity extends AppCompatActivity {

    private ListView listVisivel;
    private Button btnNovoCadastro;
    private PessoaModel pessoa;
    private PessoaHelper pessoaDao;
    private ArrayList<PessoaModel> arrayListPessoa;
    private ArrayAdapter<PessoaModel> arrayAdapterPessoa;
    private SearchView pesquisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa_lista);

        listVisivel =  findViewById(R.id.listPessoas);
        pesquisa = findViewById(R.id.pessoalista_serchPequisa);
        registerForContextMenu(listVisivel);

        btnNovoCadastro = (Button) findViewById(R.id.pessoalistaactivity_btnNovoCadastro);

        btnNovoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PessoaListaActivity.this,CadastroPessoaActivity.class);
                startActivity(i);
            }
        });

        listVisivel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PessoaModel pessoaEnviada = (PessoaModel) arrayAdapterPessoa.getItem(position);

                Intent i = new Intent(PessoaListaActivity.this,CadastroPessoaActivity.class);
                i.putExtra("pessoa-enviada",pessoaEnviada);
                startActivity(i);
            }
        });

        listVisivel.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                pessoa = arrayAdapterPessoa.getItem(position);
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
        pessoaDao = new PessoaHelper(PessoaListaActivity.this);

        this.arrayListPessoa = this.pessoaDao.consultar("");
        pessoaDao.close();

        if (listVisivel != null){
            this.arrayAdapterPessoa = new ArrayAdapter<PessoaModel>(PessoaListaActivity.this,
                    android.R.layout.simple_list_item_1,this.arrayListPessoa);
            ListViewPessoa list = new ListViewPessoa(this.arrayListPessoa,this);
            listVisivel.setAdapter(list);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        populaLista();
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem mDelete = menu.add(" Deletar esse Registro ");
        mDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                pessoaDao = new PessoaHelper(PessoaListaActivity.this);
                pessoaDao.deletar(pessoa);
                pessoaDao.close();

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
        PessoaHelper pessoaHelper = new PessoaHelper(this);
        this.arrayListPessoa = pessoaHelper.consultar(filtro);
        ListViewPessoa list = new ListViewPessoa(this.arrayListPessoa,this);
        listVisivel.setAdapter(list);
    }
}
