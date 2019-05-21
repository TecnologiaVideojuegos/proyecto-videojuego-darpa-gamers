/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import exception_serialization.*;
import imagen.Sprite;
import java.util.*;
import location.Punto;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.*;
import org.newdawn.slick.state.*;

/**
 *
 * @author Senapi Aroal
 */
public class MenuRankingGame  extends BasicGameState implements ComponentListener{
    
    private AlmacenarAvatar mapa3 = new AlmacenarAvatar();
    private Sprite marcadores,salir;
    private MouseOverArea boton;
    private ArrayList<Info_Jugador> lista = new ArrayList<>();
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
        mapa3.cargarNombres();
        lista = mapa3.ordenarMarcador();    
        System.out.println(lista.toString());
        for(int i = 0;i< lista.size();i++){
            System.out.println(lista.get(i).getNombre());
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        marcadores.draw();
        boton.render(container, g);
        if(lista.size() <= 5){
            for(int i = 0;i < lista.size();i++){
                g.drawString(lista.get(i).getNombre(),260,220+(i*90));
                g.drawString(lista.get(i).getExperiencia()+"",650,220+(i*90));
            }
        }else{
            for(int i = 0;i < 5;i++){
                g.drawString(lista.get(i).getNombre(),260,220+(i*90));
                g.drawString(lista.get(i).getExperiencia()+"",650,220+(i*90));
            }
        }
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
    public void mouseClicked(int button, int x, int y, int clickCount){
            System.out.println("Eje x: " +x + " Eje y: " + y);
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
