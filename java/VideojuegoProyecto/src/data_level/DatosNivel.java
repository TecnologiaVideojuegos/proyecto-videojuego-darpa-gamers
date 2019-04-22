/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_level;

import java.util.ArrayList;
import location.Punto;
import org.newdawn.slick.geom.Polygon;

/**
 *
 * @author Senapi Aroal
 */
public class DatosNivel {
    
    //Dato del mapa
    private final float[][] mapas;
    
    //Dato del punto donde respawnea al inicio del nivel
    private Punto inicio;
    
    //Dato del polígono de entrada
    private final float[][] poligonosDeEntrada;
    
    //Dato del polígonos de salida
    private final float[][] poligonosDeSalida;
    
    //Dato de los puntos dónde el jugador respawnea cuando entra en la escena
    private final Punto[] entradas;
    
    //Dato de los puntos dónde el jugador respawnea cuando sale de la escena
    private final Punto[] salidas;
    
    //ArrayLists donde contendrá los objetos de cada escena
    private final ArrayList<ArrayList<Polygon>> mapa_objetos; 

    //entero que indica el número de escenas que tiene el nivel
    private final int numEscenas;
    
    //entero que contiene el número de objetos que hay en cada escena
    private final int[] numObjetos;
    
    /**
     * Constructor de la clase DatosNivel
     * 
     * @param numEscenas número de escenas que posee él nivel que llama a la clase
     * @param numObjetos array donde contiene la cantidad de objetos que tiene cada escena dentro de ese nivel
     */  
    public DatosNivel(int numEscenas,int[] numObjetos){
        this.numEscenas = numEscenas;
        mapas = new float[numEscenas][];
        poligonosDeEntrada = new float[numEscenas][];
        poligonosDeSalida = new float[numEscenas][];
        this.numObjetos = numObjetos;
        this.entradas = new Punto[numEscenas];
        this.salidas = new Punto[numEscenas];
        this.inicio = new Punto();
        this.mapa_objetos = new ArrayList<>();
    }
    
    public Polygon mapasNivel(int index){ //Devuelve el poligono de colisiones de ese mapa
        return new Polygon(mapas[index]);
    }
    
    public Polygon entradasNivel(int index){ //Devuelve el poligono para entrar a la escena
        return new Polygon(poligonosDeEntrada[index]);
    }
    
    public Polygon salidasNivel(int index){ //Devuelve el poligono para salir de la escena
        return new Polygon(poligonosDeSalida[index]);
    }
    
    public ArrayList<Polygon> objetosNivel(int index){ //Devuelve un arrayList de poligonos con los objetos de esa escena
        return mapa_objetos.get(index);
    }

    public Punto getInicio() {
        return inicio;
    }

    public void setEntradas(int index,Punto punto) {
         entradas[index] = punto;
    }

    public void setSalidas(int index,Punto punto) {
         salidas[index] = punto;
    }

    public Punto getEntradas(int index) {
        return entradas[index];
    }

    public Punto getSalidas(int index) {
        return salidas[index];
    }
}
