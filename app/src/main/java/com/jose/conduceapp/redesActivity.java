package com.jose.conduceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class redesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redes);
    }
    @Override//llamar barra menu
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_redes,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.icono_regresar:
                Toast.makeText(this, "Regresar al menu", Toast.LENGTH_SHORT).show();
                Intent btnRegresar = new Intent(this,MainActivity.class);
                startActivity(btnRegresar);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}