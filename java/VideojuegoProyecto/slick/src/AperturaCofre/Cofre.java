/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AperturaCofre;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;

/**
 *
 * @author Senapi Aroal
 */
public class Cofre extends BasicGame implements ComponentListener{

    private MouseOverArea areas;
    private GameContainer container;
    private SpriteSheet sprite;
    private boolean pulsado = false;
    private Animation anim;
    public Cofre() throws SlickException{
        super("Cofre");
    }
    
    @Override
    public void init(GameContainer container) throws SlickException {
      sprite = new SpriteSheet("res/chest2.png",32,32);    
      anim = new Animation(sprite,100);
      anim.setCurrentFrame(0);
      this.container = container;
      areas = new MouseOverArea(container,anim.getImage(0),100,100,32,32,this);//similar a pulsar un botón
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {        
        areas.render(container,g);
        if(pulsado){
            anim.setCurrentFrame(1);
        }else{
            anim.setCurrentFrame(0);
        }
        anim.draw(100,100);
    }
    
    @Override
    public void update(GameContainer container, int delta) throws SlickException {      
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            AppGameContainer app = new AppGameContainer(new Cofre());
            app.setDisplayMode(600,300, false);
            app.start();
        } catch (SlickException ex) {
            Logger.getLogger(Cofre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void componentActivated(AbstractComponent source) {
       if (source == areas) {  
            if(pulsado){
                pulsado = false;
            }else{
                pulsado = true;
            }
        }
    }

}
