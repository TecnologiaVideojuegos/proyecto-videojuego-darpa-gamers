/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package characters;

import location.Punto;
import imagen.SpriteDinamica;

/**
 * @version 0.0.10
 * @author Senapi Aroal
 */
public class Ente {
    
    private int hp;
    private Punto punto;
    private SpriteDinamica sprite;
    private float velocidad;

    /**
     * Constructor de la clase Ente
     * 
     * @param hp vida del ente
     * @param punto lugar del mapa donde se posiciona
     * @param sprite imagen del ente
     * @param velocidad velocidad a la que se mueve el ente
     *
     */
    public Ente(int hp, Punto punto, SpriteDinamica sprite, float velocidad) {
        this.hp = hp;
        this.punto = punto;
        this.sprite = sprite;
        this.velocidad = velocidad;
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
    public SpriteDinamica getSpriteDinamica() {
        return sprite;
    }

    /**
     * Set the value of sprite
     *
     * @param sprite new value of sprite
     */
    public void setSpriteDinamica(SpriteDinamica sprite) {
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

}
