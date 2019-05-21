/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package materials;

import characters.Jugador;
import java.util.logging.Level;
import java.util.logging.Logger;
import map.Escena;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Senapi Aroal
 */
public class Varita {
    
    //Generador de flechas
    private ControladorFlechas flecha;
    
    //Municion de la Varita
    private int municion;
    
    public Varita(int municion){
        try {
            this.municion = municion;
            flecha = new ControladorFlechas();
        } catch (SlickException ex) {
            Logger.getLogger(Varita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void dispararFlecha(Input entrada,Jugador j){
        try{
            controlDisparo(entrada,j);
        }catch(SlickException ex){}  
    }

    public ControladorFlechas getFlecha() {
        return flecha;
    }
    
    /**
     * Get the value of municion
     *
     * @return the value of municion
     */
    public int getMunicion() {
        return municion;
    }

    /**
     * Set the value of municion
     *
     * @param municion new value of municion
     */
    public void setMunicion(int municion) {
        this.municion = municion;
    }
    
    /**
     * Añade tanta munición como se especifica en el parámetro
     * 
     * @param municion cantidad añadida
     */
    public void addMunicion(int municion){
        this.municion+=municion;
    }
   
    /**
     * Decrementa en uno la mmunición cuando se usa
     * 
     */
    public void decrementarMunicion(){
        this.municion--;
    }
    
    public void controlDisparo(Input entrada,Jugador j) throws SlickException{
        decrementarMunicion();
        if(entrada.isKeyDown(Input.KEY_RIGHT) && entrada.isKeyDown(Input.KEY_UP)){
            
            flecha.addFlecha(j.getNivelJugador(),3,j.getPersUp().getMaxX(),j.getPersR().getMaxY(),300,-300,j.getPersUp().getMaxX()+7,j.getPersR().getMaxY(),10,24);
            j.setDireccion(0);
            
        }else if(entrada.isKeyDown(Input.KEY_RIGHT) && entrada.isKeyDown(Input.KEY_DOWN)){
            
            flecha.addFlecha(j.getNivelJugador(),1,j.getPersDown().getMinX(),j.getPersR().getMinY(),300,300,j.getPersDown().getMinX()+7,j.getPersR().getMinY(),10,24);
            j.setDireccion(0);
            
        }else if(entrada.isKeyDown(Input.KEY_LEFT) && entrada.isKeyDown(Input.KEY_UP)){
            
            flecha.addFlecha(j.getNivelJugador(),3,j.getPersUp().getMinX(),j.getPersL().getMaxY(),-300,-300,j.getPersUp().getMaxX()-7,j.getPersR().getMaxY(),10,24);
            j.setDireccion(1);
            
        }else if(entrada.isKeyDown(Input.KEY_LEFT) && entrada.isKeyDown(Input.KEY_DOWN)){
            
            flecha.addFlecha(j.getNivelJugador(),1,j.getPersDown().getX(),j.getPersDown().getY(),-300,300,j.getPersDown().getX()+7,j.getPersDown().getY(),10,24);
            j.setDireccion(1);
            
        }else if(entrada.isKeyDown(Input.KEY_RIGHT)){
            
            flecha.addFlecha(j.getNivelJugador(),0,j.getPersR().getX(),j.getPersR().getY(),300,0,j.getPersR().getX(),j.getPersR().getY()+7,24,10);
            j.setDireccion(0);
            
        }else if(entrada.isKeyDown(Input.KEY_LEFT)){
            
            flecha.addFlecha(j.getNivelJugador(),2,j.getPersL().getX(),j.getPersL().getY(),-300,0,j.getPersL().getX(),j.getPersL().getY()+7,24,10);
            j.setDireccion(1);
            
        }else if(entrada.isKeyDown(Input.KEY_UP)){
            
            flecha.addFlecha(j.getNivelJugador(),3,j.getPersUp().getX()-5,j.getPersUp().getY(),0,-300,j.getPersUp().getX()+2,j.getPersUp().getY(),10,24);
            j.setDireccion(2);
            
        }else if(entrada.isKeyDown(Input.KEY_DOWN)){
            
            flecha.addFlecha(j.getNivelJugador(),1,j.getPersDown().getX()-5,j.getPersDown().getY(),0,300,j.getPersDown().getX()+2,j.getPersDown().getY(),10,24);
            j.setDireccion(3);
            
        }else{
            switch(j.getDireccion()){
                //Derecha
                case 0:
                    flecha.addFlecha(j.getNivelJugador(),0,j.getPersR().getX(),j.getPersR().getY(),300,0,j.getPersR().getX(),j.getPersR().getY()+7,24,10);
                    break;
                //Izquierda
                case 1:
                    flecha.addFlecha(j.getNivelJugador(),2,j.getPersL().getX(),j.getPersL().getY(),-300,0,j.getPersL().getX(),j.getPersL().getY()+7,24,10);
                    break;
                //Arriba
                case 2:
                    flecha.addFlecha(j.getNivelJugador(),3,j.getPersUp().getX()-5,j.getPersUp().getY(),0,-300,j.getPersUp().getX()+2,j.getPersUp().getY(),10,24);
                    break;
                //Abajo
                case 3:
                    flecha.addFlecha(j.getNivelJugador(),1,j.getPersDown().getX()-5,j.getPersDown().getY(),0,300,j.getPersDown().getX()+2,j.getPersDown().getY(),10,24);
                    break;
                default:
                    System.out.println("Dirección errónea");   
            }
        }
        
    }
    
    public void actualizarProyectil(GameContainer container,Escena escena,int delta){
        flecha.update(container,escena,delta);
    }
    
}
