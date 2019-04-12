/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package characters;

import characters.Ente;
import java.util.Random;
import location.Punto;
import imagen.Sprite;

/**
 *
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

    /**
     * Realiza un movimiento al azar en caso de ser pasivo u de bajo nivel
     *
     * 
     */
    public void realizarMovimiento(){
        Random rand = new Random();
        switch(rand.nextInt(4)){
            case 0:
                super.moverArriba();
                break;
            case 1:
                super.moverAbajo();
                break;
            case 2:
                super.moverDrcha();
                break;
            case 3:
                super.moverIzq();
                break;
        }
    } 
    
}
