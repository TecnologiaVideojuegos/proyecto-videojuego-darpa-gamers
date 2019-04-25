/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loop_game;

import map.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Senapi Aroal
 */
public class MainGame extends StateBasedGame{

    private AppGameContainer contenedor;
    public MainGame() throws SlickException{
        super("Shiawase Story");
        contenedor = new AppGameContainer(this);
        contenedor.setDisplayMode(1024,868,false);
        contenedor.setShowFPS(true);
        contenedor.start();
    }
    
    
    @Override
    public void initStatesList(GameContainer container) throws SlickException {
           this.addState(new Nivel1());
    }
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            new MainGame();
            
        }catch(SlickException slick){
            slick.printStackTrace();
        }
    }

    
}
