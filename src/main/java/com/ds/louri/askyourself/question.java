package com.ds.louri.askyourself;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ds.louri.askyourself.util.*;

public class question extends Activity {

    private int i=0;
    private Button resp1;
    private Button resp2;
    private Button resp3;
    private Button resp4;
    private int red;
    private int green;
    private SoundPool sp;
    private int mode;
    private float volume;
    private MediaPlayer mp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        float actVolume, maxVolume;


        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = actVolume / maxVolume;
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        sp = new SoundPool(2, AudioManager.STREAM_MUSIC,0);
        int id;
        Drawable drawable = null;
        final int sonidoAcierto = sp.load(getApplicationContext(), R.raw.acierto3, 1);
        final int sonidoFallo = sp.load(getApplicationContext(), R.raw.error, 1);
        mode = ((AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE)).getRingerMode();

        red = getRecurso(getApplicationContext(),"rounded_red");
        green = getRecurso(getApplicationContext(),"rounded_green");

        i=Partida.calcularPregunta();

            if(Partida.getListadoTodasPreguntas().get(Partida.getListaPeque().get(i)).getTipoPregunta()==1){
                id = getRecurso(getApplicationContext(),Partida.getListadoTodasPreguntas().get(Partida.getListaPeque().get(i)).getRuta());
                Resources resources= getResources();
                drawable = resources.getDrawable(id);

                double newImageHeight = (drawable.getIntrinsicHeight()*0.8);
                double newImageWidth =  (drawable.getIntrinsicWidth()*0.8);
                drawable.setBounds(0, 0, (int)newImageWidth, (int)newImageHeight);
            }
            if(Partida.getListadoTodasPreguntas().get(Partida.getListaPeque().get(i)).getTipoPregunta()==2){
                id = getRecurso(getApplicationContext(),Partida.getListadoTodasPreguntas().get(Partida.getListaPeque().get(i)).getRuta());
                playMusica(id);
            }


            TextView quest = (TextView)findViewById(R.id.pregunta);
            quest.setText(Partida.getListadoTodasPreguntas().get(Partida.getListaPeque().get(i)).getPregunta());
            quest.setPadding(20,20,20,20);
            if(Partida.getListadoTodasPreguntas().get(Partida.getListaPeque().get(i)).getTipoPregunta()==1){
                quest.setGravity(Gravity.CENTER_HORIZONTAL);
                quest.setCompoundDrawables(null, null, null, drawable);
            }
            resp1 = (Button)findViewById(R.id.respuesta1);
            resp1.setText(Partida.getListadoTodasPreguntas().get(Partida.getListaPeque().get(i)).getRespuesta(0));
            resp1.setOnClickListener(new View.OnClickListener(){

                public void onClick(View arg0){
                    if(Partida.getListadoTodasPreguntas().get(Partida.getListaPeque().get(i)).getTipoPregunta()==2){
                        stopMusica();
                    }
                    resp1.setClickable(false);
                    resp2.setClickable(false);
                    resp3.setClickable(false);
                    resp4.setClickable(false);
                    if(Partida.isTodasRespondidas()){
                        if (resp1.getText() == Partida.getListadoTodasPreguntas().get(Partida.getListaPeque().get(i)).getRespuestaCorrecta()) {
                            mostrarMensajeAcierto();
                            Partida.setAciertos(Partida.getAciertos()+1);
                            if (mode == AudioManager.RINGER_MODE_NORMAL) {
                                sp.play(sonidoAcierto,volume,volume,1,0,1f);
                            }
                            resp1.setBackgroundResource(green);
                        } else {
                            resp1.setBackgroundResource(red);
                            if (mode == AudioManager.RINGER_MODE_NORMAL) {
                                sp.play(sonidoFallo, volume, volume, 1, 0, 1f);
                            }

                        }
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable(){
                            @Override
                            public void run() {
                                Intent nextScreen = new Intent(getApplicationContext(), FinPartida.class);
                                startActivity(nextScreen);

                            }
                        },1200);
                    }
                    else {

                        if (resp1.getText() == Partida.getListadoTodasPreguntas().get(Partida.getListaPeque().get(i)).getRespuestaCorrecta()) {
                            mostrarMensajeAcierto();
                            Partida.setAciertos(Partida.getAciertos()+1);
                            if (mode == AudioManager.RINGER_MODE_NORMAL) {
                                sp.play(sonidoAcierto, volume, volume, 1, 0, 1f);
                            }
                            resp1.setBackgroundResource(green);

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable(){
                                @Override
                                public void run() {
                                    Intent nextScreen = new Intent(getApplicationContext(), question.class);
                                    startActivity(nextScreen);
                                }
                            },1200);
                        } else {
                            if (mode == AudioManager.RINGER_MODE_NORMAL) {
                                sp.play(sonidoFallo, volume, volume, 1, 0, 1f);
                            }
                            resp1.setBackgroundResource(red);

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable(){
                                @Override
                                public void run() {
                                    Intent nextScreen = new Intent(getApplicationContext(), falloPregunta.class);
                                    startActivity(nextScreen);
                                }
                            },1200);

                        }

                    }

                }

            });

            resp2 = (Button)findViewById(R.id.respuesta2);
            resp2.setText(Partida.getListadoTodasPreguntas().get(Partida.getListaPeque().get(i)).getRespuesta(1));
            resp2.setOnClickListener(new View.OnClickListener(){

                public void onClick(View arg0){
                    if(Partida.getListadoTodasPreguntas().get(Partida.getListaPeque().get(i)).getTipoPregunta()==2){
                        stopMusica();
                    }
                    resp1.setClickable(false);
                    resp2.setClickable(false);
                    resp3.setClickable(false);
                    resp4.setClickable(false);
                    if(Partida.isTodasRespondidas()){
                        if (resp2.getText() == Partida.getListadoTodasPreguntas().get(Partida.getListaPeque().get(i)).getRespuestaCorrecta()) {
                            mostrarMensajeAcierto();
                            Partida.setAciertos(Partida.getAciertos()+1);
                            if (mode == AudioManager.RINGER_MODE_NORMAL) {
                                sp.play(sonidoAcierto, volume, volume, 1, 0, 1f);
                            }
                            resp2.setBackgroundResource(green);
                        } else {
                            if (mode == AudioManager.RINGER_MODE_NORMAL) {
                                sp.play(sonidoFallo, volume, volume, 1, 0, 1f);
                            }
                            resp2.setBackgroundResource(red);

                        }
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable(){
                            @Override
                            public void run() {
                                Intent nextScreen = new Intent(getApplicationContext(), FinPartida.class);
                                startActivity(nextScreen);
                            }
                        },1200);
                    }
                    else {

                        if (resp2.getText() == Partida.getListadoTodasPreguntas().get(Partida.getListaPeque().get(i)).getRespuestaCorrecta()) {
                            mostrarMensajeAcierto();
                            Partida.setAciertos(Partida.getAciertos()+1);
                            if (mode == AudioManager.RINGER_MODE_NORMAL) {
                                sp.play(sonidoAcierto, volume, volume, 1, 0, 1f);
                            }
                            resp2.setBackgroundResource(green);
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable(){
                                @Override
                                public void run() {
                                    Intent nextScreen = new Intent(getApplicationContext(), question.class);
                                    startActivity(nextScreen);
                                }
                            },1200);
                        } else {
                            if (mode == AudioManager.RINGER_MODE_NORMAL) {
                                sp.play(sonidoFallo, volume, volume, 1, 0, 1f);
                            }
                            resp2.setBackgroundResource(red);

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable(){
                                @Override
                                public void run() {
                                    Intent nextScreen = new Intent(getApplicationContext(), falloPregunta.class);
                                    startActivity(nextScreen);
                                }
                            },1200);

                        }

                    }

                }
            });
            resp3 = (Button)findViewById(R.id.respuesta3);
            resp3.setText(Partida.getListadoTodasPreguntas().get(Partida.getListaPeque().get(i)).getRespuesta(2));
            resp3.setOnClickListener(new View.OnClickListener(){

                public void onClick(View arg0){
                    if(Partida.getListadoTodasPreguntas().get(Partida.getListaPeque().get(i)).getTipoPregunta()==2){
                        stopMusica();
                    }
                    resp1.setClickable(false);
                    resp2.setClickable(false);
                    resp3.setClickable(false);
                    resp4.setClickable(false);
                    if(Partida.isTodasRespondidas()){
                        if (resp3.getText() == Partida.getListadoTodasPreguntas().get(Partida.getListaPeque().get(i)).getRespuestaCorrecta()) {
                            mostrarMensajeAcierto();
                            Partida.setAciertos(Partida.getAciertos()+1);
                            if (mode == AudioManager.RINGER_MODE_NORMAL) {
                                sp.play(sonidoAcierto, volume, volume, 1, 0, 1f);
                            }
                            resp3.setBackgroundResource(green);
                        } else {
                            if (mode == AudioManager.RINGER_MODE_NORMAL) {
                                sp.play(sonidoFallo, volume, volume, 1, 0, 1f);
                            }
                            resp3.setBackgroundResource(red);

                        }
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable(){
                            @Override
                            public void run() {
                                Intent nextScreen = new Intent(getApplicationContext(), FinPartida.class);
                                startActivity(nextScreen);
                            }
                        },1200);
                    }
                    else {

                        if (resp3.getText() == Partida.getListadoTodasPreguntas().get(Partida.getListaPeque().get(i)).getRespuestaCorrecta()) {
                            mostrarMensajeAcierto();
                            Partida.setAciertos(Partida.getAciertos()+1);
                            if (mode == AudioManager.RINGER_MODE_NORMAL) {
                                sp.play(sonidoAcierto, volume, volume, 1, 0, 1f);
                            }

                            resp3.setBackgroundResource(green);
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable(){
                                @Override
                                public void run() {
                                    Intent nextScreen = new Intent(getApplicationContext(), question.class);
                                    startActivity(nextScreen);
                                }
                            },1200);
                        } else {
                            if (mode == AudioManager.RINGER_MODE_NORMAL) {
                                sp.play(sonidoFallo, volume, volume, 1, 0, 1f);
                            }
                            resp3.setBackgroundResource(red);

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable(){
                                @Override
                                public void run() {
                                    Intent nextScreen = new Intent(getApplicationContext(), falloPregunta.class);
                                    startActivity(nextScreen);
                                }
                            },1200);

                        }

                    }

                }
            });
            resp4 = (Button)findViewById(R.id.respuesta4);
            resp4.setText(Partida.getListadoTodasPreguntas().get(Partida.getListaPeque().get(i)).getRespuesta(3));
            resp4.setOnClickListener(new View.OnClickListener(){

                public void onClick(View arg0){
                    if(Partida.getListadoTodasPreguntas().get(Partida.getListaPeque().get(i)).getTipoPregunta()==2){
                        stopMusica();
                    }
                    resp1.setClickable(false);
                    resp2.setClickable(false);
                    resp3.setClickable(false);
                    resp4.setClickable(false);
                    if(Partida.isTodasRespondidas()){
                        if (resp4.getText() == Partida.getListadoTodasPreguntas().get(Partida.getListaPeque().get(i)).getRespuestaCorrecta()) {
                            mostrarMensajeAcierto();
                            Partida.setAciertos(Partida.getAciertos()+1);
                            if (mode == AudioManager.RINGER_MODE_NORMAL) {
                                sp.play(sonidoAcierto, volume, volume, 1, 0, 1f);
                            }
                            resp4.setBackgroundResource(green);
                        } else {
                            if (mode == AudioManager.RINGER_MODE_NORMAL) {
                                sp.play(sonidoFallo, volume, volume, 1, 0, 1f);
                            }
                            resp4.setBackgroundResource(red);

                        }
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable(){
                            @Override
                            public void run() {
                                Intent nextScreen = new Intent(getApplicationContext(), FinPartida.class);
                                startActivity(nextScreen);
                            }
                        },1200);
                    }
                    else {
                        if (resp4.getText() == Partida.getListadoTodasPreguntas().get(Partida.getListaPeque().get(i)).getRespuestaCorrecta()) {
                            mostrarMensajeAcierto();
                            Partida.setAciertos(Partida.getAciertos()+1);
                            if (mode == AudioManager.RINGER_MODE_NORMAL) {
                                sp.play(sonidoAcierto, volume, volume, 1, 0, 1f);
                            }
                            resp4.setBackgroundResource(green);
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable(){
                                @Override
                                public void run() {
                                    Intent nextScreen = new Intent(getApplicationContext(), question.class);
                                    startActivity(nextScreen);
                                }
                            },1200);

                        } else {
                            if (mode == AudioManager.RINGER_MODE_NORMAL) {
                                sp.play(sonidoFallo, volume, volume, 1, 0, 1f);
                            }
                            resp4.setBackgroundResource(red);

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable(){
                                @Override
                                public void run() {
                                    Intent nextScreen = new Intent(getApplicationContext(), falloPregunta.class);
                                    startActivity(nextScreen);
                                }
                            },1200);

                        }

                    }
                }
            });
        }

    @Override
    public void onBackPressed() {

        final AlertDialog.Builder alertbox = new AlertDialog.Builder(question.this);
        alertbox.setTitle("Estás a punto de salir sin terminar la partida");
        alertbox.setMessage("¿Seguro que quieres terminar la partida?");
        alertbox.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent nextScreen = new Intent(getApplicationContext(), FinPartida.class);
                startActivity(nextScreen);
            }
        });

        alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                arg0.dismiss();
            }
        });
        alertbox.setCancelable(false);
        alertbox.show();
    }

    private int getRecurso(Context context, String ruta) {
        return context.getResources().getIdentifier("drawable/"+ruta,null, context.getPackageName());
    }

    private void mostrarMensajeAcierto(){
                Toast toast;
                toast = Toast.makeText(getApplicationContext(),"¡Has acertado!", Toast.LENGTH_SHORT);
                toast.show();
    }

    private void playMusica(int id){
        mp = MediaPlayer.create(this,id);
        mp.setLooping(true);
        mp.start();

    }
    private void stopMusica(){
        mp.stop();
        mp.release();
    }



}
