/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package characters;

import location.Punto;
import location.Vector;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;

/**
 *
 * @author Senapi Aroal
 */
public class Monstruo extends Ente{
    
    private String comportamiento;
    private Circle rango;

    /**
     * Constructor de la clase Monstruo
     * 
     * @param hp vida del ente
     * @param punto lugar del mapa donde se posiciona
     * @param sprite imagen del ente
     * @param velocidad velocidad a la que se mueve el ente
     * @param direccion
     * @param rango
     * @param  comportamiento establece el comportamiento del monstruo
     * 
     */
    public Monstruo(int hp, Punto punto, SpriteSheet sprite, float velocidad,int direccion,int rango, String comportamiento) {
        super(hp, punto, sprite, velocidad,direccion);
        try{
            this.comportamiento = comportamiento;
            this.rango = new Circle(punto.getX(),punto.getY(),rango); //creamos el rango que tendrá el monstruo
        }catch(Exception ex){}
    }
    
    /**
     * Get the value of rango
     *
     * @return the value of rango
     */
    public Circle getRango() {
        return rango;
    }

    /**
     * Set the value of rango
     *
     * @param rango new value of rango
     */
    public void setRango(Circle rango) {
        this.rango = rango;
    }

    /**
     * Get the value of comportamiento
     *
     * @return the value of comportamiento
     */
    public String getComportamiento() {
        return comportamiento;
    }

    /**
     * Set the value of comportamiento
     *
     * @param comportamiento new value of comportamiento
     */
    public void setComportamiento(String comportamiento) {
        this.comportamiento = comportamiento;
    }

    public void actualizarRango(){
        this.rango.setCenterX(this.getPunto().getX());
        this.rango.setCenterY(this.getPunto().getY());
    }
    
    /**
     * Realiza un movimiento al azar en caso de ser pasivo o de bajo nivel
     *
     * @param j
     */
    public void realizarMovimiento(Jugador j,int delta){
        this.detectarJugador(j);
        this.actualizarRango();
        //Random rand = new Random();  
        if(this.getComportamiento().equals("Hostil")){
            this.movimientoHostil(j);
        }
    } 
    
    /**
     * Se encarga de detectar si está al alcance del jugador
     *
     * @param j
     */
    public void detectarJugador(Jugador j){
        if(rango.intersects(j.getPersDown()) || rango.intersects(j.getPersL()) || rango.intersects(j.getPersR()) || rango.intersects(j.getPersUp())){
            this.setComportamiento("Hostil");
        }else{
            this.setComportamiento("Pasivo");
        }
    }
    
    public void movimientoPasivo(){
        
    }
    
    public void movimientoHostil(Jugador j){
        float jugadorX =  j.getPunto().getX();
        float jugadorY =  j.getPunto().getY();
        System.out.println("jugadorX: " + jugadorX +  " jugadorY: " + jugadorY);
              
        float enemigoX = this.getPunto().getX();
        float enemigoY = this.getPunto().getY();
        System.out.println("enemigoX: " + enemigoX +  " enemigoY: " + enemigoY);
    }
    
    public void mover(int reloj){
        if(reloj > 2000){
            reloj = 0;
        }
    }
}
