/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author David
 */
public final class Animacion {
    
    private final SpriteSheet hoja_sprite;
    private final Animation [] animaciones;
    private final int frames_per_sprite;

    /**
     * 
     * Contructor de la clase animacion
     * 
     * @param hoja          spritesheet
     * @param frames        numero de frames por animacion
     */
    public Animacion(SpriteSheet hoja, int frames){
        
        this.hoja_sprite = hoja;
        this.frames_per_sprite = frames;
        
        this.animaciones = new Animation[4];
        for(int i = 0; i < 4; i++)
            this.animaciones[i] = new Animation(false);
        
        this.getAnimaciones();
    }
    
    /**
     * 
     * Metodo para cargar las cuatro animaciones de movimiento dado un spritesheet, duracion de cada
     * frame 100ms
     * 
     */
    public void getAnimaciones(){
    
        for( int i = 0; i < 4; i++){
            for(int j = 0; j < this.frames_per_sprite; j++){
                //100 ms cada frame
                this.animaciones[i].addFrame( this.hoja_sprite.getSprite(j, i), 200);
            }    
        }
    }
    
    /**
     * 
     * @param id_animacion == 0 -- abajo
     *                        1 -- arriba
     *                        2 -- drch
     *                        3 -- izq
     *  
     * @return la animacion solicitada
     */
    public Animation getAnimacion(int id_animacion){
        
        try{
            
            return this.animaciones[id_animacion];
        }
        catch(Exception e){
            //Por defecto si falla daos la de abajo
            System.out.println("Id_animacion invalido");
            return  this.animaciones[0];
        }    
    }
    
    /**
     * 
     * @param direccion del personaje
     * @return imagen estatica del personaje
     */
    public Image getImagenEstatica(int direccion){
        
        try{
            return this.hoja_sprite.getSprite(0, direccion);
        }catch(Exception e){
            System.out.println("Direccion pasada invalida");
            return this.hoja_sprite.getSprite(0, 0);
        }
    }
    
    
}
