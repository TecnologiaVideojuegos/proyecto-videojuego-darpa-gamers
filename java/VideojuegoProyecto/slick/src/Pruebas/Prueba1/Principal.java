/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas.Prueba1;

import java.util.Scanner;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Senapi Aroal
 */
public class Principal extends StateBasedGame{
    private AppGameContainer contenedor;
    public Principal() throws SlickException{
        super("Juego basado en estados");
        contenedor = new AppGameContainer(this);
        contenedor.setDisplayMode(800,640,false);
        contenedor.start();
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
                this.addState(new Juego());
                this.addState(new Juego2());
    }
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            new Principal();
            
        }catch(SlickException slick){
            slick.printStackTrace();
        }
    }
    
}
