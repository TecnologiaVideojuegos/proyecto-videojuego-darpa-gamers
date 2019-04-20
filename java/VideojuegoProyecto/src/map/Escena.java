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
 * @version 0.0.10
 * @author Senapi Aroal
 */
public class Escena {
    
    private final TiledMap mapa_escena; 
    private final Polygon area_entrada;  //polígono para entrar a la escena se situará como lugar donde el personaje hará respawn
    private final Polygon area_salida;   //polígono para salir de la escena
    private final Polygon mapa_colision; //polígono que limita el mapa
    private final ArrayList<Polygon> mapa_objetos;  //arraylist de polígonos que establece los elementos del mapa 

    public Escena(TiledMap mapa_escena,Polygon mapa_colision,ArrayList<Polygon> mapa_objetos,Polygon area_entrada,Polygon area_salida) {
        this.mapa_escena = mapa_escena;
        this.mapa_colision = mapa_colision;
        this.area_entrada = area_entrada;
        this.area_salida = area_salida;
        this.mapa_objetos = mapa_objetos;
    }

    /**
     * Get the value of mapa_colisiones
     *
     * @return the value of mapa_colisiones
     */
    public Polygon getMapa_colision() {
        return mapa_colision;
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
     * Get the value of area_entrada
     *
     * @return the value of area_entrada
     */
    public Polygon getArea_entrada() {
        return area_entrada;
    }

    /**
     * Get the value of area_salida
     *
     * @return the value of area_salida
     */
    public Polygon getArea_salida() {
        return area_salida;
    }

    /**
     * Get the value of mapa_objetos
     *
     * @return the value of mapa_objetos
     */
    public ArrayList<Polygon> getMapa_objetos() {
        return mapa_objetos;
    }

    
    /**
     * Indica si se ha colisionado con algún objeto
     *
     * @param lado lado por el cuál se puede producir la colisión
     * @return true en caso de colisión, false en caso de no haber
     */
    public boolean colisionConObjetos(Shape lado){
        if(lado.intersects(mapa_colision)){
            return true;
        }      
        for(int i = 0;i<mapa_objetos.size();i++){
            if(lado.intersects(mapa_objetos.get(i))){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Indica si se ha colisionado con el objeto de salida
     *
     * @param lado lado por el cuál se puede producir la colisión
     * @return true en caso de colisión, false en caso de no haber
     */
    public boolean colisionSalida(Shape lado){
        return lado.intersects(area_salida);
    }
    
    /**
     * Indica si se ha colisionado con el objeto de entrada
     *
     * @param lado lado por el cuál se puede producir la colisión
     * @return true en caso de colisión, false en caso de no haber
     */
    public boolean colisionEntrada(Shape lado){
        return lado.intersects(area_entrada);
    }
}
