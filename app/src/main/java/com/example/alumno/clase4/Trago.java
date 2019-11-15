package com.example.alumno.clase4;

import java.io.Serializable;
import java.util.List;

public class Trago implements Serializable {

    private String nombre;
    private String instrucciones;
    private List<String> ingredientes;
    private int id;
    private String rutaImagen;
    private byte[] imagen;


    public Trago(String nombre, String instrucciones, List<String> ingredientes, int id){
        this.nombre=nombre;
        this.instrucciones=instrucciones;
        this.ingredientes=ingredientes;
        this.id=id;
    }


    public Trago(String nombre, String instrucciones, List<String> ingredientes, int id, String rutaImagen){
        this.nombre=nombre;
        this.instrucciones=instrucciones;
        this.ingredientes=ingredientes;
        this.id=id;
        this.rutaImagen=rutaImagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }


    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Nombre: " +this.nombre+ " Instrucciones:"+this.instrucciones;
    }
}
