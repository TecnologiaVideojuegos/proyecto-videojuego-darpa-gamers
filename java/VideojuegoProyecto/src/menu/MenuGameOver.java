/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import location.Punto;
import imagen.Sprite;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.*;
import org.newdawn.slick.state.*;

/**
 *
 * @author Senapi Aroal
 */
public class MenuGameOver extends BasicGameState implements ComponentListener{

    private Sprite inicio;
    private MouseOverArea boton;
    private int estado = -1;
    private Music musica;
    
    public MenuGameOver(GameContainer container) {
        try{
            inicio = new Sprite("./res/grafico/buttons/boton_atras.png",new Punto(378,550));
            boton = new MouseOverArea(container,inicio,(int)inicio.getPosicion().getX(),(int)inicio.getPosicion().getY(),(int)inicio.getWidth(),(int)inicio.getHeight(),this);
            boton.setNormalColor(new Color(1,1,1,0.7f));
            boton.setMouseOverColor(new Color(1,1,1,0.9f));
        }catch(SlickException ex){}
    }
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        boton.render(container, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        comprobarEstado(container,game);
    }
    
    public void comprobarEstado(GameContainer container,StateBasedGame game) throws SlickException{
        switch (estado) {
            case 0:
                //Inicio
                estado = -1;
                game.enterState(0);
                break;
            default:
                break;
        }
    }
    
    @Override
    /**
     * @see org.newdawn.slick.gui.ComponentListener#componentActivated(org.newdawn.slick.gui.AbstractComponent)
     */
    public void componentActivated(AbstractComponent source) {
        if (source == boton) {
            estado = 0;
        }
    }

   
    
    @Override
    public int getID() {
        return -8;
    }
    
}