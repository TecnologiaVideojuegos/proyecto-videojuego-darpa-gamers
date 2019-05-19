/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package materials;

import java.io.Serializable;

/**
 *
 * @author Senapi Aroal
 */
public class Objeto implements Serializable{
    
    private int id;
    private String nombre;
    private String info;

    /**
     * Constructor de la clase Objeto
     * 
     * @param id identifica el objeto con el resto
     * @param nombre el nombre de ese objeto
     * @param info información adicional que se muestra para explicar su funcinamiento
     * 
     */
    public Objeto(int id, String nombre, String info) {
        this.id = id;
        this.nombre = nombre;
        this.info = info;
    }
   
    /**
     * Get the value of info
     *
     * @return the value of info
     */
    public String getInfo() {
        return info;
    }

    /**
     * Set the value of info
     *
     * @param info new value of info
     */
    public void setInfo(String info) {
        this.info = info;
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

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(int id) {
        this.id = id;
    }

}
