package com.ds.louri.askyourself.util;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Pregunta {

    private int tipoPregunta;
    private String tema;
    private String pregunta;
    private String ruta;
    private String respuestaCorrecta;
    private String respuestaFalsa1;
    private String respuestaFalsa2;
    private String respuestaFalsa3;
    private List<String> respuestas = new ArrayList<String>();


    public Pregunta(){
    }

    public Pregunta(int tipoPregunta, String pregunta, String respuestaCorrecta, String respuestaFalsa1, String respuestaFalsa2, String respuestaFalsa3, String tema){
        this.tipoPregunta = tipoPregunta;
        this.pregunta = pregunta;
        this.respuestaCorrecta = respuestaCorrecta;
        this.respuestaFalsa1 = respuestaFalsa1;
        this.respuestaFalsa2 = respuestaFalsa2;
        this.respuestaFalsa3 = respuestaFalsa3;
        this.tema = tema;
    }

    public Pregunta(int tipoPregunta, String pregunta, String respuestaCorrecta, String respuestaFalsa1, String respuestaFalsa2, String respuestaFalsa3, String tema, String ruta){

        if(tipoPregunta==1 | tipoPregunta==2){
            this.ruta = ruta;
        }

        this.tipoPregunta = tipoPregunta;
        this.pregunta = pregunta;
        this.respuestaCorrecta = respuestaCorrecta;
        this.respuestaFalsa1 = respuestaFalsa1;
        this.respuestaFalsa2 = respuestaFalsa2;
        this.respuestaFalsa3 = respuestaFalsa3;
        this.tema = tema;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public int getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(int tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getRespuesta(int i){
        return respuestas.get(i);
    }
    public void mezclarRespuestas(){
        respuestas.add(respuestaCorrecta);
        respuestas.add(respuestaFalsa1);
        respuestas.add(respuestaFalsa2);
        respuestas.add(respuestaFalsa3);
        Collections.shuffle(respuestas);
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public String getRespuestaFalsa1() {
        return respuestaFalsa1;
    }

    public void setRespuestaFalsa1(String respuestaFalsa1) {
        this.respuestaFalsa1 = respuestaFalsa1;
    }

    public String getRespuestaFalsa2() {
        return respuestaFalsa2;
    }

    public void setRespuestaFalsa2(String respuestaFalsa2) {
        this.respuestaFalsa2 = respuestaFalsa2;
    }

    public String getRespuestaFalsa3() {
        return respuestaFalsa3;
    }

    public void setRespuestaFalsa3(String respuestaFalsa3) {
        this.respuestaFalsa3 = respuestaFalsa3;
    }

    @Override
    public String toString() {
        return "Pregunta{" +
                "tipoPregunta=" + tipoPregunta +
                ", pregunta='" + pregunta + '\'' +
                ", respuestaCorrecta='" + respuestaCorrecta + '\'' +
                ", respuestaFalsa1='" + respuestaFalsa1 + '\'' +
                ", respuestaFalsa2='" + respuestaFalsa2 + '\'' +
                ", respuestaFalsa3='" + respuestaFalsa3 + '\'' +
                ", tema='" + tema + '\'' +
                ", ruta='" + ruta + '\'' +
                '}';
    }
}
