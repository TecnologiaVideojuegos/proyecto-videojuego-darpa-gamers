/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package materials;

/**
 *
 * @author David
 */
public class Buff {
    
    //Tiempo maximo del buff
    private int time_max_buff;
    
    //Estado del buff, true activado, false desactivado
    private boolean state;
    
    //Tiempo actual del buff
    private long timer_buff;
    
    /**
     *  Constructor de la clase Buff
     * 
     *  @param time_max Tiempo máximo del buff
     */
    public Buff(int time_max){
        this.state = false;
        this.timer_buff = 0;
        this.time_max_buff = time_max;
    }
    
    /**
     * 
     * @return el timer del estado del buff
     */
    public long getTimerEstadoBuff(){
        return this.timer_buff;
    }
    
    /**
     * 
     * @param ms a ser añadidos al timer
     */
    public void setTimerEstadoBuff( long ms){
        this.timer_buff = ms;
    }
    
    /**
     * 
     * @return el estado del buff 
     */
    public boolean getEstadoBuff(){
        return this.state;
    }
    
    /**
     * 
     * @param estado para el buff
     */
    public void setEstadoBuff(boolean estado){
        this.state = estado;
    }
    
    /**
     * 
     * @return El tiempo maximo del buff
     */
    public int getMaxTimeBuff(){
        return this.time_max_buff;
    }
    
    /**
     * 
     * @param ms tiempo para establecer el maximo tiempo
     */
    public void setMaxTimeBuff(int ms){
        this.time_max_buff = ms;
    }
    
    /**
     * 
     * 
     * @param delta tiempo en ms desde la ultima que se hizo 
     *              un update en el nivel.
     */
    public void controlBuff(int delta){
        
        if(this.getEstadoBuff()){
            this.setTimerEstadoBuff(this.getTimerEstadoBuff() + delta );
            
            // Despues de 20 segundos se termin el buff
            if( this.getTimerEstadoBuff() > this.getMaxTimeBuff()){
                setEstadoBuff(false);
            }
        }
    }
    
}
