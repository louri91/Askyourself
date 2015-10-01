package com.ds.louri.askyourself;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.ds.louri.askyourself.util.Partida;

public class falloPregunta extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fallo_pregunta);

        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        alertbox.setTitle("¡Has fallado!");
        String respuestaCorrecta = Partida.getListadoTodasPreguntas().get(Partida.getListaPeque().get(Partida.getPosicion()-1)).getRespuestaCorrecta();
        alertbox.setMessage("La respuesta correcta era: " +respuestaCorrecta+"\n¿Qué quieres hacer ahora?");
        alertbox.setPositiveButton("Volver al Inicio", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(nextScreen);
            }
        });

        alertbox.setNegativeButton("Continuar partida", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent nextScreen = new Intent(getApplicationContext(), question.class);
                startActivity(nextScreen);
            }
        });
        alertbox.setCancelable(false);
        alertbox.show();
    }

    @Override
    public void onBackPressed(){
        Intent nextScreen = new Intent(getApplicationContext(), question.class);
        startActivity(nextScreen);
    }


}
