/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package materials;

import location.Punto;
import map.Escena;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Senapi Aroal
 */
public class Arco {
    
    private ControladorFlechas flecha;
    
    public Arco(){
        flecha = new ControladorFlechas();
    }
    
    public void dispararFlecha(GameContainer container,Escena escena,Punto punto,int nivelEvolucion,int delta) throws SlickException{
        int damage = nivelEvolucion*50; //daño que hace la flecha
        flecha.addFlecha(punto.getX(),punto.getY());
        flecha.update(container, escena,delta);
    }
    
    
}
