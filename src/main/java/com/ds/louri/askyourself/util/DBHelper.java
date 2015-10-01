package com.ds.louri.askyourself.util;

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.*;
import java.util.*;
import android.util.*;

import com.ds.louri.askyourself.R;

public class DBHelper extends SQLiteOpenHelper{

    //Versión de la BD
    private static final int DATABASE_VERSION = 21;
    //Nombre de la BD
    private static final String DATABASE_NAME = "pregunta.db";

    private static DBHelper db = null;

    public static DBHelper getInstance(Context context){
        if(db == null){
            db = new DBHelper(context);
            LecturaDeFicheroBD(context);
            return db;
        }
        else{
            return db;
        }
    }

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String inserta_tabla = "CREATE TABLE pregunta "+
                "(tipoPregunta INT NOT NULL, " +
                "question VARCHAR(250) NOT NULL PRIMARY KEY, " +
                "correcta VARCHAR(250) NOT NULL, " +
                "falsa1 VARCHAR(250) NOT NULL, " +
                "falsa2 VARCHAR(250) NOT NULL, " +
                "falsa3 VARCHAR(250) NOT NULL, " +
                "theme VARCHAR(50) NOT NULL, " +
                "ruta VARCHAR(250))";

        db.execSQL(inserta_tabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS pregunta");
        onCreate(db);
    }

    public void insertarPregunta(Pregunta pregunta){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("tipoPregunta", pregunta.getTipoPregunta());
        values.put("question", pregunta.getPregunta());
        values.put("correcta", pregunta.getRespuestaCorrecta());
        values.put("falsa1", pregunta.getRespuestaFalsa1());
        values.put("falsa2", pregunta.getRespuestaFalsa2());
        values.put("falsa3", pregunta.getRespuestaFalsa3());
        values.put("theme", pregunta.getTema());
        values.put("ruta",pregunta.getRuta());
        // Inserting Row
        db.insert("pregunta", null, values);
        Log.d("debug", "Pregunta insertada a las "+new Date().toString());
        db.close(); // Closing database connection
    }

    public Map<Integer,Pregunta> getAll() {
        Map<Integer,Pregunta> listaPreguntas = new HashMap<Integer,Pregunta>();
        Integer posicion = 0;

        String selectQuery = "SELECT  * FROM pregunta";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery(selectQuery, null);
        if(c==null){
        }
        if(c.moveToFirst()==false){
        }
        if (c.moveToFirst()) {
            do {
                Pregunta p = new Pregunta();
                p.setTipoPregunta(Integer.parseInt(c.getString(0)));
                p.setPregunta(c.getString(1));
                p.setRespuestaCorrecta(c.getString(2));
                p.setRespuestaFalsa1(c.getString(3));
                p.setRespuestaFalsa2(c.getString(4));
                p.setRespuestaFalsa3(c.getString(5));
                p.setTema(c.getString(6));
                p.setRuta(c.getString(7));
                p.mezclarRespuestas();

                listaPreguntas.put(posicion,p);
                posicion++;
            } while (c.moveToNext());
        }
        c.close();
        db.close();

        return listaPreguntas;
    }

    public static void LecturaDeFicheroBD(Context context)
    {
        int tipoPregunta;
        String question;
        String correcta;
        String falsa1;
        String falsa2;
        String falsa3;
        String ruta;
        String tema;
        DBHelper bd = DBHelper.getInstance(context);
        String[] pregunta;
        Scanner sc = new Scanner(context.getResources().openRawResource(R.raw.database));
        while(sc.hasNextLine()) {
            pregunta = sc.nextLine().split(";");
            tipoPregunta = Integer.parseInt(pregunta[0]);
            question = pregunta[1];
            correcta = pregunta[2];
            falsa1 = pregunta[3];
            falsa2 = pregunta[4];
            falsa3 = pregunta[5];
            tema = pregunta[6];
            ruta = pregunta[7];
            bd.insertarPregunta(new Pregunta(tipoPregunta,question,correcta,falsa1,falsa2,falsa3,tema,ruta));
        }
    }
}
