/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package location;

/**
 *
 * @author Senapi Aroal
 */
public class Vector {
    
    //Datos de los puntos
    private Punto destino;   
    private Punto origen;

    /**
     * Constructor de la clase Vector
     * 
     * @param origen punto de origen
     * @param destino punto de destino
     */
    public Vector(Punto origen,Punto destino){
        this.origen = origen;
        this.destino = destino;
    }
    
    /**
     * Get the value of origen
     *
     * @return the value of origen
     */
    public Punto getOrigen() {
        return origen;
    }

    /**
     * Set the value of origen
     *
     * @param origen new value of origen
     */
    public void setOrigen(Punto origen) {
        this.origen = origen;
    }


    /**
     * Get the value of destino
     *
     * @return the value of destino
     */
    public Punto getDestino() {
        return destino;
    }

    /**
     * Set the value of destino
     *
     * @param destino new value of destino
     */
    public void setDestino(Punto destino) {
        this.destino = destino;
    }

    /**
     * Set the different between origin and destiny
     * 
     * @return the value of x
     */
    public float getX(){
        return destino.getX() - origen.getX();
    }
    
    /**
     * Set the different between origin and destiny
     * 
     * @return the value of y
     */
    public float getY(){
        return destino.getY() - origen.getY();
    }
    
    /**
     * Set the module of de origin and destiiny point
     * 
     * @return the value of x
     */
    public float getModulo(){
        double x = (double) this.getX();
        double y = (double) this.getY();
        return (float) Math.sqrt(x*x + y*y);
    }
}
