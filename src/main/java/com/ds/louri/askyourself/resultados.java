package com.ds.louri.askyourself;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.ds.louri.askyourself.util.Partida;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class resultados extends Activity {
    private String date;
    private String ac;
    private String fall;
    private String porc;
    private TableLayout tb;
    private TextView fecha;
    private TextView aciertos;
    private TextView fallos;
    private TextView porcentaje;
    private TableRow row;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        tb = (TableLayout)findViewById(R.id.tl);
        Drawable d = getResources().getDrawable(R.drawable.results);
        d.setAlpha(170);
        tb.setBackgroundDrawable(d);

        leerResultados();
    }

    @Override
    public void onBackPressed(){
        Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(nextScreen);
    }

    private void leerResultados()
    {
        String[] resultado;
        Scanner sc = null;
        try {
            sc = new Scanner(this.getApplicationContext().openFileInput("resultados.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(sc == null){
            pintarLinea("","","","");
        }
        else {
            while (sc.hasNextLine()) {
                resultado = sc.nextLine().split(";");
                date = resultado[0];
                ac = resultado[1];
                fall = resultado[2];
                porc = resultado[3];
                pintarLinea(date, ac, fall, porc);
            }
        }

    }


    private void pintarLinea(String date, String ac, String fall, String porc){
        fecha = new TextView(getApplicationContext());
        aciertos = new TextView(getApplicationContext());
        fallos = new TextView(getApplicationContext());
        porcentaje = new TextView(getApplicationContext());
        TableRow.LayoutParams params1 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT,1.0f);
        TableRow.LayoutParams params2 = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT);
        row = new TableRow(this);
        if(date!=""){
            fecha.setText(date);
            fecha.setTextColor(Color.BLACK);
            fecha.setAlpha(1);
            fecha.setPadding(20, 20, 20, 20);
            fecha.setGravity(Gravity.CENTER);
            aciertos.setText(ac);
            aciertos.setAlpha(1);
            aciertos.setPadding(20, 20, 20, 20);
            aciertos.setGravity(Gravity.CENTER);
            aciertos.setTextColor(Color.BLACK);


            fallos.setText(fall);
            fallos.setAlpha(1);
            fallos.setGravity(Gravity.CENTER);
            fallos.setPadding(20, 20, 20, 20);
            fallos.setTextColor(Color.BLACK);


            Double i = Double.parseDouble(porc);
            porcentaje.setText(i.intValue() + "%");
            if (i >= 50.0) {
                porcentaje.setTextColor(Color.rgb(62, 156, 137));
            } else {
                porcentaje.setTextColor(Color.RED);
            }
            porcentaje.setAlpha(1);
            porcentaje.setGravity(Gravity.CENTER);
            porcentaje.setPadding(20, 20, 20, 20);

            fecha.setLayoutParams(params1);
            aciertos.setLayoutParams(params1);
            fallos.setLayoutParams(params1);
            porcentaje.setLayoutParams(params1);
            row.addView(fecha);
            row.addView(aciertos);
            row.addView(fallos);
            row.addView(porcentaje);
            row.setLayoutParams(params2);
            tb.addView(row);
        }
    }
}
