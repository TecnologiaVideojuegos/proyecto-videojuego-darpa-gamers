/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import location.Punto;
import org.newdawn.slick.Image;

/**
 *
 * @author Davidcawork
 */
public class Historia {
    private Image sprite_historia;
    private long timer_historia;
    private int max_time;
    private boolean estado_historia;
    private Punto punto_historia;
    
    public Historia(Punto punto, int time, Image image){
        this.estado_historia = true;
        this.sprite_historia = image;
        this.punto_historia = punto;
        this.max_time = time;
        this.timer_historia = 0;
        
    }
    
    /**
     * 
     * Metodo para imprimir la historia 
     * 
     */
    public void imprimirHistoria(){
        
        if(this.estado_historia){
            this.sprite_historia.draw(this.punto_historia.getX(), this.punto_historia.getY());
        }
    
    }
    
    /**
     * 
     * Metodo para controlar la historia del nivel
     * 
     * @param delta tiempo desde el último update
     */
    public void controlHistoria(int delta){
        
        if(this.estado_historia){
            
            this.timer_historia += delta ;
            
           
            if( this.timer_historia > this.max_time){
                this.estado_historia = false;
                
            }
        }
    }
}
