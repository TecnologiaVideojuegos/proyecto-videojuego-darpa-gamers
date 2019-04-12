/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parte_logica;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Senapi Aroal
 */
public class Sprite {
    
    private final Animation anim;   
    private final String url;
    private final int width;
    private final int height; 
    private final SpriteSheet sprite;

    /**
     * Constructor de la clase Sprite
     * 
     * @param url ruta donde se encuentra la imagen a cargar
     * @param width tamaño en anchura de la imagen a cargar
     * @param height tamaño en altura de la imagen a cargar
     *
     */
    public Sprite(String url, int width, int height) throws SlickException{
        this.url = url;
        this.width = width;
        this.height = height;
        this.sprite = new SpriteSheet(url,width,height);
        this.anim = new Animation(sprite,100);
    }

    /**
     * Get the value of sprite
     *
     * @return the value of sprite
     */
    public SpriteSheet getSprite() {
        return sprite;
    }
   
    /**
     * Get the value of width
     *
     * @return the value of width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the value of height
     *
     * @return the value of height
     */
    public int getHeight() {
        return height;
    }
   
    /**
     * Get the value of url
     *
     * @return the value of url
     */
    public String getUrl() {
        return url;
    }
    
    /**
     * Get the value of anim
     *
     * @return the value of anim
     */
    public Animation getAnim() {
        return anim;
    }

}
