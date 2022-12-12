package com.jose.conduceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jose.conduceapp.modelo.Referidos;

import java.util.ArrayList;
import java.util.List;

public class referidosActivity extends AppCompatActivity {
    //Declaracion de variables
    FirebaseDatabase  firebaseDatabase;
    DatabaseReference databaseReference;

    private EditText txtNomReferido;
    private EditText txtDocReferido;
    private EditText txtNumReferido;
    private EditText txtNomRefiere;
    private EditText txtDocRefiere;
    private EditText txtNumRefiere;
    private ListView listViewReferido;

    private final List<Referidos> listReferidos = new ArrayList<>();
    ArrayAdapter<Referidos> arrayAdapterReferidos;
    Referidos referidosSelected;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referidos);
        txtNomReferido =(EditText) findViewById(R.id.txtNomReferido);
        txtDocReferido =(EditText) findViewById(R.id.txtDocReferido);
        txtNumReferido =(EditText) findViewById(R.id.txtNumReferido);
        txtNomRefiere =(EditText) findViewById(R.id.txtNomRefiere);
        txtDocRefiere =(EditText) findViewById(R.id.txtDocRefiere);
        txtNumRefiere =(EditText) findViewById(R.id.txtNumRefiere);
        listViewReferido =(ListView) findViewById(R.id.listViewReferido);


        inicializarBD();
        listarDatos();
        listViewReferido.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                referidosSelected= (Referidos) adapterView.getItemAtPosition(i);
                txtNomReferido.setText(referidosSelected.getNombreReferido());
                txtDocReferido.setText(referidosSelected.getDocumentoReferido());
                txtNumReferido.setText(referidosSelected.getContactoReferido());
                txtNomRefiere.setText(referidosSelected.getNombreRefiere());
                txtDocRefiere.setText(referidosSelected.getDocumentoRefiere());
                txtNumRefiere.setText(referidosSelected.getContactorefiere());
            }
        });
    }

    @Override//llamar barra menu
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_referidos,menu);
        return super.onCreateOptionsMenu(menu);
    }
    private void inicializarBD(){
        //Llamado a la base de datos
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        Toast.makeText(this, "Base de datos inicializada", Toast.LENGTH_SHORT).show();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        String txtnomReferido = txtNomReferido.getText().toString();
        String txtdocReferido = txtDocReferido.getText().toString();
        String txtnumReferido = txtNumReferido.getText().toString();
        String txtnomRefiere = txtNomRefiere.getText().toString();
        String txtdocRefiere = txtDocRefiere.getText().toString();
        String txtnumRefiere = txtNumRefiere.getText().toString();
        switch (item.getItemId()){
            case R.id.icono_nuevo:
                if (txtnomReferido.isEmpty()||(txtdocReferido.isEmpty())||(txtnumReferido.isEmpty())||(txtnomRefiere.isEmpty())||(txtdocRefiere.isEmpty())||(txtnumRefiere.isEmpty())){
                    validacion();
                }
                else {
                    Referidos objReferidos =new Referidos();
                    objReferidos.setNombreReferido(txtnomReferido);
                    objReferidos.setDocumentoReferido(txtdocReferido);
                    objReferidos.setContactoReferido(txtnumReferido);
                    objReferidos.setNombreRefiere(txtnomRefiere);
                    objReferidos.setDocumentoRefiere(txtdocRefiere);
                    objReferidos.setContactorefiere(txtnumRefiere);
                    databaseReference.child("Referir").child(objReferidos.getContactoReferido()).setValue(objReferidos);
                    Toast.makeText(this, "Registro guardado correctamente", Toast.LENGTH_SHORT).show();
                    limpiarCajas();
                }
                break;
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
    public void limpiarCajas(){
        txtNomReferido.setText("");
        txtDocReferido.setText("");
        txtNumReferido.setText("");
        txtNomRefiere.setText("");
        txtDocRefiere.setText("");
        txtNumRefiere.setText("");
    }
    public void validacion(){
        String txtnomReferido = txtNomReferido.getText().toString();
        String txtdocReferido = txtDocReferido.getText().toString();
        String txtnumReferido = txtNumReferido.getText().toString();
        String txtnomRefiere = txtNomRefiere.getText().toString();
        String txtdocRefiere = txtDocRefiere.getText().toString();
        String txtnumRefiere = txtNumRefiere.getText().toString();
        if(txtnomReferido.isEmpty()){
            this.txtNomReferido.setError("Requerido");
        }
        else if(txtdocReferido.isEmpty()){
            this.txtDocReferido.setError("Requerido");
        }
        else if(txtnumReferido.isEmpty()){
            this.txtNumReferido.setError("Requerido");
        }
        else if(txtnomRefiere.isEmpty()){
            this.txtNomRefiere.setError("Requerido");
        }
        else if(txtdocRefiere.isEmpty()){
            this.txtDocRefiere.setError("Requerido");
        }
        else {
            this.txtNumRefiere.setError("Requerido");
        }
    }
    public void listarDatos(){
        databaseReference.child("Referir").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listReferidos.clear();
                for (DataSnapshot objSnapshot: snapshot.getChildren()){
                    Referidos e= objSnapshot.getValue(Referidos.class);
                    listReferidos.add(e);
                    arrayAdapterReferidos=new ArrayAdapter<Referidos>(referidosActivity.this, android.R.layout.simple_list_item_1,listReferidos);
                    listViewReferido.setAdapter(arrayAdapterReferidos);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}