package com.example.basbiriktir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView puanEkrani;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        puanEkrani=findViewById(R.id.tvPuanTekrari);
        Intent intent=getIntent();
        int puan=intent.getIntExtra("sonPuan",-1);
        if (puan !=-1){
            puanEkrani.setText("Bir Önceki Oyundan Aldığınız Puan:  "+puan);
        }

}
    public void oyunuBaslat(View view){
        Intent intent=new Intent(MainActivity2.this,MainActivity.class);
        startActivity(intent);

    }
}