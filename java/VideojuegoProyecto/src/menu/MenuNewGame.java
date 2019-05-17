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
/**
 *
 * @author Senapi Aroal
 */
public class MenuNewGame  extends BasicGameState implements ComponentListener{

    private Sprite fondo,jugar,salir;
    /** The field taking the name */
    private TextField name;
    /** The name value */
    private String nameValue = "none";
    private AlmacenarAvatar almacenar;
    private int estado = -1;
    private final MouseOverArea[] botones = new MouseOverArea[2];

    public MenuNewGame(GameContainer gc){
        almacenar = new AlmacenarAvatar();
        almacenar.cargarDatos(1);
        name = new TextField(gc,gc.getDefaultFont(),578,141,300,40,this);
        try {
            fondo = new Sprite("./res/grafico/fonds/fondo.png");
            jugar = new Sprite("./res/grafico/buttons/boton_jugar.png",new Punto(250,500));
            salir = new Sprite("./res/grafico/buttons/boton_atras.png",new Punto(378,630));
        } catch (SlickException ex) {
            Logger.getLogger(MenuNewGame.class.getName()).log(Level.SEVERE, null, ex);
        } 
	botones[0] = new MouseOverArea(gc,jugar,(int)jugar.getPosicion().getX(),(int)jugar.getPosicion().getY(),jugar.getWidth(),jugar.getHeight(),this); 
        botones[1] = new MouseOverArea(gc,salir,(int)salir.getPosicion().getX(),(int)salir.getPosicion().getY(),salir.getWidth(),salir.getHeight(),this);         
        
        for(int i = 0;i<botones.length;i++){
            botones[i].setNormalColor(new Color(1,1,1,0.7f));
            botones[i].setMouseOverColor(new Color(1,1,1,0.9f));
        }
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        name.setBackgroundColor(Color.transparent);
        name.setTextColor(Color.red);
        name.setBorderColor(Color.yellow);
        name.setAcceptingInput(true);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        fondo.draw();
        name.render(gc, grphcs);
        grphcs.drawString("Introduzca su nombre: ",350,141);
        for (MouseOverArea botone : botones) {
            botone.render(gc, grphcs);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        comprobarEstado(gc,sbg);
    }

    @Override
    public void enter(GameContainer container,StateBasedGame game)throws SlickException{
        container.getInput().clearKeyPressedRecord();        
        init(container, game);     
    }
    
    public void comprobarEstado(GameContainer gc,StateBasedGame game) throws SlickException{
        if(estado == 0){
            estado = -1;
            if(!nameValue.equals("none")){
                Jugador j = new Jugador(nameValue,200,new Punto(230,200),null,100,0,50,50,1);
                if(almacenar.altaJugador(new Info_Jugador(j))){
                    name.setText("");
                    almacenar.guardarDatos(1);
                    game.addState(new MenuSelectLevelGame(gc,j.getNombre()));
                    game.enterState(-4);
                }else{
                    name.setText("");
                    System.err.println("Ya registrado");
                }
            }
        }else if(estado == 1){
            estado = -1;
            name.setText("");
            name.setAcceptingInput(false);
            game.enterState(0);
        }
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
        if(source == name){
            nameValue = name.getText();
        }        
        for(int i = 0;i<botones.length;i++){
            if (source == botones[i]) {
                estado = i;
            }
        }
    }
    
    /**
     * @see org.newdawn.slick.BasicGame#keyPressed(int, char)
     */
    public void keyPressed(int key, char c) {
	if (key == Input.KEY_ESCAPE) {
	    System.exit(0);
	}
    }
    
    @Override
    public int getID() {
        return -1;
    }
}
