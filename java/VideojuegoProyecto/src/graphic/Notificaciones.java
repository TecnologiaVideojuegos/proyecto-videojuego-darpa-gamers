/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 *  Notificador con funcionamiento de LIFO 
 * 
 * @author Davidcawork
 */
public class Notificaciones {
    
    
    private Image [] listaNotificaciones;
    private final ArrayList<Image> colaNotificaciones;
    private long timer_not;
    private final int duracion_notf;
    private boolean wait_state;
    private long timer_not_wait_state;
    private final int duracion_espera;
    
    /**
     * 
     * Contructur de la clase notificaciones
     * 
     * @param duracion durcion de las notificaciones
     */
    public Notificaciones(int duracion) throws SlickException{
        this.listaNotificaciones = new Image[6];
        this.colaNotificaciones = new ArrayList<>();
        this.timer_not = 0;
        this.duracion_notf = duracion;
        this.wait_state = false;
        this.duracion_espera = 750;
        this.timer_not_wait_state = 0;
        
        cargar_imagenes_notficaciones();
    }
    
    /**
     * 
     * @return el timepo actual del timer entre notf
     */
    public long getTimerNotfWS(){
        return this.timer_not_wait_state;
    }
    
    /**
     * 
     * @param ms establecer un tiempo entre notificaciones
     */
    public void setTimerNotfWS( long ms){
        this.timer_not_wait_state = ms;
    }
    
    /**
     * 
     * @return el estado de espera de las notificacion entre una y otra 
     */
    public boolean getWaitState(){
        return this.wait_state;
    }
    
    /**
     * 
     * @param state a asignar al estado de espera
     */
    public void setWaitState(boolean state){
        this.wait_state = state;
    }
    
    /**
     * 
     * @return array de imagenes de las notificaciones
     */
    public Image[] getImgNotf(){
        return this.listaNotificaciones;
    }
    
    /**
     * 
     * @return el timepo actual del timer de notf
     */
    public long getTimerNotf(){
        return this.timer_not;
    }
    
    /**
     * 
     * @param ms establecer un tiempo de notificaciones
     */
    public void setTimerNotf( long ms){
        this.timer_not = ms;
    }
    
    /**
     * 
     * @throws SlickException controlar una mala carga del obj img
     */
    public void cargar_imagenes_notficaciones() throws SlickException {
        for(int i=0; i < 5; i++){
            this.listaNotificaciones[i] = (new Image("./res/grafico/notificaciones_pociones/not_" + (i+1) +".png"));      
        }
        this.listaNotificaciones[5] = (new Image("./res/grafico/notificaciones_varios/not_1.png")); 
        
    }
    
    /**
     * 
     * @param img a añadir a la cola de notificaciones
     */
    public void aniadirNotificacion(Image img){
        this.colaNotificaciones.add(img);
    }
    
    /**
     * 
     * Elimina una notificacion ya mostrada
     * 
     */
    public void eliminarNotificacion(){
        if(!this.colaNotificaciones.isEmpty()){
            this.colaNotificaciones.remove(this.colaNotificaciones.size() - 1);
        }
    }
    
    /**
     * 
     * Imprime una notificacion
     * 
     */
    public void imprimirNotificaciones(){
        if(!this.colaNotificaciones.isEmpty()&& !this.getWaitState()){
            this.colaNotificaciones.get(this.colaNotificaciones.size() - 1).draw(20.0f, 640.0f);
        }
    }
    
    /**
     * 
     * @param delta tiempo desde el ultimo update 
     */
    public void controlNotif(int delta){
        
        if(!this.colaNotificaciones.isEmpty() && !this.getWaitState()){
            this.setTimerNotf(this.getTimerNotf() + delta );
            
            // Despues de x seg eliminamos la notificacion
            if( this.getTimerNotf() > this.duracion_notf){
                this.eliminarNotificacion();
                this.setTimerNotf(0);
                this.setWaitState(true);
            }
        }
    }
    
    /**
     * 
     * @param delta tiempo desde el ultimo update 
     */
    public void controlEstadoEspera(int delta){
        
        if(this.getWaitState()){
            
            this.setTimerNotfWS(this.getTimerNotfWS() + delta );
            
            // Despues de x seg dejamos mostrar la notificacion
            if( this.getTimerNotfWS() > this.duracion_espera){
                this.setWaitState(false);
                this.setTimerNotfWS(0);
            }
        }
    }
    
    
}
