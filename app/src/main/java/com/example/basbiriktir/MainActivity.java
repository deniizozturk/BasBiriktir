package com.example.basbiriktir;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tvSure;
    TextView tvpuan;
    int puan;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] gorseller;
    Handler handler;
    Runnable runnable;
    int[] gorseller2 = new int[]{
            R.drawable.coin,
            R.drawable.barni
    };
    int resimPuani;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSure = findViewById(R.id.tvSure);
        tvpuan = findViewById(R.id.tvPuan);

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        gorseller = new ImageView[]{
                imageView,
                imageView2,
                imageView3,
                imageView4,
                imageView5,
                imageView6,
                imageView7,
                imageView8,
                imageView9
        };
        gorselleriGizle();
        rastgeleGoster();
        puan = 0;
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvSure.setText("Kalan süre:  " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                tvSure.setText("Süre bitti!");
                handler.removeCallbacks(runnable);
                gorselleriGizle();
                alertGoster();
            }
        }.start();
    }

    private void alertGoster() {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("OYUN BİTTİ");
        alert.setMessage("Aldığınız puan:  " + puan + "\nTekrar oynamak istiyorsan evet'e tıkla");
        alert.setPositiveButton("EVET", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        alert.setNegativeButton("HAYIR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("sonPuan", puan);
                startActivity(intent);
            }
        });
        alert.show();
    }

    public void puaniArttir(View view) {
        if (resimPuani == R.drawable.barni) {
            puan += 10;
            tvpuan.setText("puan:  " + puan);
        } else if (resimPuani == R.drawable.coin) {
            puan += 5;
            tvpuan.setText("puan: " + puan);
        }
    }

    private void gorselleriGizle() {
        for (ImageView view : gorseller) {
            view.setVisibility(View.INVISIBLE);
        }
    }

    private void rastgeleGoster() {

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                gorselleriGizle();
                Random random = new Random();
                int i = random.nextInt(9);
                int degisken = (int) (Math.random() * gorseller2.length);
                gorseller[i].setVisibility(View.VISIBLE);
                gorseller[i].setBackgroundResource(gorseller2[degisken]);
                handler.postDelayed(this, 500);
                resimPuani = gorseller2[degisken];
            }
        };
        handler.post(runnable);
    }
}
