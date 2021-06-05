package br.com.dogs.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.com.dogs.model.PetModel;

public class PetHelper extends DataBase implements IDAO<PetModel> {

    private SQLiteDatabase sqLiteDatabase;
    private ContentValues valores;


    public PetHelper(Context context) {
        super(context);
        this.sqLiteDatabase = getWritableDatabase();
    }

    @Override
    public int inserir(PetModel dml) {
        int res=0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.valores = new ContentValues();
        this.valores.put("nome", dml.getNome());
        this.valores.put("raca", dml.getRaca());
        this.valores.put("nascimento", sdf.format(dml.getNascimento()));
        this.valores.put("sexo", dml.getSexo());
        this.valores.put("descricao", dml.getDescricao());

        res = (int) sqLiteDatabase.insert("pet", null, this.valores);
        return  res;
    }
    @Override
    public ArrayList<PetModel> consultar(String filtro) {
        String[] colunas = {"_id", "nome","raca","nascimento","sexo","descricao"};
        Cursor cursor;
        if(!filtro.equals("")) {
            cursor = this.sqLiteDatabase.query("pet",colunas,"raca like ?",new String[]{"%" +filtro+"%"},null, null,"upper(nome)",null);
        }else{
            cursor = this.sqLiteDatabase.query("pet",colunas,null,null,null, null,"upper(nome)",null);
        }

        ArrayList<PetModel> listPet = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        while (cursor.moveToNext()){
            PetModel p = new PetModel();
            p.setId(cursor.getInt(0));
            p.setNome(cursor.getString(1));
            p.setRaca(cursor.getString(2));
            try {
                p.setNascimento(sdf.parse(cursor.getString(3)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            p.setSexo(cursor.getString(4));
            p.setDescricao(cursor.getString(5));
            listPet.add(p);
        }
        return listPet;
    }
    @Override
    public int alterar(PetModel dml, String filtro) {
        ContentValues values = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int res;
        values.put("nome", dml.getNome());
        values.put("raca", dml.getRaca());
        values.put("nascimento", sdf.format(dml.getNascimento()));
        values.put("sexo", dml.getSexo());
        values.put("descricao", dml.getDescricao());

        String[] args = {String.valueOf(dml.getId())};
        res = this.sqLiteDatabase.update("pet",values,"_id"+"=?",args);

        return res;
    }

    @Override
    public void deletar(PetModel dml) {

        String[] args = {String.valueOf(dml.getId())};
        this.sqLiteDatabase.delete("pet","_id"+"=?",args);

    }
}
