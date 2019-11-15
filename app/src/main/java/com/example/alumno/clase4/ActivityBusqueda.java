package com.example.alumno.clase4;

import android.content.Intent;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityBusqueda extends AppCompatActivity implements View.OnClickListener {


    TextView tvNombre;
    TextView tvInstrucciones;
    TextView tvIngredientes;
    FloatingActionButton btnCompartir;

    private Trago trago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        tvNombre = (TextView) super.findViewById(R.id.tvNombreBuscado);
        tvInstrucciones = (TextView) super.findViewById(R.id.tvInstruccionesBuscado);
        tvIngredientes = (TextView) super.findViewById(R.id.tvIngredientesBuscado);
        btnCompartir = (FloatingActionButton) super.findViewById(R.id.btnCompartirBuscado);
        btnCompartir.setOnClickListener(this);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        Bundle objetoEnviado = getIntent().getExtras();
        Trago t = (Trago) objetoEnviado.getSerializable("trago");

        tvNombre.setText(t.getNombre());
        String ingredientes = "";
        for(String i : t.getIngredientes()){
            ingredientes += i + ", ";
        }
        ingredientes = ingredientes.substring(0,ingredientes.length()-2);
        tvIngredientes.setText(ingredientes);
        tvInstrucciones.setText(t.getInstrucciones());
    }

    @Override
    public void onClick(View v) {
        String strCompartir = tvNombre.getText().toString() + " Instructions: " + tvInstrucciones.getText().toString();
        Intent intentCompartir = new Intent(Intent.ACTION_SEND);
        intentCompartir.setType("text/plain");
        intentCompartir.putExtra(Intent.EXTRA_TEXT, strCompartir);
        startActivity(Intent.createChooser(intentCompartir, "Compartir por:"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            super.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
