/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animaciones;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Senapi Aroal
 */
public class PruebaAnimacion extends StateBasedGame{

    public PruebaAnimacion() throws SlickException{
        super("Prueba");
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        this.addState(new EstadoBasico());
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            AppGameContainer app = new AppGameContainer(new PruebaAnimacion());
            app.setDisplayMode(600,300,false);
            app.setTargetFrameRate(1000);
            app.setShowFPS(true);
            app.start();
        }catch(SlickException slick){
            slick.printStackTrace();
        }
    }
}
