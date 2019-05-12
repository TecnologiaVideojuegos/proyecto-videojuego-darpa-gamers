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
        float[] mapaTest = {224,192,256,192,256,224,352,224,480,224,480,160,448,160,448,128,448,64,544,64,544,96,576,96,576,64,608,64,608,160,544,160,544,256,512,256,512,448,576,448,576,416,608,416,608,448,672,448,672,416,736,416,736,384,864,384,864,416,928,416,928,448,960,448,960,480,992,480,992,576,928,576,928,640,672,640,672,576,576,576,576,544,544,544,544,512,448,512,448,448,480,448,480,256,224,256};
        float[] mapaTest2 = {160,320,224,320,224,288,352,288,352,320,384,320,384,256,416,256,416,160,352,160,352,32,384,32,384,64,480,64,480,32,512,32,512,160,448,160,448,256,480,256,480,320,512,320,512,384,448,384,448,480,480,480,480,608,352,608,352,512,384,512,384,480,416,480,416,384,224,384,224,352,160,352};
        float[] mapaTest3 = {192,288,288,288,288,192,640,192,640,256,672,256,672,288,704,288,704,320,672,320,672,352,640,352,640,448,384,448,384,384,288,384,288,320,192,320};
        float[][] datosMapa = {mapaTest,mapaTest2,mapaTest3}; //se guardan los puntos del mapa
        for(int i = 0;i<numEscenas;i++){
            mapas[i] = datosMapa[i];
        } 
        
        //Datos de los puntos de respawn al entrar en la escena o punto
        Punto[] entrada = {new Punto(230,200),new Punto(233,292)};
        for(int i = 0;i<(numEscenas-1);i++){
            entradas[i] = entrada[i];
        }
        
        //Datos de los puntos de respawn al salir de la escena o punto donde aparecerá en la siguiente escena
        Punto[] salida = {new Punto(280,300),new Punto(233,292)};
        for(int i = 0;i<(numEscenas-1);i++){
            salidas[i] = salida[i];
        }
        
        //Datos de los polígonos de entrada
        float[] poligonoEntrada = {968,480,1064,480,1064,576,968,576}; //se almacena los polígonos para acceder al siguiente escenario
        float[] otro = {423,553,439,553,439,565,423,565};
        float[] mehaa = {0,0,0,0};
        float[][] datosEntrada = {poligonoEntrada,otro,mehaa}; //se guardan los puntos de entrada
        for(int i = 0;i<numEscenas;i++){
            poligonosDeEntrada[i] = datosEntrada[i];
        }
        
        //Datos de los polígonos de salida
        float[] poligonoSalida = {192,320,192,352,180,352,180,320};
        float[] meh = {192,288,224,288,224,320,192,320};   
        float[] meha = {0,0,0,0};
        float[][] datosSalida = {meha,poligonoSalida,meh}; //se guardan los puntos de salida
        for(int i = 0;i<numEscenas;i++){
            poligonosDeSalida[i] = datosSalida[i];
        }
        
        //Datos de los objetos del nivel
        float[] piano = {768,480,864,480,864,576,768,576};
        float[] pupitre = {120,50,140,50,120,80,140,20};
        float [][] objetos = {piano,pupitre};
        for(int i=0;i<numEscenas;i++){
            mapa_objetos.add((new ArrayList<>()));//añadimos tantos arrayList como numEscenas haya (inicialmente están vacíos).
            for(int a = 0;a<numObjetos[i];a++){
                mapa_objetos.get(i).add((new Polygon(objetos[a])));
            }
        }
        
        //Generador de enemigos
        Punto[] inicioEnemigos1 = {new Punto(480,110)};
        Punto[] inicioEnemigos2 = {};
        Punto[] inicioEnemigos3 = {new Punto(603,218),new Punto(410,421)};
        Punto[][] inicioEnemigos = {inicioEnemigos1,inicioEnemigos2,inicioEnemigos3};
        
        for(int i = 0;i<numEscenas;i++){
            enemigos.add(new ArrayList<>());
            for(int j = 0;j<numEnemigos[i];j++){
                try{
                    int velocidad = (int)(100+51*Math.random());
                    enemigos.get(i).add(new Monstruo(100,inicioEnemigos[i][j],new SpriteSheet("./res/flecha.png",24,22),velocidad,0,96,"Pasivo",50));
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
