package br.com.dogs.controller;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.com.dogs.R;
import br.com.dogs.dao.PetHelper;
import br.com.dogs.model.PetModel;
import br.com.dogs.utils.Mascara;

import static android.graphics.Color.*;

public class CadastroPetActivity extends AppCompatActivity {
    private EditText edtNome, edtNascimento, edtDescricao;
    private Button btnsalvar;
    private PetModel petModel, alteraPet;
    private PetHelper petDao;
    private Spinner spinRaca, spinSexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pet);
        mapearComponente();
        mascaraComponete();
        spinnerRaca();
        spinnerSexo();

        Intent it = getIntent();
        this.alteraPet = (PetModel) it.getSerializableExtra("pessoa-enviada");
        this.petModel = new PetModel();
        this.petDao = new PetHelper(CadastroPetActivity.this);


        if (this.alteraPet != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            this.btnsalvar.setText("Alterar");
            this.edtNome.setText(this.alteraPet.getNome());
            for (int i=0;i<spinRaca.getCount();i++){
                if (spinRaca.getItemAtPosition(i).toString().equals(alteraPet.getRaca())){
                    this.spinRaca.setSelection(i);

                }
            }
            for (int i=0;i<spinSexo.getCount();i++){
                if (spinSexo.getItemAtPosition(i).toString().equals(alteraPet.getSexo())){
                    this.spinSexo.setSelection(i);
                }
            }

            this.edtNascimento.setText(sdf.format(alteraPet.getNascimento()));
            this.edtDescricao.setText(this.alteraPet.getDescricao());
            this.petModel.setId(this.alteraPet.getId());
        } else {
            btnsalvar.setText("Cadastrar");
        }

        this.btnsalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnsalvar.getText().toString().equals("Alterar")) {
                    alterar();
                } else {
                    cadastrar();
                }
            }
        });
    }

    public void cadastrar() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        petModel.setNome(this.edtNome.getText().toString());
        petModel.setRaca(this.spinRaca.getSelectedItem().toString());
        try {
            this.petModel.setNascimento(sdf.parse(this.edtNascimento.getText().toString()));
        } catch (ParseException e) {
            this.edtNascimento.setError("Data Inválida!");
            this.edtNascimento.requestFocus();
            return;
        }
        petModel.setSexo(this.spinSexo.getSelectedItem().toString());

        petModel.setDescricao(this.edtDescricao.getText().toString());


        PetHelper petHelper = new PetHelper(this);
        try {
            if (!petModel.getNome().equals("") && petModel.getNome() != null &&
                    !petModel.getRaca().equals("") && petModel.getRaca() != null &&
                    !petModel.getNascimento().equals("") && petModel.getNascimento() != null &&
                    !petModel.getSexo().equals("") && petModel.getSexo() != null &&
                    !petModel.getDescricao().equals("") && petModel.getDescricao() != null) {

                petHelper.inserir(petModel);
                Toast.makeText(this, "Agradecemos pelo cadastro " + petModel.getNome(), Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, "Preencha os dados corretamente", Toast.LENGTH_LONG).show();

            }
        } catch (SQLiteException e) {
            Toast.makeText(this, "ERRO AO INSERIR", Toast.LENGTH_LONG).show();
        }
    }


    public void alterar() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        petModel.setNome(this.edtNome.getText().toString());
        petModel.setRaca(this.spinRaca.getSelectedItem().toString());

        try {
            petModel.setNascimento(sdf.parse(this.edtNascimento.getText().toString()));
        }catch(ParseException e) {
            this.edtNascimento.setError("Data Inválida!");
            this.edtNascimento.requestFocus();
            return;
        }
        petModel.setSexo(this.spinSexo.getSelectedItem().toString());

        petModel.setDescricao(this.edtDescricao.getText().toString());

        PetHelper petHelper = new PetHelper(this);
        try {
            if (!petModel.getNome().equals("") && petModel.getNome() != null &&
                    !petModel.getRaca().equals("") && petModel.getRaca() != null &&
                    !petModel.getNascimento().equals("") && petModel.getNascimento() != null &&
                    !petModel.getSexo().equals("") && petModel.getSexo() != null &&
                    !petModel.getDescricao().equals("") && petModel.getDescricao() != null) {

                petHelper.alterar(petModel, "");
                Toast.makeText(this, "Você alterou  os dados de  " + petModel.getNome(), Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, "Preencha os dados corretamente", Toast.LENGTH_LONG).show();

            }
        } catch (SQLiteException e) {
            Toast.makeText(this, "ERRO AO INSERIR", Toast.LENGTH_LONG).show();
        }
    }


    public void mapearComponente() {
        this.edtNome = findViewById(R.id.cadastropet_edtNome);
        this.edtNascimento = findViewById(R.id.cadastropet_edtNascimento);
        this.edtDescricao = findViewById(R.id.cadastropet_edtDescricao);
        this.btnsalvar = findViewById(R.id.cadastropet_btnCadastrar);
        this.spinRaca = findViewById(R.id.cadastropet_spinRaca);
        this.spinSexo = findViewById(R.id.cadastropet_spinSexo);

    }
    private void mascaraComponete(){

        this.edtNascimento.addTextChangedListener(new Mascara("##/##/####"));
    }

    public void spinnerRaca(){
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_dropdown_item){

            @Override
            public boolean isEnabled(int position){

                if(position == 0){

                    return false;

                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {

                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;

                if(position == 0){

                    tv.setTextColor(GRAY);

                }else {
                    tv.setTextColor(BLACK);
                }

                return view;
            }
        };

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerArrayAdapter.add("Raça");
        spinnerArrayAdapter.add("Bulldog");
        spinnerArrayAdapter.add("Golden Retriever");
        spinnerArrayAdapter.add("Bull Terrier");
        spinnerArrayAdapter.add("Pit Bull");
        spinnerArrayAdapter.add("Doberman");
        spinnerArrayAdapter.add("Poodle");


        spinRaca.setAdapter(spinnerArrayAdapter);

        spinRaca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selectedItemText = (String) parent.getItemAtPosition(position);
                if(position == 0){

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void spinnerSexo(){
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_dropdown_item){

            @Override
            public boolean isEnabled(int position){

                if(position == 0){

                    return false;

                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {

                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;

                if(position == 0){

                    tv.setTextColor(GRAY);

                }else {
                    tv.setTextColor(BLACK);
                }

                return view;
            }
        };

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerArrayAdapter.add("Sexo");
        spinnerArrayAdapter.add("Macho");
        spinnerArrayAdapter.add("Fêmea");


        spinSexo.setAdapter(spinnerArrayAdapter);

        spinSexo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selectedItemText = (String) parent.getItemAtPosition(position);
                if(position == 0){

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void limparComponente() {
        this.edtNome.getText().clear();
        this.edtNascimento.getText().clear();
        this.edtDescricao.getText().clear();
    }

}
