/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animaciones;

import static java.lang.Thread.sleep;
import java.util.Date;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Senapi Aroal
 */
public class EstadoBasico extends BasicGameState{
    private SpriteSheet sprite;
    private Animation anim;
    private Shape RectRect,PersX1,PersX2,PersX3,PersX4;
    private Input entrada;
    private boolean suelo = false;
    private int floor = 250;
    private boolean saltando,presionado;
    private boolean colArriba = false;
    private boolean colAbajo = false;
    private boolean colDer = false;
    private boolean colIzq = false;
    private static final float VELOCIDAD = 100.0f;
    private float velS = 1.0f; //velocidad de subida
    private float x = 0;
    private float y = 250;
    private float impulso = 2.0f;
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        sprite = new SpriteSheet("res/DudeWalking.png",28,49);
        anim = new Animation(sprite,100);
        PersX1 = new Rectangle(x,(y+2),0.5f,45); //poligono para la izquierda
        PersX2 = new Rectangle((x+28),(y+2),0.5f,45); //poligono para la derecha
        PersX3 = new Rectangle((x+4),y,22,0.5f); //poligono para arriba
        PersX4 = new Rectangle((x+4),(y+49),22,0.5f); //poligono para abajo
        RectRect = new Rectangle(100,250,300,50);
        container.getGraphics().setBackground(Color.gray);
        entrada = container.getInput();
        y = floor;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        
    }
    
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        anim.draw(x,y);
        g.draw(PersX1);
        g.draw(PersX2);
        g.draw(PersX3);
        g.draw(PersX4);
        g.drawRect(RectRect.getX(),RectRect.getY(),RectRect.getWidth(),RectRect.getHeight());
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        actualizarObjetos(delta);
        jumping();
        int i = gestionColisiones();
        if(i >= 1){
          //  System.out.println("COlision: " + i);
        }
        
       
    }    
    private int gestionColisiones(){
        if(PersX2.intersects(RectRect)){
            return 2;
        }else if(PersX1.intersects(RectRect)){
            return 3;
        }else if(PersX4.intersects(RectRect)){
            return 1;
        }else{
            return -1;
        }
    }
    private void actualizarObjetos(int delta){
        /*if((x+(sprite.getWidth()/9)) >= 338 && (y+sprite.getHeight()) >= 299){           
            x += (VELOCIDAD+50) * (float)delta/1000;
            PersoRect.setX(x);
        }*/
        if(entrada.isKeyDown(Input.KEY_RIGHT)){
            if((int)x != (600-(sprite.getWidth()/8)) && gestionColisiones() != 2){
                x += (VELOCIDAD * (float)delta/1000)*impulso;
                PersX1.setX(x);
                PersX2.setX((x+28));
                PersX3.setX(x+4);
                PersX4.setX((x+4));
            }
        }else if(entrada.isKeyDown(Input.KEY_LEFT) ){
            if((int)x != 0 && gestionColisiones() != 3){
              x -= (VELOCIDAD * (float)delta/1000)*impulso;
                PersX1.setX(x);
                PersX2.setX((x+28));
                PersX3.setX(x+4);
                PersX4.setX((x+4));
            }           
        }else{
            
        }
            if (entrada.isKeyDown(Input.KEY_SPACE)){ // metodo de salto
                presionado = true;  //hemos presionado la telca
            }              
        }
    private void jumping(){
        if(presionado){  
                    if(!saltando){   //si no está saltando
                        velS= -1.50f;   //comenzamos a subir
                        saltando = true; //activamos flag de salto
                        PersX1.setX(x);
                        PersX1.setY((y-2));
                        PersX2.setX((x+28));
                        PersX2.setY((y-2));
                        PersX3.setX((x+4));
                        PersX3.setY(y);
                        PersX4.setX((x+4));
                        PersX4.setY((y+49));
                    }
                    if(saltando){
                        velS  += 0.009;  //empezamos a descender 
                        PersX1.setX(x);
                        PersX1.setY((y-2));
                        PersX2.setX((x+28));
                        PersX2.setY((y-2));
                        PersX3.setX((x+4));
                        PersX3.setY(y);
                        PersX4.setX((x+4));
                        PersX4.setY((y+49));
                    }
                    y += velS;   //nos movemos a esa posicion
                    if (y >= floor){//hemos llegado al suelo
                        y = floor;
                        saltando = false; //desactivamos flags de salto
                        presionado = false;  //descativamos flags de tecla presionada
                    }else if(gestionColisiones() == 1){
                        suelo = true;
                        saltando = false; //desactivamos flags de salto
                        presionado = false;  //descativamos flags de tecla presionada
                        PersX1.setX(x);
                        PersX1.setY((y-2));
                        PersX2.setX((x+28));
                        PersX2.setY((y-2));
                        PersX3.setX((x+4));
                        PersX3.setY(y);
                        PersX4.setX((x+4));
                        PersX4.setY((y+49));
                    }
        
       }
    }
    @Override
    public void mouseReleased(int button, int x, int y) {
      //  System.exit(0);
     
       }
    
    @Override
    public int getID() {
        return 0;
    }
}
