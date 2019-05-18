/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_level;

import characters.Monstruo;
import java.util.ArrayList;
import location.Punto;
import materials.Cofre;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Polygon;

/**
 *
 * @author Senapi Aroal
 */
public class DatosNivel {
    
    //Dato del mapa
    private final float[][] mapas;
    
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
    
    private final ArrayList<ArrayList<Monstruo>> enemigos;
    
    private final ArrayList<ArrayList<Cofre>> cofres_escenas;

    //entero que indica el número de escenas que tiene el nivel
    private final int numEscenas;
    
    //entero que contiene el número de objetos que hay en cada escena
    private final int[] numObjetos;
    
    //entero que contiene el número de enemigos que hay en cada escena
    private final int[] numEnemigos;
    
    //entero que contiene el numero de cofres que hay en cada escena
    private final int [] numCofres;
    
    /**
     * Constructor de la clase Punto
     * 
     * @param numEscenas numero de escenas que tiene el nivel que invoca a esta clase
     * @param numObjetos array de objetos que indica la cantidad que tiene cada escena
     * @param numEnemigos numero de enemigos que habrá en cada escena
     * @param num_cofres numero de cofres que habrá en cada escena
     * 
     */
    public DatosNivel(int numEscenas,int[] numObjetos,int[] numEnemigos, int[] num_cofres){
        this.numEscenas = numEscenas;
        mapas = new float[numEscenas][];
        poligonosDeEntrada = new float[numEscenas][];
        poligonosDeSalida = new float[numEscenas][];
        this.numObjetos = numObjetos;
        this.numEnemigos = numEnemigos;
        this.numCofres = num_cofres;
        this.entradas = new Punto[numEscenas];
        this.salidas = new Punto[numEscenas];
        this.mapa_objetos = new ArrayList<>();
        this.enemigos = new ArrayList<>();
        this.cofres_escenas = new ArrayList<>();
    }
    
    /**
     * Carga todos los datos referidos al nivel 1
     * 
     */
    public void datosNivel1(){
        //Datos de los mapas
        float[] escena1 = {160,608,224,608,224,736,96,736,96,704,64,704,64,640,32,640,32,608,96,608,96,320,160,320,160,288,192,288,192,320,224,320,224,288,256,288,256,352,352,352,352,224,448,224,448,128,576,128,576,160,544,160,544,224,832,224,832,160,960,160,960,224,992,224,992,608,864,608,864,736,608,736,608,416,544,416,544,352,672,352,672,672,800,672,800,608,736,608,736,416,928,416,928,288,416,288,416,384,512,384,512,576,352,576,352,512,448,512,448,448,320,448,320,416,256,416,256,448,160,448};
        float[] escena2 = {192,128,256,128,256,160,288,160,288,256,256,256,256,192,256,352,512,352,512,384,544,384,544,448,928,448,928,576,832,576,832,544,800,544,800,576,608,576,608,544,320,544,320,480,416,480,416,416,224,416,224,608,576,608,576,736,640,736,640,640,896,640,896,736,928,736,928,640,992,640,992,384,736,384,736,352,672,352,672,256,512,256,512,288,352,288,352,128,320,128,320,64,192,64,192,128};
        float[] escena3 = {32,448,288,448,288,544,96,544,96,576,32,576,32,640,160,640,160,608,352,608,352,448,544,448,544,608,576,608,576,672,864,672,864,704,960,704,960,640,928,640,928,608,832,608,832,480,960,480,960,352,896,352,896,416,832,416,832,256,992,256,992,192,768,192,768,608,640,608,640,576,608,576,608,160,480,160,480,192,416,192,416,160,384,160,384,256,544,256,544,384,224,384,224,160,256,160,256,128,224,128,224,96,128,96,128,160,160,160,160,384,32,384,32,448};
        float[] escena4 = {32,448,288,448,288,544,288,576,544,576,544,448,992,448,992,384,960,384,544,384,544,256,512,256,288,256,288,384,32,384};
        float[][] datosMapa = {escena1,escena2,escena3,escena4}; //se guardan los puntos del mapa
        for(int i = 0;i<numEscenas;i++){
            mapas[i] = datosMapa[i];
        } 
        
        //Datos de los puntos de respawn al salir de la escena o punto
        //Cuando sale de la escena a la siguiente, registra la posición en escena-1
        Punto[] entrada = {new Punto(80,655),new Punto(),new Punto()};
        for(int i = 0;i<(numEscenas-1);i++){
            entradas[i] = entrada[i];
        }
        
        //Datos de los puntos de respawn al entrar en la escena o punto donde aparecerá en la siguiente escena
        //Cuando sale de la escena a la anterior, registra la posición en escena
        Punto[] salida = {new Punto(370,450),new Punto(940,215),new Punto(945,410)};
        for(int i = 0;i<(numEscenas-1);i++){
            salidas[i] = salida[i];
        }
        
        //Datos de los polígonos de entrada
        float[] polEntEsc1 = {544,416,544,352,560,352,560,416}; //se almacena los polígonos para acceder al siguiente escenario
        float[] polEntEsc2 = {192,128,208,128,208,64,192,64};
        float[] polEntEsc3 = {32,384,32,448,48,448,48,384};
        float[] polEntEsc4 = {32,448,32,384,48,384,48,448};
        float[][] datosEntrada = {polEntEsc1,polEntEsc2,polEntEsc3,polEntEsc4}; //se guardan los puntos de entrada
        for(int i = 0;i<numEscenas;i++){
            poligonosDeEntrada[i] = datosEntrada[i];
        }
        
        //Datos de los polígonos de salida
        float[] polSalEsc1 = {0,0};
        float[] polSalEsc2 = {416,416,416,480,400,480,400,416};
        float[] polSalEsc3 = {992,192,976,192,976,256,992,256};   
        float[] polSalEsc4 = {992,384,992,448,976,448,976,384};
        float[][] datosSalida = {polSalEsc1,polSalEsc2,polSalEsc3,polSalEsc4}; //se guardan los puntos de salida
        for(int i = 0;i<numEscenas;i++){
            poligonosDeSalida[i] = datosSalida[i];
        }
        
        //Datos de los objetos del nivel
        float[] pozo = {800,480,928,480,928,544,800,544};
        float [][][] objetos = {{pozo},{},{},{}};
        for(int i=0;i<numEscenas;i++){
            mapa_objetos.add((new ArrayList<>()));//añadimos tantos arrayList como numEscenas haya (inicialmente están vacíos).
            for(int a = 0;a<numObjetos[i];a++){
                mapa_objetos.get(i).add((new Polygon(objetos[i][a])));
            }
        }
        
        //Generador de enemigos
        Punto[] enemigosEscena1 = {new Punto()};
        Punto[] enemigosEscena2 = {new Punto()};
        Punto[] enemigosEscena3 = {new Punto()};
        Punto[] enemigosEscena4 = {new Punto(346,440)};
        Punto[][] enemigosNivel1 = {enemigosEscena1,enemigosEscena2,enemigosEscena3,enemigosEscena4};
        
        for(int i = 0;i<numEscenas;i++){
            enemigos.add(new ArrayList<>());
            for(int j = 0;j<numEnemigos[i];j++){
                try{
                    int velocidad = (int)(100+51*Math.random());
                    enemigos.get(i).add(new Monstruo(100,enemigosNivel1[i][j],new SpriteSheet("./res/grafico/Character2.png",24,22),velocidad,0,96,"Pasivo",50));
                    System.out.println(enemigos.get(i).size());
                }catch(Exception ex){}
            }
        }
        
        //Generador de cofres
        Punto[] loc_cofres_escena1 = {new Punto(600,500)};
        Punto[] loc_cofres_escena2 = {new Punto (450, 100)};
        Punto[] loc_cofres_escena3 = {};
        Punto[][] loc_cofres_escenas = {loc_cofres_escena1,loc_cofres_escena2,loc_cofres_escena3};
        
        for(int i = 0;i<numEscenas;i++){
            this.cofres_escenas.add(new ArrayList<>());
            for(int j = 0; j < this.numCofres[i]; j++){
                try{
                    
                    cofres_escenas.get(i).add(new Cofre((int)loc_cofres_escenas[i][j].getX(),(int)loc_cofres_escenas[i][j].getY()));
                
                }catch(Exception ex){
                    System.out.println("Error al generar los cofres");
                }
            }
        }
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
     * Devuelve los cofres de esa escena
     * 
     * @param index indica el índice para obtener los cofres de la escena
     * @return ArrayList de los cofres
     */
    public ArrayList<Cofre> cofresNivel(int index){ 
        return this.cofres_escenas.get(index);
    }
    
    /**
     * Devuelve los enemigos de esa escena
     * 
     * @param index indica el índice para obtener los objetos de la escena
     * @return la lista de polígonos de los objetos decorativos
     */
    public ArrayList<Monstruo> enemigosNivel(int index){ 
        return enemigos.get(index);
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