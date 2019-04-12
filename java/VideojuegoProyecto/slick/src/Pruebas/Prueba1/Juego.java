/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas.Prueba1;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Senapi Aroal
 */
public class Juego extends BasicGameState implements ComponentListener{
    private MouseOverArea areas;
    private Image sprite,fondo;
    private Image boton;
    private GameContainer container;
    private TiledMap mapa,mapa1;
    private boolean pulsado = false;
    private Input entrada;
    private Rectangle PersoRect;
    private int x = 0;
    private int y = 100;
    private int scene = 1;
    private TiledMap[] escenas = new TiledMap[5];
    public Juego() {
        
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        try{
        sprite = new Image("res/foto1.jpg");
        fondo = new Image("res/sky2.jpg");
        boton = new Image("res/botonJuego.png");
         }catch(Exception ex){
        
      }
        for(int i = 1;i<3;i++){
          escenas[i] = new TiledMap("res/Nivel_"+i+".tmx","res");
          System.out.println(escenas[i]);
        }
        PersoRect = new Rectangle(x,y,30,20);
        this.container = container;
        areas = new MouseOverArea(container,boton,200,300,435,120,this);//similar a pulsar un botón
        areas.setNormalColor(new Color(1,1,1,0.8f));
	areas.setMouseOverColor(new Color(1,1,1,0.9f));
        entrada = container.getInput();
        
    }
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
       
        fondo.draw();
        g.translate(-PersoRect.getX(),-PersoRect.getY());
        escenas[scene].render(0, 0);
        g.drawRect(PersoRect.getX(),PersoRect.getY(),PersoRect.getWidth(),PersoRect.getHeight());
   
        //sprite.draw(0, 0, 0.5f);
        areas.render(container, g);
   
    }
/*if (container.getInput().isKeyPressed(Input.KEY_UP) && !jumping) {
     verticalSpeed = -10.0f * delta;//negative value indicates an upward movement
     jumping = true;
}

if (jumping) {
     verticalSpeed += .01f * delta;//change this value to alter gravity strength
}

playerY += verticalSpeed;*/
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        
        if(entrada.isKeyPressed(Input.KEY_ENTER) || pulsado){
            game.enterState(1);
        }
        if(entrada.isKeyPressed(Input.KEY_SPACE)){
            if(scene == 2){
                scene = 1;
            }else{
                scene++;
            }
        }
        if(entrada.isKeyDown(Input.KEY_RIGHT)){
          
                x +=1;
                PersoRect.setX(x);
            
        }else if(entrada.isKeyDown(Input.KEY_LEFT)){
         
              x -=1;
                PersoRect.setX(x);
                 
        }
        if(entrada.isKeyDown(Input.KEY_UP)){
          
               y -=1;
                PersoRect.setY(y);
            
        }else if(entrada.isKeyDown(Input.KEY_DOWN)){
         
              y +=1;
                PersoRect.setY(y);
                 
        }
    }
    @Override
    public void enter(GameContainer container ,StateBasedGame game)throws SlickException{
        //container.getInput().clearMousePressedRecord();
        init(container,game);
        pulsado = false;
    }
    @Override
    public void mouseReleased(int button, int x, int y) {
      /*  if(button!=0)return;
        if((x>=300 && x<=517) && (y>= 300 && y<=360)){
            pulsado = true;
        }*/
    }
    @Override
    public int getID() {
        return 0;
    }
    public void componentActivated(AbstractComponent source) {
        System.out.println("ACTIVL : "+source);
		
	if (source == areas) {
            pulsado = true;
            System.out.println("PULSADO!!!!");
        }
    }
}
