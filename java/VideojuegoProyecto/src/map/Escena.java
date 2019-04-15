/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;


import java.util.ArrayList;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Senapi Aroal
 */
public class Escena {
    
    private ArrayList<TiledMap> mapa_escena; 
    private ArrayList<Polygon> mapa_colisiones;

    public Escena(ArrayList<TiledMap> mapa_escena, ArrayList<Polygon> mapa_colisiones) {
        this.mapa_escena = mapa_escena;
        this.mapa_colisiones = mapa_colisiones;
    }

    /**
     * Get the value of mapa_colisiones
     *
     * @return the value of mapa_colisiones
     */
    public ArrayList<Polygon> getMapa_colisiones() {
        return mapa_colisiones;
    }

    /**
     * Get the value of mapa_escena
     *
     * @return the value of mapa_escena
     */
    public ArrayList<TiledMap> getMapa_escena() {
        return mapa_escena;
    }

    

}
