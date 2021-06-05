package br.com.dogs.utils;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.dogs.R;
import br.com.dogs.model.PessoaModel;
import br.com.dogs.model.PetModel;

public class ListViewPessoa extends BaseAdapter{
    private final ArrayList<PessoaModel> pessoaList;
    private final Activity act;

    public ListViewPessoa(ArrayList<PessoaModel> pessoaList, Activity act) {
        this.pessoaList = pessoaList;
        this.act = act;
    }

    @Override
    public int getCount() {
        return this.pessoaList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.pessoaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater()
                .inflate(R.layout.activity_lista_personalizada, parent, false);
        PessoaModel pessoaModel = (PessoaModel) pessoaList.get(position);

        TextView descricao = (TextView)
                view.findViewById(R.id.listapersonalizada_txtDescricao);
        ImageView imagem = (ImageView)
                view.findViewById(R.id.listapersonalizada_imgRaca);

        descricao.setText(pessoaModel.toString());
        if(pessoaModel.getSexo().equals("Masculino"))
            imagem.setImageResource(R.drawable.masculine_icon);
        else if(pessoaModel.getSexo().equals("Feminino"))
            imagem.setImageResource(R.drawable.feminino_icon);

        return view;
    }
}
