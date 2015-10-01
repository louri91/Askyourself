package com.ds.louri.askyourself.util;


import android.content.Context;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.util.Date;

public class Resultados {

    public static void guardarResultados(Context context){

        FileOutputStream fos;
        try {
            fos = context.openFileOutput("resultados.txt", Context.MODE_APPEND);
            OutputStreamWriter out = new OutputStreamWriter(fos);
            DateFormat dateFormat = DateFormat.getDateInstance();
            Date date = new Date();
            double re = ((Partida.getAciertos()) / (15.0) * 100.0);
            out.write(dateFormat.format(date) + ";" + Partida.getAciertos() + ";" + (Partida.getPosicion() - Partida.getAciertos()) + ";" + (int)re + ";\n");

            out.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


}

