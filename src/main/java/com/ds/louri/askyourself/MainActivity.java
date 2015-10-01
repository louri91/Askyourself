package com.ds.louri.askyourself;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.content.*;

import com.ds.louri.askyourself.util.Partida;

public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button otrosJuegos = (Button)findViewById(R.id.button3);
        otrosJuegos.setOnClickListener(new View.OnClickListener(){

            public void onClick(View arg0){
                otrosJuegos.setBackgroundResource(getRecurso(MainActivity.this, "rounded_yellow"));
                Intent nextScreen = new Intent(getApplicationContext(),otrosJuegos.class);
                startActivity(nextScreen);
            }
        });

        final Button question = (Button)findViewById(R.id.button);
        question.setOnClickListener(new View.OnClickListener(){

            public void onClick(View arg0){
                question.setBackgroundResource(getRecurso(MainActivity.this, "rounded_yellow"));
                Intent nextScreen = new Intent(getApplicationContext(),temas.class);
                startActivity(nextScreen);
            }
        });

        final Button results = (Button)findViewById(R.id.button2);
        results.setOnClickListener(new View.OnClickListener(){

            public void onClick(View arg0){
                results.setBackgroundResource(getRecurso(MainActivity.this, "rounded_yellow"));
                Intent nextScreen = new Intent(getApplicationContext(),resultados.class);
                startActivity(nextScreen);
            }
        });

    }

    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    private int getRecurso(Context context, String ruta) {
        return context.getResources().getIdentifier("drawable/"+ruta,null, context.getPackageName());
    }



}
