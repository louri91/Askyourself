package com.ds.louri.askyourself;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.ds.louri.askyourself.util.Partida;
import com.ds.louri.askyourself.util.Resultados;


public class FinPartida extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_partida);

        Resultados.guardarResultados(this);

        double re;
        int fal;

        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        alertbox.setTitle("¡Has terminado la partida!");
        re = (((Partida.getAciertos())/(15.0))*100.0);
        fal = ((Partida.getPosicion())-Partida.getAciertos());

        alertbox.setMessage("Aciertos: "+ Partida.getAciertos()+ "\nFallos: "+fal+"\n¡Has acertado un: "+re+ "%!");
        alertbox.setPositiveButton("Volver al Inicio", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(nextScreen);
            }
        });

        alertbox.setNegativeButton("Ver Resultados", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent nextScreen = new Intent(getApplicationContext(), resultados.class);
                startActivity(nextScreen);
            }
        });
        alertbox.setCancelable(false);
        alertbox.show();
    }

    @Override
    public void onBackPressed(){
        Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(nextScreen);
    }



}
