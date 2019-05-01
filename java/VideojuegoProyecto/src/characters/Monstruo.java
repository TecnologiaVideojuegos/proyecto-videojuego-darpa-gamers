/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package characters;

import location.*;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

/**
 *
 * @author Senapi Aroal
 */
public class Monstruo extends Ente{
    
    private String comportamiento;
    private Circle rango;
    private Shape poligono;
    private Vector movimiento;

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
            this.poligono = new Rectangle(punto.getX(),punto.getY(),sprite.getHeight(),sprite.getWidth());
            this.movimiento = new Vector(new Punto(0,0));
            this.comportamiento = comportamiento;
            this.rango = new Circle(punto.getX()+sprite.getHeight()/2,punto.getY()+sprite.getWidth()/2,rango); //creamos el rango que tendrá el monstruo
        }catch(Exception ex){}
    }

    /**
     * Get the value of poligono
     *
     * @return the value of poligono
     */
    public Shape getPoligono() {
        return poligono;
    }

    /**
     * Set the value of poligono
     *
     * @param poligono new value of poligono
     */
    public void setPoligono(Shape poligono) {
        this.poligono = poligono;
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
        this.rango.setCenterX(this.getPunto().getX()+this.getSpriteSheet().getHeight()/2);
        this.rango.setCenterY(this.getPunto().getY()+this.getSpriteSheet().getWidth()/2);
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
        int numAleatorio = 0;
        switch(numAleatorio){
            //case Up
            case 0:
                movimiento.setDestino(new Punto(0,-this.getVelocidad()));
                this.setDireccion(0);
                break;
            //case Down 
            case 1:
                movimiento.setDestino(new Punto(0,this.getVelocidad()));
                this.setDireccion(1);
                break;
            //case Left
            case 2:
                movimiento.setDestino(new Punto(-this.getVelocidad(),0));
                this.setDireccion(2);
                break;
            //case Right
            case 3:
                movimiento.setDestino(new Punto(this.getVelocidad(),0));
                this.setDireccion(3);
                break;
            //error
            default:
                System.out.println("ERROR");
        }
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
    
    public void detectarColision(Shape poligono,int delta){
        if(!this.getPoligono().intersects(poligono)){
            this.actualizarPosicion(delta);
            this.actualizarRango();
        }
    }
    
    public void actualizarPosicion(int delta){
        float x = this.getPunto().getX() + movimiento.getX() * ((float) delta/1000);
        float y = this.getPunto().getY() + movimiento.getY() * ((float) delta/1000);
        this.setPunto(new Punto(x,y));
        this.getPoligono().setX(x);
        this.getPoligono().setY(y);
    }
    
    public void move(int delta,float dx,float dy){
        movimiento.setOrigen(new Punto(this.getPunto().getX(),this.getPunto().getY()));
        movimiento.setDestino(new Punto(dx,dy));
        this.setDireccion(0);
        this.actualizarPosicion(delta);
        this.actualizarRango();
    }
}
