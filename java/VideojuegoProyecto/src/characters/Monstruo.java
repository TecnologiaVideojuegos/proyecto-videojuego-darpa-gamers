/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package characters;

import graphic.Animacion;
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
    private final Rectangle colision;
    //Animaciones
    private final Animacion animacion_enemigo;

    /**
     * Constructor de la clase Monstruo
     * 
     * @param hp vida del ente
     * @param punto lugar del mapa donde se posiciona
     * @param velocidad velocidad a la que se mueve el ente
     * @param direccion
     * @param rango
     * @param comportamiento establece el comportamiento del monstruo
     * @param danio daño del ente
     * 
     */
    public Monstruo(int hp, Punto punto, float velocidad,int direccion,int rango, String comportamiento,int danio) throws SlickException {
        super(hp, punto, velocidad,direccion, danio);
        this.PersUp = new Rectangle((this.getPunto().getX()+2),this.getPunto().getY(),12,1);
        this.PersDown = new Rectangle((this.getPunto().getX()+2),(this.getPunto().getY()+32),12,1);
        this.PersL = new Rectangle(this.getPunto().getX(),(this.getPunto().getY()),1,30);
        this.PersR = new Rectangle((this.getPunto().getX()+16),(this.getPunto().getY()),1,30);
        this.colision = new Rectangle(this.getPunto().getX(),this.getPunto().getY()-16,14,46);
        this.animacion_enemigo = new Animacion(new SpriteSheet("./res/grafico/enemigo/enemigo_spritesheet.png",32,50), 9);
        
        try{
            this.movimiento = new Vector(new Punto());
            this.comportamiento = comportamiento;
            this.rango = new Circle(this.PersUp.getX()+(this.getPersUp().getWidth()/2),this.getPersUp().getY()+10,rango); //creamos el rango que tendrá el monstruo
            this.respawn = punto;
            }catch(Exception ex){
            
        }
    }

    public Rectangle getColision(){
        return colision;
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
        this.rango.setCenterX(this.PersUp.getX()+(this.getPersUp().getWidth()/2));
        this.rango.setCenterY(this.getPersUp().getY()+10);
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
        if(this.getComportamiento().equals("Hostil") || this.getComportamiento().equals("Perseguir")){
            this.movimientoHostil(j,escena,delta);
        }else{
            this.movimientoPasivo(escena,delta,reloj);    
        }
        this.detectarColisionEnemigo(escena.getEnemigos());
        this.controlAnimacionesEnemigo(delta);
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
        if(this.getComportamiento().equals("Perseguir")){
        }else if(rango.intersects(j.getPersDown()) || rango.intersects(j.getPersL()) || rango.intersects(j.getPersR()) || rango.intersects(j.getPersUp())){
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
            this.setComportamiento("Pasivo");
        }else if(escena.colisionConPoligonos(this.getPersDown()) &&escena.colisionConPoligonos(this.getPersR())){         
            this.setComportamiento("Pasivo");
        }else if(escena.colisionConPoligonos(this.getPersUp()) && escena.colisionConPoligonos(this.getPersL())){    
            this.setComportamiento("Pasivo");
        }else if(escena.colisionConPoligonos(this.getPersUp()) && escena.colisionConPoligonos(this.getPersR())){   
            this.setComportamiento("Pasivo");
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
            if(j.getPunto().getX() <= this.getPunto().getX()){
                this.setDireccion(2);
            }else{
                this.setDireccion(3);
            }
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
        this.getPersL().setY((this.getPunto().getY()));
        this.getPersR().setY((this.getPunto().getY()));
        this.getPersUp().setY((this.getPunto().getY()));
        this.getPersDown().setY((this.getPunto().getY()+32));
        this.getColision().setX(this.getPunto().getX());
        this.getColision().setY(this.getPunto().getY()-16);
    }
    
    /**
     * 
     * Metodo para pintar las animaciones o imagenes estaticas
     * 
     */
    public void imprimirEnemigo(){
        
        /*  Dir abajo ( dir == 1) == (id_animacion == 0)
            Dir arriba( dir == 0) == (id_animacion == 1)
            Dir izq   ( dir == 2) == (id_animacion == 3)
            Dir drch  ( dir == 3) == (id_animacion == 2)
        
        */
        
        if(this.getDireccion() == 0){
            this.animacion_enemigo.getAnimacion(1).draw( super.getPunto().getX()-10.0f, super.getPunto().getY()-18.0f);
        }else if(this.getDireccion() == 1){
            this.animacion_enemigo.getAnimacion(0).draw( super.getPunto().getX()-13.0f, super.getPunto().getY()-18.0f);
        }else if(this.getDireccion() == 2){
            this.animacion_enemigo.getAnimacion(3).draw( super.getPunto().getX()-10.0f, super.getPunto().getY()-18.0f);
        }else if(this.getDireccion() == 3){
            this.animacion_enemigo.getAnimacion(2).draw( super.getPunto().getX()-10.0f, super.getPunto().getY()-18.0f);
        }
    
    }
    
    
    public void controlAnimacionesEnemigo(int delta){
        switch (this.getDireccion()) {
            case 0:
                this.animacion_enemigo.getAnimacion(1).update(delta);
                break;
            case 1:
                this.animacion_enemigo.getAnimacion(0).update(delta);
                break;
            case 2:
                this.animacion_enemigo.getAnimacion(3).update(delta);
                break;
            case 3:
                this.animacion_enemigo.getAnimacion(2).update(delta);
                break;
            default:
                break;
        }
    }
    
    
    
}
