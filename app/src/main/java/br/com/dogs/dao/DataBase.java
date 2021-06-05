package br.com.dogs.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {
    private static final String BANCO = "DOGS";
    private static final int VERSAO = 3;
    private ArrayList<String> createTable;

    public DataBase(Context context) {
        super(context, BANCO, null, VERSAO);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.carregarLista();
            for(String ddl: this.createTable)
                db.execSQL(ddl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion == 2 ) {
            carregarLista();
            db.execSQL("DROP TABLE IF EXISTS " + "pessoa");
            db.execSQL("DROP TABLE IF EXISTS " + "pet");
            for(String ddl: this.createTable)
                db.execSQL(ddl);
        }
    }

    public void carregarLista(){
        this.createTable = new ArrayList<>();
        this.createTable.add(createPessoa());
        this.createTable.add(createPet());

    }

    public String createPessoa(){
        StringBuilder createPessoa = new StringBuilder();
        createPessoa.append("CREATE TABLE pessoa (_id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        createPessoa.append("nome TEXT,");
        createPessoa.append("cpf TEXT,");
        createPessoa.append("telefone TEXT,");
        createPessoa.append("nascimento TEXT,");
        createPessoa.append("sexo TEXT,");
        createPessoa.append("email TEXT,");
        createPessoa.append("senha TEXT)");
        return createPessoa.toString();
    }

    public String createPet(){
        StringBuilder createPet = new StringBuilder();
        createPet.append("CREATE TABLE pet (_id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        createPet.append("nome TEXT,");
        createPet.append("raca TEXT,");
        createPet.append("nascimento TEXT,");
        createPet.append("sexo TEXT,");
        createPet.append("descricao TEXT)");
        return createPet.toString();
    }

}
