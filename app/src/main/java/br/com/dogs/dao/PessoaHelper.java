package br.com.dogs.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.com.dogs.model.PessoaModel;

public class PessoaHelper extends DataBase implements  IDAO<PessoaModel>{

     private SQLiteDatabase sqLiteDatabase;
     private ContentValues valores;

    public PessoaHelper(Context context) {
        super(context);
        this.sqLiteDatabase = getWritableDatabase();
    }

    @Override
    public int inserir(PessoaModel dml) {

        int res=0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.valores = new ContentValues();
        this.valores.put("nome", dml.getNome());
        this.valores.put("cpf", dml.getCpf());
        this.valores.put("nascimento", sdf.format(dml.getNascimento()));
        this.valores.put("sexo", dml.getSexo());
        this.valores.put("telefone", dml.getTelefone());
        this.valores.put("email", dml.getEmail());
        this.valores.put("senha", dml.getSenha());

        res = (int) sqLiteDatabase.insert("pessoa", null, this.valores);
        return  res;
    }

    @Override
    public ArrayList<PessoaModel> consultar(String filtro) {
        String[] colunas = {"_id", "nome","cpf","nascimento","sexo", "telefone","email","senha"};
        Cursor cursor;
        if(!filtro.equals("")) {
            cursor = this.sqLiteDatabase.query("pessoa", colunas, "nome like ?", new String[]{"%" + filtro + "%"}, null, null, "upper(nome)", null);
        }else {
            cursor = this.sqLiteDatabase.query("pessoa",colunas,null,null,null, null,"upper(nome)",null);
        }
        ArrayList<PessoaModel> listPessoa =  new ArrayList<PessoaModel>();

        while (cursor.moveToNext()){
            PessoaModel p = new PessoaModel();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            p.setId(cursor.getInt(0));
            p.setNome(cursor.getString(1));
            p.setCpf(cursor.getString(2));
            try {
                p.setNascimento(sdf.parse(cursor.getString(3)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            p.setSexo(cursor.getString(4));
            p.setTelefone(cursor.getString(5));
            p.setEmail(cursor.getString(6));
            p.setSenha(cursor.getString(7));
            listPessoa.add(p);
        }
        return listPessoa;
    }

    @Override
    public int alterar(PessoaModel dml, String filtro) {

        ContentValues values = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int res;

        values.put("nome",dml.getNome());
        values.put("cpf",dml.getCpf());
        values.put("nascimento", sdf.format(dml.getNascimento()));
        values.put("sexo",dml.getSexo());
        values.put("telefone",dml.getTelefone());
        values.put("email",dml.getEmail());
        values.put("senha",dml.getSenha());


        String[] args = {String.valueOf(dml.getId())};
        res = this.sqLiteDatabase.update("pessoa",values,"_id"+"=?",args);

        return res;
    }

    @Override
    public void deletar(PessoaModel dml) {
        String[] args = {String.valueOf(dml.getId())};
        this.sqLiteDatabase.delete("pessoa","_id"+"=?",args);
    }

}
