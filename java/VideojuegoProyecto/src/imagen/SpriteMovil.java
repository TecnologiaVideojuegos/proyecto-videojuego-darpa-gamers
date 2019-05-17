/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagen;

import location.*;
import org.newdawn.slick.*;

/**
 *
 * @author Senapi Aroal
 */
public class SpriteMovil extends Sprite{
    
    private Vector velocidad;
    
    public SpriteMovil(String ruta, Punto posicion,Vector velocidad) throws SlickException {
        super(ruta, posicion);
        this.velocidad = velocidad;
    }
    
    public SpriteMovil(String ruta, Punto posicion,Punto velocidad) throws SlickException {
        this(ruta,posicion,new Vector(velocidad));
    }
    
    public SpriteMovil(Image img, Punto posicion,Punto velocidad) throws SlickException {
        super(img,posicion);
        this.velocidad = new Vector(velocidad);
    }
    
    /**
     * Get the value of velocidad
     *
     * @return the value of velocidad
     */
    public Vector getVelocidad() {
        return velocidad;
    }

    /**
     * Set the value of velocidad
     *
     * @param velocidad new value of velocidad
     */
    public void setVelocidad(Vector velocidad) {
        this.velocidad = velocidad;
    }
    
    public void actualizarCoordenadas(int delta){
            float x = posicion.getX() + velocidad.getX() * ((float) delta/1000);
            float y = posicion.getY() + velocidad.getY() * ((float) delta/1000);
            this.setPosicion(x, y);
    }
    
}
