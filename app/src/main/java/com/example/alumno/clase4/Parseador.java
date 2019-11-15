package com.example.alumno.clase4;


import android.util.JsonReader;
import android.util.Log;
import android.util.Xml;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alumno on 17/10/2019.
 */

public class Parseador {


    public static List<Trago> encontrarTragos(String s) {

        List<Trago> tragos = new ArrayList<>();
        if(s==null){
            return tragos;
        }
        try {
            JSONObject reader = new JSONObject(s);
            JSONArray jsonArray = reader.getJSONArray("drinks");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String nombre = obj.getString("strDrink");
                String instrucciones = obj.getString("strInstructions");
                String id = obj.getString("idDrink");
                String rutaImagen = obj.getString("strDrinkThumb");

                List<String> ingredientes = new ArrayList<String>();
                for (int j = 1; j < 15; j++) {
                    if (!(obj.getString("strIngredient" + j).equals("null"))) {
                        ingredientes.add(obj.getString("strIngredient" + j));
                    }
                }

                Trago t = new Trago(nombre, instrucciones, ingredientes, Integer.parseInt(id), rutaImagen);
                tragos.add(t);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tragos;
    }


    public static Trago busquedaPorNombre(String s, String nombreBuscado) {
        Trago t = null;
        if (s != null) {
            nombreBuscado = nombreBuscado.replace(" ", "");
            try {
                JSONObject reader = new JSONObject(s);
                JSONArray jsonArray = reader.getJSONArray("drinks");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    String nombre = obj.getString("strDrink");
                    String nombreSinEspacios = nombre.replace(" ", "");
                    if (nombreSinEspacios.toUpperCase().equals(nombreBuscado.toUpperCase())) {
                        String instrucciones = obj.getString("strInstructions");
                        String id = obj.getString("idDrink");

                        List<String> ingredientes = new ArrayList<String>();
                        for (int j = 1; j < 15; j++) {
                            if (!(obj.getString("strIngredient" + j).equals("null"))) {
                                ingredientes.add(obj.getString("strIngredient" + j));
                            }
                        }


                        t = new Trago(nombre, instrucciones, ingredientes, Integer.parseInt(id));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return t;
    }
}
