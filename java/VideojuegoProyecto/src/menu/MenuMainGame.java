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
public class MenuMainGame extends BasicGameState implements ComponentListener{

    private Sprite fondo,logo_juego,logo_grupo,titulo,nueva_partida,cargar_partida,opciones,salir;
    private int estado = -1;
    private boolean anadidoLoad = false,anadidoNew = false,anadidoOpcion = false;
    private final MouseOverArea[] botones = new MouseOverArea[4];

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        fondo = new Sprite("./res/grafico/fonds/fondo.png");
        titulo = new Sprite("./res/grafico/titles/titulo_principal.png",new Punto(150,10));
        logo_juego = new Sprite("./res/grafico/fonds/logotipo_juego.png",new Punto(949,693),0.25f);
        logo_grupo = new Sprite("./res/grafico/fonds/logotipo_grupo.png",new Punto(0,693),0.25f);
        nueva_partida = new Sprite("./res/grafico/buttons/boton_nueva_partida.png",new Punto(250,200));
        cargar_partida = new Sprite("./res/grafico/buttons/boton_cargar_partida.png",new Punto(250,300));
        opciones = new Sprite("./res/grafico/buttons/boton_opciones.png",new Punto(250,400));
        salir = new Sprite("./res/grafico/buttons/boton_salir.png",new Punto(378,550));
        Sprite buttons[] = {nueva_partida,cargar_partida,opciones,salir};
        for(int i = 0;i<botones.length;i++){
            botones[i] = new MouseOverArea(container,buttons[i],(int)buttons[i].getPosicion().getX(),(int)buttons[i].getPosicion().getY(),buttons[i].getWidth(),buttons[i].getHeight(),this);         
            botones[i].setNormalColor(new Color(1,1,1,0.7f));
            botones[i].setMouseOverColor(new Color(1,1,1,0.9f));
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        fondo.draw();
        titulo.draw();
        logo_juego.draw();
        logo_grupo.draw();
        for(int i = 0;i<botones.length;i++){
            botones[i].render(container, g);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        comprobarEstado(container,game);
    }

    public void comprobarEstado(GameContainer container,StateBasedGame game) throws SlickException{
        switch (estado) {
            case 0:
                //Nueva Partida
                estado = -1;
                if(!anadidoNew){
                    anadidoNew = true;
                    game.addState(new MenuNewGame(container));
                }   game.enterState(-1);
                break;
            case 1:
                //Cargar Partida
                estado = -1;
                if(!anadidoLoad){
                    anadidoLoad = true;
                    game.addState(new MenuLoadGame(container));
                }   game.enterState(-2);
                break;
            case 2:
                //Opciones
                estado = -1;
                if(!anadidoOpcion){
                    anadidoOpcion = true;
                    game.addState(new MenuOpcionGame(container));                    
                }   game.enterState(-3);
                break;
            case 3:
                //Salir
                estado = -1;
                System.exit(0);
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
        return 0;
    }
}
