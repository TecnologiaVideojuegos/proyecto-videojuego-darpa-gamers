/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package location;

/**
 *
 * @author Senapi Aroal
 */
public class Punto {
    
    private float x;   
    private float y;

    /**
     * Constructor de la clase Punto
     * 
     * @param x representa el eje x en el mapa
     * @param y representa el eje y en el mapa
     *
     */
    public Punto(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor de la clase Punto por defecto
     * 
     *
     */
    public Punto(){
        this.x = 0.0f;
        this.y = 0.0f;
    }
    
    /**
     * Get the value of x
     *
     * @return the value of x
     */
    public float getX() {
        return x;
    }

    /**
     * Set the value of x
     *
     * @param x new value of x
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Get the value of y
     *
     * @return the value of y
     */
    public float getY() {
        return y;
    }

    /**
     * Set the value of y
     *
     * @param y new value of y
     */
    public void setY(float y) {
        this.y = y;
    }
}
