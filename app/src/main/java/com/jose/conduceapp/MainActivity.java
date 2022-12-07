package com.jose.conduceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void horarios (View view){
        Intent horarios = new Intent(MainActivity.this,horariosActivity.class);
        startActivity(horarios);
    }
    public void referir (View view){
        Intent referir = new Intent(MainActivity.this,referidosActivity.class);
        startActivity(referir);
    }
    public void redesSociales (View view){
        Intent redes = new Intent(MainActivity.this,redesActivity.class);
        startActivity(redes);
    }
}