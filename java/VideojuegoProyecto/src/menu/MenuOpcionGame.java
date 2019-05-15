/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import imagen.Sprite;
import location.Punto;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.*;
import org.newdawn.slick.state.*;

/**
 *
 * @author Senapi Aroal
 */
public class MenuOpcionGame extends BasicGameState implements ComponentListener{

    private boolean anadidoCreditos = false,anadidoControles = false,anadidoMarcadores = false;
    private Sprite fondo,creditos,marcadores,controles,salir;
    private int estado = -1; 
    private final MouseOverArea[] botones = new MouseOverArea[4];

    public MenuOpcionGame(GameContainer container) {
        try{
            fondo = new Sprite("./res/fonds/fondo.png");
            controles = new Sprite("./res/buttons/boton_controles.png",new Punto(250,200));
            marcadores = new Sprite("./res/buttons/boton_marcadores.png",new Punto(250,300));
            creditos = new Sprite("./res/buttons/boton_creditos.png",new Punto(250,400));
            salir = new Sprite("./res/buttons/boton_atras.png",new Punto(378,550));
            Sprite buttons[] = {controles,marcadores,creditos,salir};
            for(int i = 0;i<botones.length;i++){
                botones[i] = new MouseOverArea(container,buttons[i],(int)buttons[i].getPosicion().getX(),(int)buttons[i].getPosicion().getY(),buttons[i].getWidth(),buttons[i].getHeight(),this);         
                botones[i].setNormalColor(new Color(1,1,1,0.7f));
                botones[i].setMouseOverColor(new Color(1,1,1,0.9f));
            }
        }catch(SlickException ex){}
    }
    
    
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        fondo.draw();
        for (MouseOverArea botone : botones) {
            botone.render(container, g);
        }
    }
    
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        comprobarEstado(container,game);
    }
    
    public void comprobarEstado(GameContainer container,StateBasedGame game) throws SlickException{
        switch (estado) {
            case 0:
                //Controles
                estado = -1;
                if(!anadidoControles){
                    anadidoControles = true;
                    game.addState(new MenuControlsGame(container));
                }   game.enterState(-5);
                break;
            case 1:
                //Marcadores
                estado = -1;
                if(!anadidoMarcadores){
                    anadidoMarcadores = true;
                    game.addState(new MenuRankingGame(container));
                }   game.enterState(-6);
                break;
            case 2:
                //Creditos
                estado = -1;
                if(!anadidoCreditos){
                    anadidoCreditos = true;
                    game.addState(new MenuCreditsGame(container));                    
                }   game.enterState(-7);
                break;
            case 3:
                //Salir
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
        for(int i = 0;i<botones.length;i++){
            if (source == botones[i]) {
                estado = i;
            }
        }
    }
    
    @Override
    public int getID() {
        return -3;
    }
    
}
