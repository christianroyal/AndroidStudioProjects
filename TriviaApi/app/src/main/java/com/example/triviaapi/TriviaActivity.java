package com.example.triviaapi;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;


public class TriviaActivity extends AppCompatActivity{
ImageView imageView;
Button btna,btnb,btnc,btnd,btntrue,btnfalse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.triviaactivity_mc);
        imageView=findViewById(R.id.container);
        btna = findViewById(R.id.btn_a);
        btnb= findViewById(R.id.btn_b);
        btnc = findViewById(R.id.btn_c);
        btnd = findViewById(R.id.btn_d);
        btntrue = findViewById(R.id.btn_true);
        btnfalse = findViewById(R.id.btn_false);


        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("PassingUrls");
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Glide.with(this).load(message).into(imageView);

        btna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();


            }
        });
        btnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();


            }
        });
        btnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();


            }
        });
        btnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();


            }
        });
        btntrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();


            }
        });
        btnfalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();


            }
        });


    }

}
