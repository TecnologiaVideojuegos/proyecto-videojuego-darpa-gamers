/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package characters;

import location.Punto;
import imagen.Sprite;

/**
 * @version 0.0.10
 * @author Senapi Aroal
 */
public class Monstruo extends Ente{
    
    private String comportamiento;

    /**
     * Constructor de la clase Monstruo
     * 
     * @param hp vida del ente
     * @param punto lugar del mapa donde se posiciona
     * @param sprite imagen del ente
     * @param velocidad velocidad a la que se mueve el ente
     * @param  comportamiento establece el comportamiento del monstruo
     * 
     */
    public Monstruo(int hp, Punto punto, Sprite sprite, float velocidad, String comportamiento) {
        super(hp, punto, sprite, velocidad);
        this.comportamiento = comportamiento;
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
    
}
