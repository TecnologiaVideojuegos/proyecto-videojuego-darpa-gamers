/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagen;

import location.Punto;
import org.newdawn.slick.*;

/**
 *
 * @author Senapi Aroal
 */
public class Sprite extends Image{
    
    protected Punto posicion;
    
    /**
     * Constructor de la clase Sprite
     * 
     * @param ruta url de donde se encuentra la imagen
     * @param posicion posición del sprite
     * @throws SlickException tratar excepción sobre el url
     */
    public Sprite(String ruta,Punto posicion) throws SlickException{
        super(ruta);
        this.posicion = posicion;
    }
    
    /**
     * Constructor de la clase Sprite
     * 
     * @param ruta url de donde se encuentra la imagen
     * @param x posición del sprite en el eje x
     * @param y posición del sprite en el eje y
     * @throws SlickException tratar excepción sobre el url
     */
    public Sprite(String ruta,float x,float y) throws SlickException{
        super(ruta);
        this.posicion = new Punto(x,y);
    }
    
    /**
     * Get the value of posicion
     *
     * @return the value of posicion
     */
    public Punto getPosicion() {
        return posicion;
    }

    /**
     * Set the value of posicion
     *
     * @param posicion new value of posicion
     */
    public void setPosicion(Punto posicion) {
        this.posicion = posicion;
    }   
    
    /**
     * Set the value of posicion
     * 
     * @param x valor en eje x
     * @param y valor en eje y
     */
    public void setPosicion(float x,float y){
        this.posicion = new Punto(x,y);
    }
    
    /**
     * método que dibuja el sprite especificando la posición
     * 
     */
    @Override
    public void draw(){
        super.draw(posicion.getX(),posicion.getY());
    }
}
