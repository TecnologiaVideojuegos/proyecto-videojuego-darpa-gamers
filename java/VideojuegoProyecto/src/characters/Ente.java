/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package characters;

import location.Punto;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Senapi Aroal
 */
public class Ente {
    
    private int hp;
    private Punto punto;
    private SpriteSheet sprite;
    private float velocidad;
    private int direccion;

    /**
     * Constructor de la clase Ente
     * 
     * @param hp vida del ente
     * @param punto lugar del mapa donde se posiciona
     * @param sprite imagen del ente
     * @param velocidad velocidad a la que se mueve el ente
     * @param direccion direccion 0,1,2,3 corresponde a donde mira el ente
     *
     */
    public Ente(int hp, Punto punto, SpriteSheet sprite, float velocidad,int direccion) {
        this.hp = hp;
        this.punto = punto;
        this.sprite = sprite;
        this.velocidad = velocidad;
        this.direccion = direccion;
    }

    /**
     * Get the value of direccion
     *
     * @return the value of direccion
     */
    public int getDireccion() {
        return direccion;
    }

    /**
     * Set the value of direccion
     *
     * @param direccion new value of direccion
     */
    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }
    
    /**
     * Get the value of velocidad
     *
     * @return the value of velocidad
     */
    public float getVelocidad() {
        return velocidad;
    }

    /**
     * Set the value of velocidad
     *
     * @param velocidad new value of velocidad
     */
    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }

    /**
     * Get the value of sprite
     *
     * @return the value of sprite
     */
    public SpriteSheet getSpriteSheet() {
        return sprite;
    }

    /**
     * Set the value of sprite
     *
     * @param sprite new value of sprite
     */
        public void setSpriteSheet(SpriteSheet sprite) {
        this.sprite = sprite;
    }

    /**
     * Get the value of punto
     *
     * @return the value of punto
     */
    public Punto getPunto() {
        return punto;
    }

    /**
     * Set the value of punto
     *
     * @param punto new value of punto
     */
    public void setPunto(Punto punto) {
        this.punto = punto;
    }

    /**
     * Get the value of hp
     *
     * @return the value of hp
     */
    public int getHp() {
        return hp;
    }

    /**
     * Set the value of hp
     *
     * @param hp new value of hp
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Desplaza el personaje hacia abajo
     *
     */
    public void moverAbajo(){
        this.getPunto().setY(this.getPunto().getY()+this.getVelocidad());
    }
    
    /**
     * Desplaza el personaje hacia arriba
     *
     */
    public void moverArriba(){
        this.getPunto().setY(this.getPunto().getY()-this.getVelocidad());
    }
    
    /**
     * Desplaza el personaje hacia la derecha
     *
     */
    public void moverDrcha(){
        this.getPunto().setX(this.getPunto().getX()+this.getVelocidad());
    }
     
    /**
     * Desplaza el personaje hacia la izquierda
     *
     */
    public void moverIzq(){
        this.getPunto().setX(this.getPunto().getX()-this.getVelocidad());
    }
}
