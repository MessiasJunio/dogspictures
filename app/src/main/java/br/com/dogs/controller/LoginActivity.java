package br.com.dogs.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.dogs.R;
import br.com.dogs.dao.LoginHelper;

public class LoginActivity extends Activity {
    private AutoCompleteTextView edtEmail, edtSenha;
    private TextView txtFazerLogin;
    private LoginHelper loginHelper;
    private Button btnEntrar;
    private RadioButton radLembrar;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPreferencesEditor;
    private Boolean isSalvarLogin;
    private Boolean isLogado = false;
    private Date data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(br.com.dogs.R.layout.activity_login);
        this.mapearComponente();
        this.txtFazerLogin.setPaintFlags(this.txtFazerLogin.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        this.loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        this.loginPreferencesEditor = loginPreferences.edit();

        isSalvarLogin = loginPreferences.getBoolean("salvarlogin", false);
        if (isSalvarLogin == true) {
            this.edtEmail.setText(loginPreferences.getString("email", ""));
            this.edtSenha.setText(loginPreferences.getString("senha", ""));
            radLembrar.setChecked(true);
        }else{
            radLembrar.setChecked(false);
        }
    }

    private void cliqueLogin() {

            this.loginHelper = new LoginHelper(this);
            String email = edtEmail.getText().toString();
            String[] dominio = email.split("@");
            String senha = edtSenha.getText().toString();

            try {
                if (dominio[1].toString().equals("gerentedogs.com") && this.loginHelper.validaLoginUsario(email, senha) == true) {
                    Intent it = new Intent(this, Carregamento.class);
                    startActivity(it);
                    SharedPreferences pref = getSharedPreferences("prefs", MODE_PRIVATE);
                    alert(pref.getString("Logou", "Bem Vindo ao Dogs"));
                    ultimoLogin();
                    finishAffinity();

                } else if (this.loginHelper.validaLoginUsario(email, senha) == true) {
                    Intent it = new Intent(this, MainActivity.class);
                    startActivity(it);
                    SharedPreferences pref = getSharedPreferences("prefs", MODE_PRIVATE);
                    alert(pref.getString("Logou", "Bem Vindo ao Dogs"));
                    ultimoLogin();
                    finishAffinity();

                } else {
                    alert("Acho que você digitou alguma coisa errada!");
                }
            }catch(Exception e){
                    alert("Cara dê uma olhada neste seu E-mail");
            }

    }

    public void clicou(View view) {
        if (view == this.txtFazerLogin) {
            Intent i = new Intent(this, CadastroPessoaActivity.class);
            startActivity(i);
        }
    }

    public void lembraLogin(View view){
        if(view == btnEntrar) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(this.edtEmail.getWindowToken(), 0);

            String email = this.edtEmail.getText().toString();
            String senha = this.edtSenha.getText().toString();

            if (radLembrar.isChecked()) {
                this.loginPreferencesEditor.putBoolean("salvarlogin", true);
                this.loginPreferencesEditor.putString("email", email);
                this.loginPreferencesEditor.putString("senha", senha);
                this.loginPreferencesEditor.apply();
            } else {
                this.loginPreferencesEditor.clear();
                this.loginPreferencesEditor.apply();

            }
            cliqueLogin();
        }

    }
    private void mapearComponente() {
        this.edtEmail = findViewById(R.id.loginactivity_edtEmail);
        this.edtSenha = findViewById(R.id.loginactivity_edtSenha);
        this.txtFazerLogin = findViewById(R.id.login_fazerLogin);
        this.btnEntrar = findViewById(R.id.loginactivity_btnEntrar);
        this.radLembrar = findViewById(R.id.loginactivity_radLembrar);
    }
    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

    private void ultimoLogin(){
            this.data = new Date();
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Logou", "Último acesso: " + formatador.format(data));
            editor.apply();

    }

    public void radioClique(View view) {
        if (view == this.radLembrar) {
            if (this.radLembrar.isSelected()) {
                this.radLembrar.setSelected(false);
                this.radLembrar.setChecked(false);

            } else{
                this.radLembrar.setSelected(true);
                this.radLembrar.setChecked(true);

            }
        }
    }
}
