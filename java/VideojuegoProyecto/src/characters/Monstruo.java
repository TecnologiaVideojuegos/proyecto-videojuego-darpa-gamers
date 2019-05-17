/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package characters;

import java.util.ArrayList;
import location.*;
import map.Escena;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

/**
 *
 * @author Senapi Aroal
 * @author Davidcawork
 */
public class Monstruo extends Ente{
    
    private String comportamiento;
    private Punto respawn;
    private Circle rango;
    private Vector movimiento;    
    private final Rectangle PersUp,PersDown,PersL,PersR;

    /**
     * Constructor de la clase Monstruo
     * 
     * @param hp vida del ente
     * @param punto lugar del mapa donde se posiciona
     * @param sprite imagen del ente
     * @param velocidad velocidad a la que se mueve el ente
     * @param direccion
     * @param rango
     * @param comportamiento establece el comportamiento del monstruo
     * @param danio daño del ente
     * 
     */
    public Monstruo(int hp, Punto punto, SpriteSheet sprite, float velocidad,int direccion,int rango, String comportamiento,int danio) {
        super(hp, punto, sprite, velocidad,direccion, danio);
        this.PersUp = new Rectangle((this.getPunto().getX()+2),this.getPunto().getY(),12,1);
        this.PersDown = new Rectangle((this.getPunto().getX()+2),(this.getPunto().getY()+16),12,1);
        this.PersL = new Rectangle(this.getPunto().getX(),(this.getPunto().getY()+2),1,12);
        this.PersR = new Rectangle((this.getPunto().getX()+16),(this.getPunto().getY()+2),1,12);
        try{
            this.movimiento = new Vector(new Punto(0,0));
            this.comportamiento = comportamiento;
            this.rango = new Circle(punto.getX()+sprite.getHeight()/2,punto.getY()+sprite.getWidth()/2,rango); //creamos el rango que tendrá el monstruo
            this.respawn = punto;
        }catch(Exception ex){}
    }

    /**
     * Get the value of respawn
     *
     * @return the value of respawn
     */
    public Punto getRespawn() {
        return respawn;
    }

    /**
     * Set the value of respawn
     *
     * @param respawn new value of respawn
     */
    public void setRespawn(Punto respawn) {
        this.respawn = respawn;
    }

    /**
     * Obtiene el polígono superior del personaje
     *
     * @return el polígono superior del personaje
     */
    public Rectangle getPersUp() {
        return PersUp;
    }

    /**
     * Obtiene el polígono inferior del personaje
     *
     * @return el polígono inferior del personaje
     */
    public Rectangle getPersDown() {
        return PersDown;
    }

    /**
     * Obtiene el polígono izquierdo del personaje
     *
     * @return el polígono izquierdo del personaje
     */
    public Rectangle getPersL() {
        return PersL;
    }

    /**
     * Obtiene el polígono derecho del personaje
     *
     * @return el polígono derecho del personaje
     */
    public Rectangle getPersR() {
        return PersR;
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
     * @param escena
     * @param delta
     * @param reloj
     */
    public void realizarMovimiento(Jugador j,Escena escena,int delta,int reloj){
        this.detectarJugador(j); 
        if(this.getComportamiento().equals("Hostil")){
            this.movimientoHostil(j,escena,delta);
        }else{
            this.movimientoPasivo(escena,delta,reloj);    
        }
        this.detectarColisionEnemigo(escena.getEnemigos());
        this.corregirBug(escena);
        this.actualizarPosicionPoligono();
        this.actualizarRango();
    } 
    
    public void corregirBug(Escena escena){

        if(!escena.getMapa_colision().contains(this.getPersUp()) && !escena.getMapa_colision().contains(this.getPersDown()) && !escena.getMapa_colision().contains(this.getPersL()) && !escena.getMapa_colision().contains(this.getPersR()) ){
            this.setPunto(this.getRespawn());
            
        }else{
            for(int i = 0;i<escena.getMapa_objetos().size();i++){
                if(escena.getMapa_objetos().get(i).contains(this.getPersUp()) && escena.getMapa_objetos().get(i).contains(this.getPersDown()) && escena.getMapa_objetos().get(i).contains(this.getPersL()) && escena.getMapa_objetos().get(i).contains(this.getPersR()) ){
                    this.setPunto(this.getRespawn());
                }
            }
        }
    }
    
    public void detectarColisionEnemigo(ArrayList<Monstruo> mon){
        for(int i = 0;i<mon.size();i++){
            if(this.getPersDown().intersects(mon.get(i).getPersUp())){
                this.setPunto(new Punto(this.getPunto().getX(),this.getPunto().getY()-10));
            }else if(this.getPersUp().intersects(mon.get(i).getPersDown())){
                this.setPunto(new Punto(this.getPunto().getX(),this.getPunto().getY()+10));
            }else if(this.getPersL().intersects(mon.get(i).getPersR())){
                this.setPunto(new Punto(this.getPunto().getX()+10,this.getPunto().getY()));
            }else if(this.getPersR().intersects(mon.get(i).getPersL())){
                this.setPunto(new Punto(this.getPunto().getX()-10,this.getPunto().getY()));
            }
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
    
    public void movimientoPasivo(Escena escena,int delta,int reloj){
            //case Up
            if(reloj > 1500){
                if(!escena.colisionConPoligonos(this.getPersUp())){
                    this.move(delta,0,0,0,-this.getVelocidad());
                    this.setDireccion(0);
                }
            //case Left
            }else if(reloj > 1000){
                if(!escena.colisionConPoligonos(this.getPersL())){
                    this.move(delta,0,0,-this.getVelocidad(),0);
                    this.setDireccion(2);
                }
            //case Down 
            }else if(reloj > 500){
                if(!escena.colisionConPoligonos(this.getPersDown())){
                    this.move(delta,0,0,0,this.getVelocidad());
                    this.setDireccion(1);
                }
            //case Right
            }else{
                if(!escena.colisionConPoligonos(this.getPersR())){
                    this.move(delta,0,0,this.getVelocidad(),0);
                    this.setDireccion(3);
                }
            }
    }
    
    public void movimientoHostil(Jugador j,Escena escena,int delta){
        if(escena.colisionConPoligonos(this.getPersDown()) && escena.colisionConPoligonos(this.getPersL())){
        }else if(escena.colisionConPoligonos(this.getPersDown()) &&escena.colisionConPoligonos(this.getPersR())){            
        }else if(escena.colisionConPoligonos(this.getPersUp()) && escena.colisionConPoligonos(this.getPersL())){           
        }else if(escena.colisionConPoligonos(this.getPersUp()) && escena.colisionConPoligonos(this.getPersR())){           
        }else if(escena.colisionConPoligonos(this.getPersUp())){
            if(j.getPunto().getY() >= this.getPunto().getY()){
                this.move(delta,0,this.getPunto().getY()*2,0,j.getPunto().getY()*2);
            }else{
                this.move(delta,this.getPunto().getX()*2,0,j.getPunto().getX()*2,0);
            }
        }else if(escena.colisionConPoligonos(this.getPersDown())){
            if(j.getPunto().getY() <= this.getPunto().getY()){
                this.move(delta,0,this.getPunto().getY()*2,0,j.getPunto().getY()*2);
            }else{
                this.move(delta,this.getPunto().getX()*2,0,j.getPunto().getX()*2,0);               
            }
        }else if(escena.colisionConPoligonos(this.getPersL())){
            if(j.getPunto().getX() >= this.getPunto().getX()){
                this.move(delta,this.getPunto().getX()*2,0,j.getPunto().getX()*2,0);
            }else{
                this.move(delta,0,this.getPunto().getY()*2,0,j.getPunto().getY()*2);
            }
        }else if(escena.colisionConPoligonos(this.getPersR())){
            if(j.getPunto().getX() <= this.getPunto().getX()){
                this.move(delta,this.getPunto().getX()*2,0,j.getPunto().getX()*2,0);
            }else{
                this.move(delta,0,this.getPunto().getY()*2,0,j.getPunto().getY()*2);
            }
        }else if(escena.colisionConPoligonos(this.getPersUp())){
            if(j.getPunto().getY() >= this.getPunto().getY()){
                this.move(delta,0,this.getPunto().getY()*2,0,j.getPunto().getY()*2);
            }else{
                this.move(delta,this.getPunto().getX()*2,0,j.getPunto().getX()*2,0);
            }
        }else if(escena.colisionConPoligonos(this.getPersDown())){
            if(j.getPunto().getY() <= this.getPunto().getY()){
                this.move(delta,0,this.getPunto().getY()*2,0,j.getPunto().getY()*2);
            }else{
                this.move(delta,this.getPunto().getX()*2,0,j.getPunto().getX()*2,0);               
            }
        }else if(escena.colisionConPoligonos(this.getPersL())){
            if(j.getPunto().getX() >= this.getPunto().getX()){
                this.move(delta,this.getPunto().getX()*2,0,j.getPunto().getX()*2,0);
            }else{
                this.move(delta,0,this.getPunto().getY()*2,0,j.getPunto().getY()*2);
            }
        }else if(escena.colisionConPoligonos(this.getPersR())){
            if(j.getPunto().getX() <= this.getPunto().getX()){
                this.move(delta,this.getPunto().getX()*2,0,j.getPunto().getX()*2,0);
            }else{
                this.move(delta,0,this.getPunto().getY()*2,0,j.getPunto().getY()*2);
            }
        }else{
            this.move(delta,this.getPunto().getX(),this.getPunto().getY(),j.getPunto().getX(),j.getPunto().getY());
        }
    }
    
    public void actualizarPosicion(int delta){
        float x = this.getPunto().getX() + movimiento.getX() * ((float) delta/1000);
        float y = this.getPunto().getY() + movimiento.getY() * ((float) delta/1000);
        this.setPunto(new Punto(x,y));
        this.actualizarPosicionPoligono();
    }
    
    public void move(int delta,float ox,float oy,float dx,float dy){
        movimiento.setOrigen(new Punto(ox,oy));
        movimiento.setDestino(new Punto(dx,dy));
        this.actualizarPosicion(delta);
        this.actualizarRango();
    }
    
    /**
     * Actualiza la posición de los polígonos de acuerdo a la posición del personaje
     *
     */
    public void actualizarPosicionPoligono(){
        this.getPersL().setX(this.getPunto().getX());
        this.getPersR().setX((this.getPunto().getX()+16));
        this.getPersUp().setX(this.getPunto().getX()+2);
        this.getPersDown().setX((this.getPunto().getX()+2));
        this.getPersL().setY((this.getPunto().getY()+2));
        this.getPersR().setY((this.getPunto().getY()+2));
        this.getPersUp().setY((this.getPunto().getY()));
        this.getPersDown().setY((this.getPunto().getY()+16));
    }
    
}
