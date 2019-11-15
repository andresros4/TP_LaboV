package com.example.alumno.clase4;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HttpManager {

    String clave;

    public HttpManager(String clave){
        this.clave=clave;
    }

    public String obtenerTrago(){ //

        try {
            URL url= new URL("https://www.thecocktaildb.com/api/json/v1/"+clave+"/search.php?s=martini"); //
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            byte[] resultado;
            connection.setRequestMethod("GET");
            //     connection.setConnectTimeout(500); esto se haria si tuviesemos q procesar mucha informacion
            connection.connect(); // nos conectamos al servidor

            if(connection.getResponseCode() == 200){
                //empezamos a procesar la informacion
                InputStream is = connection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1000];
                int cant=0;
                while(  (cant=is.read(buffer, 0,1000)) > -1){
                    baos.write(buffer,0,cant);
                }
                resultado = baos.toByteArray();
                is.close();
                return new String(resultado);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public byte[] obtenerImagen(String ruta){

        try {
            URL url= new URL(ruta);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            byte[] resultado;
            connection.setRequestMethod("GET");
            connection.connect(); // nos conectamos al servidor

            if(connection.getResponseCode() == 200){

                //empezamos a procesar la informacion
                InputStream is = connection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1000];
                int cant=0;
                while(  (cant=is.read(buffer, 0,1000)) > -1){
                    baos.write(buffer,0,cant);
                }
                resultado = baos.toByteArray();
                is.close();
                return resultado;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    public String buscarTrago(String query){

        try {
            URL url= new URL("https://www.thecocktaildb.com/api/json/v1/"+clave+"/search.php?s="+query);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            byte[] resultado;
            connection.setRequestMethod("GET");
            //     connection.setConnectTimeout(500); esto se haria si tuviesemos q procesar mucha informacion
            connection.connect(); // nos conectamos al servidor

            if(connection.getResponseCode() == 200){
                //empezamos a procesar la informacion
                InputStream is = connection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1000];
                int cant=0;
                while(  (cant=is.read(buffer, 0,1000)) > -1){
                    baos.write(buffer,0,cant);
                }
                resultado = baos.toByteArray();
                is.close();
                return new String(resultado);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
