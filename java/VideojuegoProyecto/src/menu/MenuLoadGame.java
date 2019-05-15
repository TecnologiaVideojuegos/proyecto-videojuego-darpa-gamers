/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import characters.Jugador;
import exception_serialization.*;
import imagen.Sprite;
import java.util.logging.Level;
import java.util.logging.Logger;
import location.Punto;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.*;

/**
 *
 * @author Senapi Aroal
 */
public class MenuLoadGame extends BasicGameState implements ComponentListener{

    private final MouseOverArea[] botones = new MouseOverArea[2];
    private Sprite fondo,jugar,salir;
    /** The field taking the name */
    private TextField name;
    /** The name value */
    private String nameValue = "none";
    private AlmacenarAvatar almacenar;
    private int estado = -1;

    public MenuLoadGame(GameContainer container) {
        almacenar = new AlmacenarAvatar();
        almacenar.cargarDatos(1);
        name = new TextField(container,container.getDefaultFont(),578,141,300,40,this);
        try {
            fondo = new Sprite("./res/fonds/fondo.png");
            jugar = new Sprite("./res/buttons/boton_jugar.png",new Punto(250,500));
            salir = new Sprite("./res/buttons/boton_atras.png",new Punto(378,630));
        } catch (SlickException ex) {
            Logger.getLogger(MenuLoadGame.class.getName()).log(Level.SEVERE, null, ex);
        }   
	botones[0] = new MouseOverArea(container,jugar,(int)jugar.getPosicion().getX(),(int)jugar.getPosicion().getY(),jugar.getWidth(),jugar.getHeight(),this); 
        botones[1] = new MouseOverArea(container,salir,(int)salir.getPosicion().getX(),(int)salir.getPosicion().getY(),salir.getWidth(),salir.getHeight(),this);         
        for(int i = 0;i<botones.length;i++){
            botones[i].setNormalColor(new Color(1,1,1,0.7f));
            botones[i].setMouseOverColor(new Color(1,1,1,0.9f));
        }     
    }
    
    
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        name.setBackgroundColor(Color.transparent);
        name.setTextColor(Color.red);
        name.setBorderColor(Color.yellow);
        name.setAcceptingInput(true);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        fondo.draw();
        name.render(container,g);
        for (MouseOverArea botone : botones) {
            botone.render(container, g);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
       comprobarEstado(container,game);
    }

    public void comprobarEstado(GameContainer container,StateBasedGame game) throws SlickException{
        if(estado == 0){
            estado = -1;
            if(!nameValue.equals("none")){
               Jugador j = new Jugador(nameValue,200,new Punto(230,200),null,100,0,50,50,1);
                if(almacenar.getMostrar().containsKey(nameValue)){
                    game.addState(new MenuSelectLevelGame(container,j.getNombre()));
                    game.enterState(-4);
                }else{
                    name.setText("");
                    System.err.println("Ya registrado");
                }
            }
        }else if(estado == 1){
            estado = -1;
            name.setAcceptingInput(false);
            name.setText("");
            game.enterState(0);
        }
    }
    
    @Override
    public void enter(GameContainer container,StateBasedGame game)throws SlickException{
        container.getInput().clearKeyPressedRecord();        
        init(container, game);     
    }
    
    @Override
    public int getID() {
        return -2;
    }
    
    @Override
    public void componentActivated(AbstractComponent source) {
        if(source == name){
            nameValue = name.getText();
        }        
        for(int i = 0;i<botones.length;i++){
            if (source == botones[i]) {
                estado = i;
            }
        }
    }
}
