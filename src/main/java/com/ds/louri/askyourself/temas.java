package com.ds.louri.askyourself;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ds.louri.askyourself.util.DBHelper;
import com.ds.louri.askyourself.util.Partida;
import com.ds.louri.askyourself.util.Pregunta;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class temas extends Activity {

    Partida partida;
    public static Context contexto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temas);
        contexto = this.getApplicationContext();

        partida = new Partida();

        final Button historia = (Button)findViewById(R.id.historia);
        historia.setOnClickListener(new View.OnClickListener(){

            public void onClick(View arg0){
                    partida.seleccionarTema("historia");
                historia.setBackgroundResource(getRecurso(temas.this, "rounded_yellow"));
                Intent nextScreen = new Intent(getApplicationContext(),question.class);
                startActivity(nextScreen);
            }
        });

        final Button arte = (Button)findViewById(R.id.arte);
        arte.setOnClickListener(new View.OnClickListener(){

            public void onClick(View arg0){
                partida.seleccionarTema("arte");
                arte.setBackgroundResource(getRecurso(temas.this, "rounded_yellow"));
                Intent nextScreen = new Intent(getApplicationContext(),question.class);
                startActivity(nextScreen);
            }
        });

        final Button deporte = (Button)findViewById(R.id.deportes);
        deporte.setOnClickListener(new View.OnClickListener(){

            public void onClick(View arg0){
                partida.seleccionarTema("deporte");
                deporte.setBackgroundResource(getRecurso(temas.this, "rounded_yellow"));
                Intent nextScreen = new Intent(getApplicationContext(),question.class);
                startActivity(nextScreen);
            }
        });

        final Button ciencia = (Button)findViewById(R.id.ciencia);
        ciencia.setOnClickListener(new View.OnClickListener(){

            public void onClick(View arg0){
                partida.seleccionarTema("ciencia");
                ciencia.setBackgroundResource(getRecurso(temas.this, "rounded_yellow"));
                Intent nextScreen = new Intent(getApplicationContext(),question.class);
                startActivity(nextScreen);
            }
        });

        final Button entretenimiento = (Button)findViewById(R.id.entretenimiento);
        entretenimiento.setOnClickListener(new View.OnClickListener(){

            public void onClick(View arg0){
                partida.seleccionarTema("entretenimiento");
                entretenimiento.setBackgroundResource(getRecurso(temas.this, "rounded_yellow"));
                Intent nextScreen = new Intent(getApplicationContext(),question.class);
                startActivity(nextScreen);
            }
        });

        final Button geografia = (Button)findViewById(R.id.geografia);
        geografia.setOnClickListener(new View.OnClickListener(){

            public void onClick(View arg0){
                partida.seleccionarTema("geografia");
                geografia.setBackgroundResource(getRecurso(temas.this, "rounded_yellow"));
                Intent nextScreen = new Intent(getApplicationContext(),question.class);
                startActivity(nextScreen);
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(nextScreen);
    }

    private int getRecurso(Context context, String ruta) {
        return context.getResources().getIdentifier("drawable/"+ruta,null, context.getPackageName());
    }



}
