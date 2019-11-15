package com.example.alumno.clase4;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by alumno on 12/09/2019.
 */

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public View v;
    public TextView tvNombre;
    public TextView tvIngredientes;
    public ImageView imagen;
    private MainActivity mainActivity;

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int posicion;
    //la dejamos public para acceder facil y no hacer los getters y setters

    public MyViewHolder(View itemView, MainActivity mainActivity) {
        super(itemView);
        this.v = itemView;
        this.tvNombre = (TextView) itemView.findViewById(R.id.tvNombre);
        this.tvIngredientes = (TextView) itemView.findViewById(R.id.tvIngredientes);
        this.imagen = (ImageView) itemView.findViewById(R.id.ivFoto);
        this.mainActivity=mainActivity;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
         this.mainActivity.mostrarFormulario(this.posicion);
    }
}
