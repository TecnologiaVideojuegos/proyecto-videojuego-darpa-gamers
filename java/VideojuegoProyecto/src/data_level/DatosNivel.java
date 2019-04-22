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
    
    /**
     * Devuelve un polígono de colisiones de ese mapa
     * 
     * @param index indica el índice para obtener el mapa adecuado
     * @return el polígono pedido
     */
    public Polygon mapasNivel(int index){
        return new Polygon(mapas[index]);
    }
    
    /**
     * Devuelve el polígono de entrada de una escena
     * 
     * @param index indica el índice para obtener el polígono adecuado
     * @return el polígono pedido
     */
    public Polygon entradasNivel(int index){ //Devuelve el poligono para entrar a la escena
        return new Polygon(poligonosDeEntrada[index]);
    }
    
    /**
     * Devuelve un polígono de salida de una escena
     * 
     * @param index indica el índice para obtener el polígono adecuado
     * @return el polígono pedido
     */
    public Polygon salidasNivel(int index){ //Devuelve el poligono para salir de la escena
        return new Polygon(poligonosDeSalida[index]);
    }
    
    /**
     * Devuelve un polígono de los objetos de esa escena
     * 
     * @param index indica el índice para obtener los objetos de la escena
     * @return la lista de polígonos de los objetos decorativos
     */
    public ArrayList<Polygon> objetosNivel(int index){ 
        return mapa_objetos.get(index);
    }

    /**
     * Obtiene el punto de inicio
     * 
     * @return el punto de respawn inicial de cada nivel
     */
    public Punto getInicio() {
        return inicio;
    }

    /**
     * Establece la nueva posición de la entrada a la escena
     * 
     * @param index indica la escena que se encuentra
     * @param punto posición registrada por última vez
     */
    public void setEntradas(int index,Punto punto) {
         entradas[index] = punto;
    }
    
    /**
     * Obtiene la posición de la entrada registrada
     * 
     * @param index indica la escena que se encuentra
     * @return la posición registrada por última vez
     */
    public Punto getEntradas(int index) {
        return entradas[index];
    }
    
    /**
     * Establece la nueva posición de la entrada a la escena
     * 
     * @param index indica la escena que se encuentra
     * @param punto posición registrada por última vez
     */
    public void setSalidas(int index,Punto punto) {
         salidas[index] = punto;
    }

    /**
     * Obtiene la posición de la salida registrada
     * 
     * @param index indica la escena que se encuentra
     * @return la posición registrada por última vez
     */
    public Punto getSalidas(int index) {
        return salidas[index];
    }   
}
