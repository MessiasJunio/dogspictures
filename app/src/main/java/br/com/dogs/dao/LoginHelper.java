package br.com.dogs.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.com.dogs.model.LoginModel;

public class LoginHelper extends DataBase implements IDAO<LoginModel> {

    private SQLiteDatabase sqLiteDatabase;
    private ContentValues valores;

    public LoginHelper(Context context) {
        super(context);
        this.sqLiteDatabase = getWritableDatabase();
    }


    @Override
    public int inserir(LoginModel dml) {
        return 0;
    }

    @Override
    public ArrayList<LoginModel> consultar(String filtro) {
        //TODO
        ArrayList<LoginModel> res = new ArrayList<>();
        Cursor cursor; //Recebe os dados do banco

        cursor = sqLiteDatabase.query("pessoa",null, "where login = ? and senha = ?", new String[] {"%" + filtro + "%"}, null, null, "DATA", null);

        if(cursor.getCount()> 0){  //      TEM REGISTRO NA CONSULTA
            cursor.moveToFirst(); //  POSICIONEI NO 1º ELEMENTO DO CURSOR
        /*    do{
                Tarefa tarefa = new Tarefa();  //CRIA UMA TAREFA
                tarefa.setId(cursor.getInt(0));
                tarefa.setDescricao(cursor.getString(1));
                try{
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    tarefa.setData(sdf.parse(cursor.getString(2)));
                }catch (Exception e){
                    e.printStackTrace();
                }
                res.add(tarefa); // ADICIONA A TAREFA NO ARRAY
            }while(cursor.moveToNext());
        */
        }

        return res;
    }

    @Override
    public int alterar(LoginModel dml, String filtro) {
        return 0;
    }

    @Override
    public void deletar(LoginModel dml) {

    }


    public Boolean validaLoginUsario(String email, String senha){
        boolean res = false;
        Cursor cursor = sqLiteDatabase.query("pessoa",null, " email = ? and senha = ?", new String[] {email, senha}, null, null,  null);
        if(cursor.getCount() == 1 && cursor != null)
            res = true;
        return res;
    }

    public Boolean validaLoginAdministrador(String email, String senha, String cpf){
        boolean res = false;
        Cursor cursor = sqLiteDatabase.query("pessoa",null, "where email = ? and senha = ? and cpf = ?", new String[] {email, senha, cpf}, null, null,  null);
        if(cursor.getCount() == 1 && cursor != null)
            res = true;
        return res;
    }
}
