/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;

/**
 *
 * @author Senapi Aroal
 */
public class Nave{
    private String nombre;
    private int dannyo;

    public Nave(String nombre, int dannyo) {
        this.nombre = nombre;
        this.dannyo = dannyo;
    }

    /**
     * Get the value of dannyo
     *
     * @return the value of dannyo
     */
    public int getDannyo() {
        return dannyo;
    }

    /**
     * Set the value of dannyo
     *
     * @param dannyo new value of dannyo
     */
    public void setDannyo(int dannyo) {
        this.dannyo = dannyo;
    }


    /**
     * Get the value of nombre
     * 
     * @return the value of nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the value of nombre
     * 
     * @param nombre new value of nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}