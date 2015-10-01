package com.ds.louri.askyourself.util;

import com.ds.louri.askyourself.*;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Partida{

    private static int aciertos;
    private static boolean todasRespondidas;
    private static int posicion;
    private static Map<Integer,Pregunta> listadoTodasPreguntas = new HashMap<Integer,Pregunta>();
    private static List<Integer> listaPeque;
    private DBHelper bd;

    public Partida(){
        aciertos = 0;
        todasRespondidas = false;
        posicion = 0;
        bd = DBHelper.getInstance(temas.contexto);
        listadoTodasPreguntas = bd.getAll();
    }

    public static int calcularPregunta(){
        if(posicion==14){
            todasRespondidas=true;
            return posicion++;
        }
        if(posicion==listadoTodasPreguntas.size()){
            todasRespondidas=true;
        }
        return posicion++;
    }



    public static List<Integer> getListaPeque() {
        return listaPeque;
    }

    public static int getAciertos() {
        return aciertos;
    }

    public static void setAciertos(int aciertos) {
        Partida.aciertos = aciertos;
    }

    public static boolean isTodasRespondidas() {
        return todasRespondidas;
    }

    public static Map<Integer, Pregunta> getListadoTodasPreguntas() {
        return listadoTodasPreguntas;
    }

    public static int getPosicion() {
        return posicion;
    }

    public void seleccionarTema(String tema){
        listaPeque = new ArrayList<>();

        for (Map.Entry entry : listadoTodasPreguntas.entrySet()) {
            Integer pos = (Integer)entry.getKey();
            Pregunta pr = (Pregunta)entry.getValue();
            if(pr.getTema().equals(tema)){
                listaPeque.add(pos);
            }
        }
        Collections.shuffle(listaPeque);
    }

}
