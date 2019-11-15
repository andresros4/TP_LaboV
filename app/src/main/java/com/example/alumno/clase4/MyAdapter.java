package com.example.alumno.clase4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Handler;


import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Trago> tragos;
    private MyViewHolder holder;
    private MainActivity mainActivity;


    public MyAdapter(List<Trago> tragos, MainActivity mainActivity) {
        this.tragos = tragos;
        this.mainActivity = mainActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v, this.mainActivity);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        this.holder = holder;
        Trago t = this.tragos.get(position);
        holder.posicion = position;
        holder.tvNombre.setText(t.getNombre());
        String ingre = "";
        for(int i = 0; i<t.getIngredientes().size();i++){
            if(i==t.getIngredientes().size()-1) {
                ingre += t.getIngredientes().get(i);
            } else {
                ingre += t.getIngredientes().get(i) + ", ";
            }
        }

        holder.tvIngredientes.setText(ingre);

        if (t.getImagen() == null) {
            Handler handler = new Handler(mainActivity);
            MiHilo hilo = new MiHilo(handler);
            hilo.setName("Imagen");
            hilo.setPosicion(position);
            hilo.start();
        } else {
            Bitmap bmp = BitmapFactory.decodeByteArray(t.getImagen(), 0, t.getImagen().length);
            holder.imagen.setImageBitmap(bmp);
        }

    }

    @Override
    public int getItemCount() {
        return this.tragos.size();
    }

    public List<Trago> getTragos() {
        return tragos;
    }

    public void setTragos(List<Trago> tragos) {
        this.tragos = tragos;
    }

}
