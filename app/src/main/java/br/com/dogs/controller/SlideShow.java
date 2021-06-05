package br.com.dogs.controller;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import br.com.dogs.R;
import br.com.dogs.utils.SlidePersonalizado;

public class SlideShow extends AppCompatActivity {
    private ViewPager viewPager;
    private SlidePersonalizado  slidePersonalizado ;
    private String escolhaRaca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentacao_dogs);
        viewPager= findViewById(R.id.apresentacao_viewPager);
        this.slidePersonalizado = new SlidePersonalizado(this);

        Bundle bundle = getIntent().getExtras();
        this.slidePersonalizado.setRacaFoto(bundle.get("slide").toString());
        viewPager.setAdapter(this.slidePersonalizado);


    }

    public void escolhaRaca(String raca){

        this.escolhaRaca = raca;
    }
    public String getRaca(){
        return escolhaRaca;
    }


}
