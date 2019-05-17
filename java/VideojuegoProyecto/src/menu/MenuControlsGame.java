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
public class MenuControlsGame extends BasicGameState implements ComponentListener{

    private Sprite controles,salir;
    private MouseOverArea boton;
    private int estado = -1;
    
    public MenuControlsGame(GameContainer container) {
        try{
            controles = new Sprite("./res/grafico/game_utils/controles.png");
            salir = new Sprite("./res/grafico/buttons/boton_atras.png",new Punto(378,550));
            boton = new MouseOverArea(container,salir,(int)salir.getPosicion().getX(),(int)salir.getPosicion().getY(),(int)salir.getWidth(),(int)salir.getHeight(),this);
            boton.setNormalColor(new Color(1,1,1,0.7f));
            boton.setMouseOverColor(new Color(1,1,1,0.9f));
        }catch(SlickException ex){}
    }
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        controles.draw();
        boton.render(container, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        comprobarEstado(container,game);
    }
    
    public void comprobarEstado(GameContainer container,StateBasedGame game) throws SlickException{
        switch (estado) {
            case 0:
                //Salir
                estado = -1;
                game.enterState(-3);
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
        return -5;
    }
    
}
