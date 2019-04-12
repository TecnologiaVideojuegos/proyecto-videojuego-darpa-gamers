/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas.Prueba1;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Senapi Aroal
 */
public class Juego2 extends BasicGameState{
    private Image sprite;
    private SpriteSheet sprites;
    private Animation anim;
    private Input entrada;
    private float x = 0.0f;
    private float y = 250.0f;
    private int[] frame = {1,2,3};
    private int[] tiempo = {100,100,100};
    public Juego2() {
        
    }
   
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
       sprite = new Image("res/foto2.jpg");
       sprites = new SpriteSheet("res/foto3.png",25,32);
       //anim = new Animation(sprites,frame,100);
       anim = new Animation(sprites,0,3,2,3,true,150,false);
       entrada = container.getInput();
          }
    
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        sprite.draw(0, 0,0.5f);
        anim.draw(x,y);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(entrada.isKeyPressed(Input.KEY_ENTER)){
            game.enterState(0);
        }
        if(entrada.isKeyDown(Input.KEY_LEFT)){
            x+=0.5;
        }
        if(entrada.isKeyDown(Input.KEY_RIGHT)){
            x-=0.5;
        }
    }
    @Override
    public void enter(GameContainer container ,StateBasedGame game)throws SlickException{
        //container.getInput().clearMousePressedRecord();
        init(container,game);
        
    }
     @Override
    public int getID() {
        return 1;
    }

}
