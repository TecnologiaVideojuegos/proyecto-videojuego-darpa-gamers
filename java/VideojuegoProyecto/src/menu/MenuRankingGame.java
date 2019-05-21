/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import exception_serialization.*;
import imagen.Sprite;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import location.Punto;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.*;
import org.newdawn.slick.state.*;

/**
 *
 * @author Senapi Aroal
 */
public class MenuRankingGame  extends BasicGameState implements ComponentListener{
    
    private AlmacenarAvatar almacenar = new AlmacenarAvatar();;
    private Sprite marcadores,salir;
    private MouseOverArea boton;
    private ArrayList<Info_Jugador> lista = new ArrayList<>();;
    private int estado = -1;

    public MenuRankingGame(GameContainer container) {
        try{
            marcadores = new Sprite("./res/grafico/game_utils/marcadores.png");
            salir = new Sprite("./res/grafico/buttons/boton_atras.png",new Punto(378,610));
            boton = new MouseOverArea(container,salir,(int)salir.getPosicion().getX(),(int)salir.getPosicion().getY(),(int)salir.getWidth(),(int)salir.getHeight(),this);
            boton.setNormalColor(new Color(1,1,1,0.7f));
            boton.setMouseOverColor(new Color(1,1,1,0.9f));
            
        }catch(SlickException ex){}
    }
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        almacenar.guardarDatos(3);
            almacenar.guardarNombre();
            almacenar.cargarDatos(3);
            almacenar.cargarNombres();
            lista = almacenar.ordenarMarcador();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        marcadores.draw();
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
    public void enter(GameContainer container,StateBasedGame game)throws SlickException{
        container.getInput().clearKeyPressedRecord();        
        init(container, game);     
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
        return -6;
    }
    
}
