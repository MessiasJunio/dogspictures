package br.com.dogs.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import br.com.dogs.R;

public class SlidePersonalizado extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private int[] terrier = {R.drawable.terrierfilhote, R.drawable.terriermedio, R.drawable.terrieradulto};
    private int[] bulldog = {R.drawable.bulldogfilhote, R.drawable.bulldogmedio, R.drawable.bulldogadulto};
    private int[] doberman = {R.drawable.dobermanfilhote, R.drawable.dobermanmedio, R.drawable.dobermanadulto};
    private int[] golden = {R.drawable.goldenfilhote, R.drawable.goldenmedio, R.drawable.goldenadulto};
    private int[] pitbull = {R.drawable.pitbullfilhote, R.drawable.pitbullmedio, R.drawable.pitbulladulto};
    private int[] poodle = {R.drawable.poodlefilhote, R.drawable.poodlemedio, R.drawable.poodleadulto};
    private String raca = "";

    public SlidePersonalizado(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {

        return terrier.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return  (view==object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        this.layoutInflater= (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView=layoutInflater.inflate(R.layout.activity_slide_show,container,false);


            return escolhaRaca(container, position, itemView);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }


    public void setRacaFoto(String raca){
        this.raca = raca;
    }
    public String getRacaFoto(){
        return raca;
    }

    public View escolhaRaca(ViewGroup container, int position, View itemView){

        ImageView imageView =itemView.findViewById(R.id.slideshow_imgView);
        TextView textView =  itemView.findViewById(R.id.imageCount);

        if(position == 0) {
            textView.setText("Filhote:");
        }else if(position == 1){
            textView.setText("Médio:");
        }else if(position == 2) {
            textView.setText("Adulto:");
        }

        if(getRacaFoto().equals("bulldog")){
            imageView.setImageResource(bulldog[position]);
        }else if(getRacaFoto().equals("golden")){
            imageView.setImageResource(golden[position]);
        }else if(getRacaFoto().equals("terrier")){
            imageView.setImageResource(terrier[position]);
        }else if(getRacaFoto().equals("pitbull")){
            imageView.setImageResource(pitbull[position]);
        }else if(getRacaFoto().equals("doberman")) {
            imageView.setImageResource(doberman[position]);
        }else if(getRacaFoto().equals("poodle")){
            imageView.setImageResource(poodle[position]);
        }
        container.addView(itemView);
        return itemView;
    }
}
