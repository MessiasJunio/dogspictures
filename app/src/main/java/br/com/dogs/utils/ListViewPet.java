package br.com.dogs.utils;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.dogs.R;
import br.com.dogs.model.PetModel;

public class ListViewPet extends BaseAdapter {
    private final ArrayList<PetModel> petList;
    private final Activity act;

    public ListViewPet(ArrayList<PetModel> petList, Activity act) {
        this.petList = petList;
        this.act = act;
    }

    @Override
    public int getCount() {
        return this.petList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.petList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater()
                .inflate(R.layout.activity_lista_personalizada, parent, false);
         PetModel petModel = (PetModel) petList.get(position);

        TextView descricao = (TextView)
                view.findViewById(R.id.listapersonalizada_txtDescricao);
        ImageView imagem = (ImageView)
                view.findViewById(R.id.listapersonalizada_imgRaca);

        descricao.setText(petModel.toString());
        if(petModel.getRaca().equals("Bulldog"))
            imagem.setImageResource(R.drawable.bulldog);
        else if(petModel.getRaca().equals("Golden Retriever"))
            imagem.setImageResource(R.drawable.goldenretriever);
        else if(petModel.getRaca().equals("Bull Terrier"))
            imagem.setImageResource(R.drawable.bullterrier);
        else if(petModel.getRaca().equals("Pit Bull"))
            imagem.setImageResource(R.drawable.pitbull);
        else if(petModel.getRaca().equals("Doberman"))
            imagem.setImageResource(R.drawable.doberman);
        else if(petModel.getRaca().equals("Poodle"))
            imagem.setImageResource(R.drawable.poogle);
        return view;
    }
}
