/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;


import java.util.ArrayList;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Senapi Aroal
 */
public class Escena {
    
    private TiledMap mapa_escena; 
    private Polygon area_entrada;  //polígono para entrar a la escena se situará como lugar donde el personaje hará respawn
    private Polygon area_salida;   //polígono para salir de la escena
    private Polygon mapa_colisiones; //polígono que limita el mapa
    private ArrayList<Polygon> mapa_objetos;  //arraylist de polígonos que establece los elementos del mapa 

    public Escena(TiledMap mapa_escena,Polygon mapa_colisiones) {
        this.mapa_escena = mapa_escena;
        this.mapa_colisiones = mapa_colisiones;
    }

    /**
     * Get the value of mapa_colisiones
     *
     * @return the value of mapa_colisiones
     */
    public Polygon getMapa_colisiones() {
        return mapa_colisiones;
    }

    /**
     * Get the value of mapa_escena
     *
     * @return the value of mapa_escena
     */
    public TiledMap getMapa_escena() {
        return mapa_escena;
    }

    /**
     * Indica si se ha colisionado con algún objeto
     *
     * @param lado lado por el cuál se puede producir la colisión
     * @return true en caso de clisión, false en caso de no haber
     */
    public boolean colisionConObjetos(Shape lado){
        if(lado.intersects(mapa_colisiones)){
            return true;
        }       
        return false;
    }
    
}
