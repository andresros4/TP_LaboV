package com.example.alumno.clase4;

import android.os.Handler;
import android.os.Message;


public class MiHilo extends Thread {

    private Handler handler;
    private int posicion;
    String query;
    String clave;

    public MiHilo(Handler handler) {
        this.handler = handler;

    }

    public MiHilo(Handler handler, String clave) {
        this.handler = handler;
        this.clave=clave;
    }


    public MiHilo(Handler handler, String query , String clave) {
        this.handler = handler;
        this.query = query;
        this.clave=clave;
    }


    @Override
    public void run() {
        HttpManager httpManager = new HttpManager(clave);
        Parseador parseador = new Parseador();
        Message message = new Message();

        if (this.getName().equals("Primero")) {
            message.obj = parseador.encontrarTragos(httpManager.obtenerTrago());
            handler.sendMessage(message);
            message.arg1 = 0;
        } else if (this.getName().equals("Imagen")) {
            message.obj = httpManager.obtenerImagen(MainActivity.tragos.get(this.posicion).getRutaImagen());
            message.arg1 = 1;
            message.arg2 = posicion;
            handler.sendMessage(message);
        } else if (this.getName().equals("BusquedaNombre")) {
            message.obj = parseador.busquedaPorNombre(httpManager.buscarTrago(this.query), this.query);
            message.arg1 = 3;
            handler.sendMessage(message);

        }
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }


}

