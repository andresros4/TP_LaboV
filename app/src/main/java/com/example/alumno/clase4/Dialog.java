package com.example.alumno.clase4;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


public class Dialog extends DialogFragment implements DialogInterface.OnClickListener{

    private String nombre;
    EditText etApiKey;

    public Dialog(){}

    public Dialog (String nombre){
        this.nombre=nombre;

    }

    public AlertDialog onCreateDialog(Bundle b){
        AlertDialog.Builder build = new AlertDialog.Builder(getActivity());
        AlertDialog ad = null;
        if(this.nombre.equals("Error")) {
            build.setTitle("Error");
            build.setMessage("Ese trago no existe");
            build.setPositiveButton("Volver", null);
            ad = build.create();
        } else if(this.nombre.equals("ApiKey")){
            build.setTitle("Cambiar API Key");
            View v = LayoutInflater.from(this.getContext()).inflate(R.layout.dialog, null);
            etApiKey = (EditText) v.findViewById(R.id.etApiKey);
            SharedPreferences prefs = super.getActivity().getSharedPreferences("miConfig", Context.MODE_PRIVATE);
            etApiKey.setText(prefs.getString("ApiKey","1"));
            build.setMessage("Ingresar key:");
            build.setPositiveButton("Cambiar",this);
            build.setNeutralButton("Volver",null);
            build.setView(v);
            ad = build.create();
        }
        return ad;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(android.support.v7.app.AlertDialog.BUTTON_POSITIVE == which){
           SharedPreferences prefs = super.getActivity().getSharedPreferences("miConfig", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            Log.d("onClick",etApiKey.getText().toString());
            editor.putString("ApiKey",etApiKey.getText().toString());
            editor.commit();

        }


    }
}
