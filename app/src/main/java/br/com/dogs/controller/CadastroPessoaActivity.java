package br.com.dogs.controller;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.com.dogs.R;
import br.com.dogs.dao.PessoaHelper;
import br.com.dogs.model.PessoaModel;
import br.com.dogs.utils.Mascara;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.GRAY;

public class CadastroPessoaActivity extends AppCompatActivity {

    private AutoCompleteTextView edtNome, edtCpf,  edtTelefone, edtNascimento, edtEmail, edtSenha, edtConfirmaSenha;
    private Button btnCadastar, btnTestar;
    private PessoaModel pessoaModel, alteraPessoa;
    private PessoaHelper pessoaDao;
    private Spinner spinSexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoa);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        this.mapearComponente();
        this.mascaraComponete();
        this.spinnerSexo();


        Intent it = getIntent();
        this.alteraPessoa = (PessoaModel) it.getSerializableExtra("pessoa-enviada");
        this.pessoaModel = new PessoaModel();
        this.pessoaDao = new PessoaHelper(CadastroPessoaActivity.this);


        if(this.alteraPessoa != null){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            this.btnCadastar.setText("Alterar");
            this.edtNome.setText(this.alteraPessoa.getNome());
            this.edtCpf.setText(this.alteraPessoa.getCpf());
            this.edtTelefone.setText(this.alteraPessoa.getTelefone());
            this.edtNascimento.setText(sdf.format(alteraPessoa.getNascimento()));
            for (int i=0;i<spinSexo.getCount();i++){
                if (spinSexo.getItemAtPosition(i).toString().equals(alteraPessoa.getSexo())){
                    this.spinSexo.setSelection(i);

                }
            }

            this.edtEmail.setText(this.alteraPessoa.getEmail());
            this.edtSenha.setText(this.alteraPessoa.getSenha());
            this.pessoaModel.setId(this.alteraPessoa.getId());
        }else {
            btnCadastar.setText("Cadastrar");
        }

        this.btnCadastar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnCadastar.getText().toString().equals("Alterar")){
                    alterar();
                }else{
                    cadastrar();
                }
            }
        });
    }

    public void cadastrar()  {

            this.pessoaModel = new PessoaModel();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            pessoaModel.setNome(this.edtNome.getText().toString());
            pessoaModel.setCpf(this.edtCpf.getText().toString());

            try {
                pessoaModel.setNascimento(sdf.parse(this.edtNascimento.getText().toString()));
            }catch(ParseException e) {
                this.edtNascimento.setError("Data Inválida!");
                this.edtNascimento.requestFocus();
                return;
            }
            sdf.setLenient(false);
            pessoaModel.setSexo(this.spinSexo.getSelectedItem().toString());

            pessoaModel.setTelefone(this.edtTelefone.getText().toString());
            pessoaModel.setEmail(this.edtEmail.getText().toString());


            if(!this.edtSenha.getText().toString().equals(this.edtConfirmaSenha.getText().toString())) {
                Toast.makeText(this, "Senhas diferentes "  , Toast.LENGTH_LONG).show();
                this.edtSenha.requestFocus();
                this.edtSenha.getText().clear();
                this.edtConfirmaSenha.getText().clear();
                return;
            }else{
                pessoaModel.setSenha(this.edtSenha.getText().toString());

            }

            PessoaHelper pessoaHelper = new PessoaHelper(this);
            try {
                     if(!pessoaModel.getNome().equals("")  && pessoaModel.getNome() != null &&
                        !pessoaModel.getCpf().equals("")   && pessoaModel.getCpf() !=null &&
                        !pessoaModel.getEmail().equals("") && pessoaModel.getEmail() != null &&
                        !pessoaModel.getSenha().equals("") && pessoaModel.getSenha() != null &&
                             !pessoaModel.getSexo().equals("") && pessoaModel.getSexo() != null) {

                            pessoaHelper.inserir(pessoaModel);
                            this.limparComponente();
                            Toast.makeText(this, "Agradecemos pelo cadastro " + pessoaModel.getNome() , Toast.LENGTH_LONG).show();
                            finish();

                }else{
                    Toast.makeText(this, "Preencha os dados corretamente", Toast.LENGTH_LONG).show();

                }
            }catch (SQLiteException e){
                Toast.makeText(this, "ERRO AO INSERIR", Toast.LENGTH_LONG).show();
            }
    }


    public void alterar(){

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        pessoaModel.setNome(this.edtNome.getText().toString());
        pessoaModel.setCpf(this.edtCpf.getText().toString());

        try {
            pessoaModel.setNascimento(sdf.parse(this.edtNascimento.getText().toString()));
        }catch(ParseException e) {
            this.edtNascimento.setError("Data Inválida!");
            this.edtNascimento.requestFocus();
            return;
        }
        sdf.setLenient(false);
        pessoaModel.setSexo(this.spinSexo.getSelectedItem().toString());

        pessoaModel.setTelefone(this.edtTelefone.getText().toString());
        pessoaModel.setEmail(this.edtEmail.getText().toString());
        pessoaModel.setSenha(this.edtSenha.getText().toString());


        PessoaHelper pessoaHelper = new PessoaHelper(this);
        try {
            if(!pessoaModel.getNome().equals("")  && pessoaModel.getNome() != null &&
                    !pessoaModel.getCpf().equals("")   && pessoaModel.getCpf() !=null &&
                    !pessoaModel.getEmail().equals("") && pessoaModel.getEmail() != null &&
                    !pessoaModel.getSenha().equals("") && pessoaModel.getSenha() != null &&
                    !pessoaModel.getSexo().equals("") && pessoaModel.getSexo() != null) {

                pessoaHelper.alterar(pessoaModel, "");
                Toast.makeText(this, "Você alterou  os dados de  " + pessoaModel.getNome() , Toast.LENGTH_LONG).show();
                finish();
            }else{
                Toast.makeText(this, "Preencha os dados corretamente", Toast.LENGTH_LONG).show();

            }
        }catch (SQLiteException e){
            Toast.makeText(this, "ERRO AO INSERIR", Toast.LENGTH_LONG).show();
        }
    }

    private void mapearComponente(){
        this.edtNome = findViewById(R.id.cadastropessoa_edtNome);
        this.edtCpf = findViewById(R.id.cadastropessoa_edtCpf);
        this.edtNascimento = findViewById(R.id.cadastropessoa_edtNascimento);
        this.edtTelefone = findViewById(R.id.cadastropessoa_edtTelefone);
        this.edtEmail = findViewById(R.id.cadastropessoa_edtEmail);
        this.edtSenha =  findViewById(R.id.cadastropessoa_edtSenha);
        this.edtConfirmaSenha = findViewById(R.id.cadastropessoa_edtConfirmarSenha);
        this.btnCadastar =  findViewById(R.id.cadastropessoa_btnCadastrar);
        this.spinSexo = findViewById(R.id.cadastropessoa_spinSexo);
        //this.btnTestar =  findViewById(R.id.cadastropessoa_btnTestar);
    }
    private void mascaraComponete(){

        this.edtCpf.addTextChangedListener(new Mascara("###.###.###-##"));
        this.edtTelefone.addTextChangedListener(new Mascara("## #####-####"));
        this.edtNascimento.addTextChangedListener(new Mascara("##/##/####"));
    }

    public void limparComponente(){
        this.edtNome.getText().clear();
        this.edtCpf.getText().clear();
        this.edtTelefone.getText().clear();
        this.edtNascimento.getText().clear();
        this.edtEmail.getText().clear();
        this.edtSenha.getText().clear();
        this.edtConfirmaSenha.getText().clear();
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
        spinnerArrayAdapter.add("Masculino");
        spinnerArrayAdapter.add("Feminino");


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


    public void testar(View view){
        if (this.btnCadastar  == view){
            PessoaModel pessoaModel = new PessoaModel();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            try {
                pessoaModel.setNome(this.edtNome.getText().toString());
                pessoaModel.setCpf(this.edtCpf.getText().toString());
                pessoaModel.setNascimento(sdf.parse(this.edtNascimento.getText().toString()));
                pessoaModel.setTelefone(this.edtTelefone.getText().toString());
                pessoaModel.setEmail(this.edtEmail.getText().toString());
                pessoaModel.setSenha(this.edtSenha.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "Pessoa " + this.edtNome.getText() + pessoaModel.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
