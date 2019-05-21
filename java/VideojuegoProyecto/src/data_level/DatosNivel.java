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
    
    //ArrayList que contendrá los enemigo de todas las escenas 
    private final ArrayList<ArrayList<Monstruo>> enemigos;
    
    //ArrayList que contendrá los cofres de cada escena
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
    
    public DatosNivel(){
        this.numEscenas = 0;
        mapas = null;
        poligonosDeEntrada = null;
        poligonosDeSalida = null;
        this.numObjetos = null;
        this.numEnemigos = null;
        this.numCofres = null;
        this.entradas = null;
        this.salidas = null;
        this.mapa_objetos = new ArrayList<>();
        this.enemigos = new ArrayList<>();
        this.cofres_escenas = null;
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
        Punto[] salida = {new Punto(370,440),new Punto(940,200),new Punto(945,410)};
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
        Punto[] enemigosEscena2 = {new Punto(586,337)};
        Punto[] enemigosEscena3 = {new Punto(500,206),new Punto(134,572)};
        Punto[] enemigosEscena4 = {new Punto(346,440), new Punto(356, 330)};
        Punto[][] enemigosNivel1 = {enemigosEscena1,enemigosEscena2,enemigosEscena3,enemigosEscena4};
        
        for(int i = 0;i<numEscenas;i++){
            enemigos.add(new ArrayList<>());
            for(int j = 0;j<numEnemigos[i];j++){
                try{
                    int velocidad = (int)(100+51*Math.random());
                    enemigos.get(i).add(new Monstruo(100,enemigosNivel1[i][j],velocidad,0,96,"Pasivo",50));
                }catch(Exception ex){}
            }
        }
        
        //Generador de cofres
        Punto[] loc_cofres_escena1 = {new Punto(739,418),new Punto(407,535),new Punto(541,132)};
        Punto[] loc_cofres_escena2 = {new Punto(284, 67)};
        Punto[] loc_cofres_escena3 = {new Punto(39,600), new Punto(406,221)};
        Punto[] loc_cofres_escena4 = {new Punto(389, 289)};
        Punto[][] loc_cofres_escenas = {loc_cofres_escena1,loc_cofres_escena2,loc_cofres_escena3,loc_cofres_escena4};
        
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
    
    public void datosNivel2(){
        //Datos de los mapas
        float[] escena1 = {192,512,384,512,384,416,128,416,128,352,96,352,96,288,128,288,128,256,576,256,576,160,288,160,288,96,704,96,704,160,640,160,640,288,800,288,800,256,928,256,928,416,864,416,864,352,704,352,704,416,448,416,448,608,192,608,192,512};
        float[] escena2 = {32,256,64,256,64,288,224,288,224,256,320,256,320,288,352,288,352,160,128,160,128,96,448,96,448,160,416,160,416,288,576,288,576,160,544,160,544,64,608,64,608,96,736,96,736,160,640,160,640,288,768,288,768,256,928,256,928,416,896,416,896,384,544,384,544,512,608,512,608,530,640,530,640,512,704,512,704,576,288,576,288,516,480,516,480,384,64,384,64,416,32,416,32,256};
        float[] escena3 = {64,256,128,256,128,288,544,288,544,288,576,288,576,160,352,160,352,128,320,128,320,96,608,96,608,96,640,96,640,160,640,160,640,288,672,288,928,288,928,256,992,256,992,416,928,416,928,384,896,384,896,352,448,352,448,544,640,544,640,576,736,576,736,576,896,576,896,672,768,672,768,640,576,640,576,576,544,576,544,608,384,608,384,352,128,352,128,416,64,416,64,256};
        
        float[][] datosMapa = {escena1,escena2,escena3}; //se guardan los puntos del mapa
        for(int i = 0;i<numEscenas;i++){
            mapas[i] = datosMapa[i];
        } 
        
        //Datos de los puntos de respawn al salir de la escena o punto
        //Cuando sale de la escena a la siguiente, registra la posición en escena-1
        Punto[] entrada = {new Punto(161,289),new Punto(),new Punto()};
        for(int i = 0;i<(numEscenas-1);i++){
            entradas[i] = entrada[i];
        }
        
        //Datos de los puntos de respawn al entrar en la escena o punto donde aparecerá en la siguiente escena
        //Cuando sale de la escena a la anterior, registra la posición en escena
        Punto[] salida = {new Punto(68,326),new Punto(106,309)};
        for(int i = 0;i<(numEscenas-1);i++){
            salidas[i] = salida[i];
        }
        
        //Datos de los polígonos de entrada
        float[] polEntEsc1 = {928,256,912,256,912,416,928,416}; //se almacena los polígonos para acceder al siguiente escenario
        float[] polEntEsc2 = {912,256,928,256,928,416,912,416};
        float[] polEntEsc3 = {959,320,992,320,992,351,959,351};
        
        float[][] datosEntrada = {polEntEsc1,polEntEsc2,polEntEsc3}; //se guardan los puntos de entrada
        for(int i = 0;i<numEscenas;i++){
            poligonosDeEntrada[i] = datosEntrada[i];
        }
        
        //Datos de los polígonos de salida
        float[] polSalEsc1 = {0,0};
        float[] polSalEsc2 = {32,256,48,256,48,416,32,416};
        float[] polSalEsc3 = {64,256,80,256,80,416,64,416};   
        
        float[][] datosSalida = {polSalEsc1,polSalEsc2,polSalEsc3}; //se guardan los puntos de salida
        for(int i = 0;i<numEscenas;i++){
            poligonosDeSalida[i] = datosSalida[i];
        }
        
        //Datos de los objetos del nivel
        float[] mesa = {288,300,544,300,544,320,288,320};
        float [][][] objetos = {{mesa},{},{}};
        for(int i=0;i<numEscenas;i++){
            mapa_objetos.add((new ArrayList<>()));//añadimos tantos arrayList como numEscenas haya (inicialmente están vacíos).
            for(int a = 0;a<numObjetos[i];a++){
                mapa_objetos.get(i).add((new Polygon(objetos[i][a])));
            }
        }
        
        //Generador de enemigos
        Punto[] enemigosEscena1 = {new Punto(430,114), new Punto(623,314), new Punto(287,539), new Punto(313,265)};
        Punto[] enemigosEscena2 = {new Punto(586,337),new Punto(452,541),new Punto(225,114),new Punto(812,320),new Punto(236,308)};
        Punto[] enemigosEscena3 = {new Punto(408,296),new Punto(422,570),new Punto(863,301)};
        
        Punto[][] enemigosNivel1 = {enemigosEscena1,enemigosEscena2,enemigosEscena3};
        
        for(int i = 0;i<numEscenas;i++){
            enemigos.add(new ArrayList<>());
            for(int j = 0;j<numEnemigos[i];j++){
                try{
                    int velocidad = (int)(100+51*Math.random());
                    enemigos.get(i).add(new Monstruo(200,enemigosNivel1[i][j],velocidad,0,96,"Pasivo",100));
                }catch(Exception ex){}
            }
        }
        
        //Generador de cofres
        Punto[] loc_cofres_escena1 = {new Punto(295,517)};
        Punto[] loc_cofres_escena2 = {new Punto(357, 100),new Punto(666, 534),new Punto(551, 69),new Punto(693, 98)};
        Punto[] loc_cofres_escena3 = {new Punto(483,98), new Punto(857,641),new Punto(778,641)};
        
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
    
    public void datosNivel3(){
        //Datos de los mapas
        float[] escena1 = {35,544,796,544,800,577,898,577,898,481,447,481,448,256,512,257,512,416,960,418,960,510,989,510,991,612,912,613,915,735,832,735,832,679,710,679,710,736,609,733,609,605,35,606,35,544};
        float[] escena2 = {381,609,381,518,290,515,289,610,30,609,30,190,381,190,381,353,288,353,289,447,443,448,450,162,988,157,988,226,866,226,866,319,959,321,960,642,544,642,544,512,451,512,451,609,381,609};
        float[] escena3 = {32,160,161,160,161,349,287,349,287,320,384,320,384,349,417,349,417,320,545,320,547,349,577,349,577,320,641,320,641,349,670,349,670,320,766,320,766,352,831,352,831,384,955,384,955,454,865,454,865,510,705,510,705,480,676,480,676,512,576,512,576,481,549,481,549,512,289,513,289,608,95,608,95,231,32,231,32,160};
        float[] escena4 = {64,384,320,384,352,384,352,512,352,544,576,544,576,512,640,512,672,512,672,544,736,544,736,512,768,512,800,512,800,704,768,704,768,640,576,640,576,608,288,608,288,448,64,448,64,384};
        float[][] datosMapa = {escena1,escena2,escena3,escena4}; //se guardan los puntos del mapa
        for(int i = 0;i<numEscenas;i++){
            mapas[i] = datosMapa[i];
        } 
        
        //Datos de los puntos de respawn al salir de la escena o punto
        //Cuando sale de la escena a la siguiente, registra la posición en escena-1
        Punto[] entrada = {new Punto(63,556),new Punto(),new Punto(),new Punto()};
        for(int i = 0;i<(numEscenas-1);i++){
            entradas[i] = entrada[i];
        }
        
        //Datos de los puntos de respawn al entrar en la escena o punto donde aparecerá en la siguiente escena
        //Cuando sale de la escena a la anterior, registra la posición en escena
        Punto[] salida = {new Punto(413,562),new Punto(61,180),new Punto(113,401)};
        for(int i = 0;i<(numEscenas-1);i++){
            salidas[i] = salida[i];
        }
        
        //Datos de los polígonos de entrada
        float[] polEntEsc1 = {450,257,510,257,510,273,450,273}; //se almacena los polígonos para acceder al siguiente escenario
        float[] polEntEsc2 = {988,159,988,227,972,227,972,159};
        float[] polEntEsc3 = {954,386,954,454,939,454,939,386};
        float[] polEntEsc4 = {672,512,672,544,736,544,736,512};
        
        float[][] datosEntrada = {polEntEsc1,polEntEsc2,polEntEsc3,polEntEsc4}; //se guardan los puntos de entrada
        for(int i = 0;i<numEscenas;i++){
            poligonosDeEntrada[i] = datosEntrada[i];
        }
        
        //Datos de los polígonos de salida
        float[] polSalEsc1 = {0,0};
        float[] polSalEsc2 = {381,609,381,593,450,593,450,609};
        float[] polSalEsc3 = {32,160,46,160,46,229,32,229};  
        float[] polSalEsc4 = {64,384,64,448,80,448,80,384};
        
        float[][] datosSalida = {polSalEsc1,polSalEsc2,polSalEsc3,polSalEsc4}; //se guardan los puntos de salida
        for(int i = 0;i<numEscenas;i++){
            poligonosDeSalida[i] = datosSalida[i];
        }
        
        //Datos de los objetos del nivel
        float[] pozo1 = {129,289,223,290,222,414,191,415,190,511,95,510,95,322,129,319,129,289};
        float[] pozo2 = {513,225,733,225,733,320,513,320};
        float[] pozo3 = {674,385,894,387,894,476,866,476,866,574,769,574,766,512,674,512,674,385};
        float[] pared = {159,421,224,418,224,545,159,543};
        float [][][] objetos = {{},{pozo1,pozo2,pozo3},{pared},{}};
        for(int i=0;i<numEscenas;i++){
            mapa_objetos.add((new ArrayList<>()));//añadimos tantos arrayList como numEscenas haya (inicialmente están vacíos).
            for(int a = 0;a<numObjetos[i];a++){
                mapa_objetos.get(i).add((new Polygon(objetos[i][a])));
            }
        }
        
        //Generador de enemigos
        Punto[] enemigosEscena1 = {new Punto(665,605), new Punto(851,649), new Punto(918,470), new Punto(556,424)};
        Punto[] enemigosEscena2 = {new Punto(533,385),new Punto(246,234),new Punto(770,202),new Punto(180,537)};
        Punto[] enemigosEscena3 = {new Punto(262,375),new Punto(122,560),new Punto(521,388), new Punto(497,384)};
        Punto[] enemigosEscena4 = {};
        
        Punto[][] enemigosNivel1 = {enemigosEscena1,enemigosEscena2,enemigosEscena3,enemigosEscena4};
        
        for(int i = 0;i<numEscenas;i++){
            enemigos.add(new ArrayList<>());
            for(int j = 0;j<numEnemigos[i];j++){
                try{
                    int velocidad = (int)(100+51*Math.random());
                    enemigos.get(i).add(new Monstruo(300,enemigosNivel1[i][j],velocidad,0,96,"Pasivo",150));
                }catch(Exception ex){}
            }
        }
        
        //Generador de cofres
        Punto[] loc_cofres_escena1 = {new Punto(754,548)};
        Punto[] loc_cofres_escena2 = {new Punto(612, 612),new Punto(48, 577),new Punto(45, 196),new Punto(774, 163)};
        Punto[] loc_cofres_escena3 = {new Punto(247,575), new Punto(731,320)};
        Punto[] loc_cofres_escena4 = {new Punto(295,517)};
        
        Punto[][] loc_cofres_escenas = {loc_cofres_escena1,loc_cofres_escena2,loc_cofres_escena3,loc_cofres_escena4};
        
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