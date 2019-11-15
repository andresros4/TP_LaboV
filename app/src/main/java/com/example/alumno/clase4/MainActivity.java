package com.example.alumno.clase4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Handler.Callback, SearchView.OnQueryTextListener {

    static List<Trago> tragos;
    String apiKey;
    MyAdapter adapter;
    Handler handler;
    public static Integer tragoSeleccionado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> ingredientes = new ArrayList<String>();
        tragos = new ArrayList<Trago>();


        handler = new Handler(this);

        SharedPreferences prefs = getSharedPreferences("miConfig", Context.MODE_PRIVATE);


        apiKey = prefs.getString("ApiKey","1");
        Log.d("Api",apiKey);
        MiHilo hilo = new MiHilo(handler, apiKey);
        hilo.setName("Primero");
        hilo.start();


        RecyclerView rv = (RecyclerView) super.findViewById(R.id.rvPersonas);
        adapter = new MyAdapter(tragos, this);
        rv.setAdapter(adapter);

        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean handleMessage(Message msg) {

        if (msg.arg1 == 0) {

            tragos = (List<Trago>) msg.obj;
            if(tragos.isEmpty()){
                Dialog d = new Dialog("Error");
                d.show(getSupportFragmentManager(),"onCreate");
            }
            adapter.setTragos(tragos);
            adapter.notifyDataSetChanged();
        } else if (msg.arg1 == 1) {
            byte[] img = (byte[]) msg.obj;
            tragos.get(msg.arg2).setImagen(img);
            adapter.notifyItemChanged(msg.arg2);
        } else if (msg.arg1 == 3) {
            if ( msg.obj != null) {
                Trago t = (Trago) msg.obj;
                Intent i = new Intent(this, ActivityBusqueda.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("trago", t);
                i.putExtras(bundle);
                startActivity(i);
            } else {
                Dialog d = new Dialog("Error");
                d.show(getSupportFragmentManager(),"onCreate");
            }
        }

        return false;
    }

    public void mostrarFormulario(int posicion) {

        Intent i = new Intent(this, ActivityBusqueda.class);
        Bundle bundle = new Bundle();
        Trago t = tragos.get(posicion);
        bundle.putSerializable("trago", t);
        i.putExtras(bundle);
        startActivity(i);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.campo_buscar);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        MiHilo h = new MiHilo(this.handler, query, apiKey);
        h.setName("BusquedaNombre");
        h.start();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Dialog d = new Dialog("ApiKey");
        d.show(getSupportFragmentManager(),"onCreate");
        return super.onOptionsItemSelected(item);
    }
}
