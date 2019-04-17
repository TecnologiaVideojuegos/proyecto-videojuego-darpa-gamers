/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package characters;

import materials.Inventario;
import location.Punto;
import imagen.Sprite;

/**
 * @version 0.0.10
 * @author Senapi Aroal
 */
public class Jugador extends Ente{
    
    private int experiencia;
    private Inventario inventario;
    private int nivelJugador;
    private int nivelMapa;
    private int escenario;
     
    /**
     * Constructor de la clase Jugador
     * 
     * @param hp vida del ente
     * @param punto lugar del mapa donde se posiciona
     * @param sprite imagen del ente
     * @param velocidad velocidad a la que se mueve el ente
     * 
     */    
    public Jugador(int hp, Punto punto, Sprite sprite, float velocidad){
        super(hp, punto, sprite, velocidad);
        this.experiencia = 0; //inicializado
        this.nivelJugador = 1; //inicializado
        this.nivelMapa = 1; //inicializado
        this.escenario = 1; //inicializado
    }
   
    /**
     * Get the value of inventario
     *
     * @return the value of inventario
     */
    public Inventario getInventario() {
        return inventario;
    }

    /**
     * Set the value of inventario
     *
     * @param inventario new value of inventario
     */
    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }
    /**
     * Get the value of experiencia
     *
     * @return the value of experiencia
     */
    public int getExperiencia() {
        return experiencia;
    }

    /**
     * Set the value of experiencia
     *
     * @param experiencia new value of experiencia
     */
    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }
    
    /**
     * Get the value of jugador
     *
     * @return the value of nivelJugador
     */
    public int getNivelJugador() {
        return nivelJugador;
    }

    /**
     * Get the value of nivelMapa
     *
     * @return the value of nivelMapa
     */
    public int getNivelMapa() {
        return nivelMapa;
    }

    /**
     * Get the value of escenario
     *
     * @return the value of escenario
     */
    public int getEscenario() {
        return escenario;
    }
    
    public void anadirExperiencia(int exp){
        this.setExperiencia(this.getExperiencia()+exp);
    }
    
    public void subirNivelJugador(){
        this.nivelJugador++;
    }
    
    public void avanzarEscenario(){
        this.escenario++;
    }
    
    public void retrocederEscenario(){
        this.escenario--;
    }
    
    public void avanzarMapa(){
        this.nivelMapa++;
    }
}
