/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception_serialization;

import characters.Jugador;
import data_level.DatosNivel;
import java.io.Serializable;
import java.util.ArrayList;
import materials.Objeto;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Senapi Aroal
 */
public class Info_Jugador implements Serializable{
    
    private String nombre;
    private int hp;
    private float velocidad;
    private int municion;
    private int danyo;
    private int nivelMapaActual;
    private int nivelMapaMax;
    private int experiencia;
    private int nivelJugador;
    private ArrayList<Objeto>[] inventario;
    
    public Info_Jugador(Jugador j)throws SlickException{
        this.nombre = j.getNombre();
        this.hp = j.getHp();
        this.velocidad = j.getVelocidad();
        this.municion = j.getVarita().getMunicion();
        this.danyo = j.getDanyo();
        this.experiencia = j.getExperiencia();
        this.nivelMapaActual = j.getNivelMapa();
        this.nivelMapaMax = j.getNivelMapaMax();
        this.inventario = j.getInventario().getInventario();
        this.nivelJugador = j.getNivelJugador();
    }

    public Jugador devolverJugador(DatosNivel data){
        Jugador j = null;
        
        try{
            j = new Jugador(this.getNombre(),this.getHp(),data.getEntradas((this.getNivelMapaActual()-1)),this.getVelocidad(),0,this.getMunicion(),this.getDanyo(),this.getNivelMapaActual());            
            j.setExperiencia(this.getExperiencia());
            j.setNivelMapaMax(this.getNivelMapaMax());          
            j.setNivelJugador(this.getNivelJugador());            
            j.getInventario().setInventario(this.getInventario());
        }catch(SlickException ex){}
        return j;
    }

    public int getNivelJugador() {
        return nivelJugador;
    }

    public void setNivelJugador(int nivelJugador) {
        this.nivelJugador = nivelJugador;
    }

    
    
    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }
    
    
    public ArrayList<Objeto>[] getInventario() {
        return inventario;
    }

    public void setInventario(ArrayList<Objeto>[] inventario) {
        this.inventario = inventario;
    }
    
    public int getNivelMapaActual() {
        return nivelMapaActual;
    }

    public void setNivelMapaActual(int nivelMapaActual) {
        this.nivelMapaActual = nivelMapaActual;
    }

    public int getNivelMapaMax() {
        return nivelMapaMax;
    }

    public void setNivelMapaMax(int nivelMapaMax) {
        this.nivelMapaMax = nivelMapaMax;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public float getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }

    public int getMunicion() {
        return municion;
    }

    public void setMunicion(int municion) {
        this.municion = municion;
    }

    public int getDanyo() {
        return danyo;
    }

    public void setDanyo(int danyo) {
        this.danyo = danyo;
    }
    
    
}
