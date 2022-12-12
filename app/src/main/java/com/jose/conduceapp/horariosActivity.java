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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jose.conduceapp.modelo.Horarios;

import java.util.ArrayList;
import java.util.List;

public class horariosActivity extends AppCompatActivity {
    //Declaracion de variables
    FirebaseDatabase  firebaseDatabase;
    DatabaseReference databaseReference;

    private EditText txtClase ;
    private EditText txtDia;
    private EditText txtHorario;
    private EditText txtInstructor;
    private ListView listViewClases;

    private final List <Horarios> listHorarios = new ArrayList<>();
    ArrayAdapter<Horarios> arrayAdapterHorarios;
    Horarios horariosSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);
        txtClase =(EditText) findViewById(R.id.txtClase);
        txtDia =(EditText) findViewById(R.id.txtDia);
        txtHorario =(EditText) findViewById(R.id.txtHorario);
        txtInstructor =(EditText) findViewById(R.id.txtInstructor);
        listViewClases =(ListView) findViewById(R.id.listViewClases);



        inicializarBD();
        listarDatos();
        listViewClases.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                horariosSelected= (Horarios) adapterView.getItemAtPosition(i);
                txtClase.setText(horariosSelected.getNombreClase());
                txtDia.setText(horariosSelected.getDiaClase());
                txtHorario.setText(horariosSelected.getHorarioClase());
                txtInstructor.setText(horariosSelected.getInstructor());
            }
        });
    }
    @Override//llamar barra menu
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_horarios,menu);
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
        String txtclase = txtClase.getText().toString();
        String txtdia = txtDia.getText().toString();
        String txthorario = txtHorario.getText().toString();
        String txtinstructor = txtInstructor.getText().toString();
        switch (item.getItemId()){
            case R.id.icono_nuevo:
                if (txtclase.isEmpty()||(txtdia.isEmpty())||(txthorario.isEmpty())||(txtinstructor.isEmpty())){
                    validacion();
                    }
                else {
                    Horarios objHorarios =new Horarios();
                    objHorarios.setNombreClase(txtclase);
                    objHorarios.setDiaClase(txtdia);
                    objHorarios.setHorarioClase(txthorario);
                    objHorarios.setInstructor(txtinstructor);
                    databaseReference.child("Clases").child(objHorarios.getDiaClase()).setValue(objHorarios);
                    Toast.makeText(this, "Registro guardado correctamente", Toast.LENGTH_SHORT).show();
                    limpiarCajas();
                }
                break;
            case R.id.icono_actualizar:

                if(horariosSelected != null){
                    if(validacion()==false){
                        Horarios objHorarios = new Horarios();
                        objHorarios.setNombreClase(txtclase);
                        objHorarios.setDiaClase(txtdia);
                        objHorarios.setHorarioClase(txthorario);
                        objHorarios.setInstructor(txtinstructor);
                        databaseReference.child("Clases").child(objHorarios.getDiaClase()).setValue(objHorarios);
                        Toast.makeText(this, "Actualizado Correctamente", Toast.LENGTH_SHORT).show();
                        horariosSelected = null;
                    }
                }else{
                    Toast.makeText(this, "Seleccione un registro de nuevo", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.icono_regresar:
                Toast.makeText(this, "Regresar al menu", Toast.LENGTH_SHORT).show();
                Intent btnRegresar = new Intent(this,MainActivity.class);
                startActivity(btnRegresar);
                break;
            default:
                break;
            case R.id.icono_eliminar:
                if(horariosSelected != null){
                    Horarios objHorarios =new Horarios();
                    objHorarios.setNombreClase(horariosSelected.getNombreClase());
                    databaseReference.child("Clases").child(objHorarios.getNombreClase()).removeValue();
                    horariosSelected = null;
                    Toast.makeText(this, "Eliminado Correctamente", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Seleccione una clase para eliminar", Toast.LENGTH_SHORT).show();
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    public void limpiarCajas(){
        txtClase.setText("");
        txtDia.setText("");
        txtHorario.setText("");
        txtInstructor.setText("");
    }
    private boolean validacion(){
        String txtclase = txtClase.getText().toString();
        String txtdia = txtDia.getText().toString();
        String txthorario = txtHorario.getText().toString();
        String txtinstructor = txtInstructor.getText().toString();
        if(txtclase.isEmpty()){
            this.txtClase.setError("Requerido");
            return true;
        }
        else if(txtdia.isEmpty()){
            this.txtDia.setError("Requerido");
            return true;
        }
        else if(txthorario.isEmpty()){
            this.txtHorario.setError("Requerido");
            return true;
        }
        else if (txtinstructor.isEmpty()){
            this.txtInstructor.setError("Requerido");
            return true;
        }else{
            return false;
    }
    }
    public void listarDatos(){
        databaseReference.child("Clases").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listHorarios.clear();
                for (DataSnapshot objSnapshot: snapshot.getChildren()){
                    Horarios e= objSnapshot.getValue(Horarios.class);
                    listHorarios.add(e);
                    arrayAdapterHorarios=new ArrayAdapter<Horarios>(horariosActivity.this, android.R.layout.simple_list_item_1,listHorarios);
                    listViewClases.setAdapter(arrayAdapterHorarios);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
























