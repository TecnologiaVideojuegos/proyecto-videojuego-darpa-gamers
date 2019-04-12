/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animaciones;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Senapi Aroal
 */
public class Salto extends BasicGame{
     private int floor = 250;
     private float x, y, speed,velS;
     private boolean saltando,presionado;

    public Salto() throws SlickException{
        super("Auida");
    }

   
   
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         try{
            AppGameContainer app = new AppGameContainer(new Salto());
            app.setDisplayMode(600,300,false);
            app.setTargetFrameRate(1000);
            app.setShowFPS(true);
            app.start();
        }catch(SlickException slick){
            slick.printStackTrace();
        }
    }

    @Override
    public void init(GameContainer container) throws SlickException {
         x = 0;
         y = floor;
         speed =1;
         
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
          if (gc.getInput().isKeyDown(Input.KEY_D)){
              x += speed;
          }else if (gc.getInput().isKeyDown(Input.KEY_A)){
              x -= speed;
          }
         // Jump code
         if (gc.getInput().isKeyDown(Input.KEY_SPACE)){ // Must be on the ground to jump.
             presionado = true;
         }if(presionado){
                  if(!saltando){
                      velS= -4.0f * delta;
                      saltando = true;
                  }
                  if(saltando){
                      velS  += 0.04f*delta;
                  }
                  y += velS;
                   if (y >= floor){//Sub.bottom is the floor of the game
                       y = floor;
                   saltando = false;//we're not jumping anymore
                   presionado = false;//up key reset
    }
         }
    }
    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
         g.setColor(Color.white);
  g.fillRect(0, container.getHeight() - 32,container.getWidth(), 32);  
  g.setColor(Color.gray);
  g.fillRect(x, y, 32, 32);
    }

}
