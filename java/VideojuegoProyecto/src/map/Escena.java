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
    
    private final TiledMap mapa_escena;
    private final Polygon area_entrada;  //polígono para entrar a la escena siguiente
    private final Polygon area_salida;   //polígono para salir de la escena e ir a la anterior
    private final Polygon mapa_colision;
    private ArrayList<Polygon> mapa_objetos;

    public Escena(TiledMap mapa_escena,Polygon mapa_colision,ArrayList<Polygon> mapa_objetos,Polygon area_entrada,Polygon area_salida) {
        this.mapa_escena = mapa_escena;
        this.mapa_colision = mapa_colision;
        this.area_entrada = area_entrada;
        this.area_salida = area_salida;
        this.mapa_objetos = mapa_objetos;
    }

    public TiledMap getMapa_escena() {
        return mapa_escena;
    }

    public Polygon getMapa_colision() {
        return mapa_colision;
    }

    public Polygon getArea_entrada() {
        return area_entrada;
    }

    public Polygon getArea_salida() {
        return area_salida;
    }
    
    public ArrayList<Polygon> getMapa_objetos(){
        return mapa_objetos;
    }
    
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

    public boolean colisionSalida(Shape lado){
        return lado.intersects(area_salida);
    }
    
    public boolean colisionEntrada(Shape lado){
        return lado.intersects(area_entrada);
    }
}
