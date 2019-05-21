/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 *
 * @author Senapi Aroal
 */
public class NivelFinal extends BasicGameState{

    private Music musica;
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        
    }
    
    @Override
    public void enter(GameContainer container,StateBasedGame game) throws SlickException{
        container.getInput().clearKeyPressedRecord();
        init(container,game);
    }
    
    //Estado siguiente al último nivel jugable
    @Override
    public int getID() {
        return 4;
    }
    
}
