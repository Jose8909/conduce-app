package com.jose.conduceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class VisorWebTickTockActivity extends AppCompatActivity {
    WebView miVisorWeb;
    String url = "https://api.whatsapp.com/send/?phone=573204414989";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_web_tick_tock);

        miVisorWeb = (WebView) findViewById(R.id.visorWeb);
        final WebSettings ajustesVisorWeb = miVisorWeb.getSettings();
        ajustesVisorWeb.setJavaScriptEnabled(true);
        miVisorWeb.loadUrl(url);
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