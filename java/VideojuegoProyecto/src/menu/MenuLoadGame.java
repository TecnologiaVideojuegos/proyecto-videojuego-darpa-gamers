/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import characters.Jugador;
import exception_serialization.*;
import graphic.Notificaciones;
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
 * @author Davidcawork
 */
public class MenuLoadGame extends BasicGameState implements ComponentListener{

    private final MouseOverArea[] botones = new MouseOverArea[2];
    private Sprite fondo,jugar,salir,menu_nombre;
    /** The field taking the name */
    private TextField name;
    /** The name value */
    private String nameValue = "none";
    private AlmacenarAvatar almacenar;
    private int estado = -1;
    private final Notificaciones notif;
    private Music musica;

    public MenuLoadGame(GameContainer container,Music musica) throws SlickException {
        this.musica = musica;
        almacenar = new AlmacenarAvatar();
        almacenar.cargarDatos(1);
        name = new TextField(container,container.getDefaultFont(),120+166,80+141,440,65,this);
        notif = new Notificaciones(3000);
        try {
            fondo = new Sprite("./res/grafico/fonds/fondo.png");
            menu_nombre = new Sprite("./res/grafico/menu_carga_nueva_partida/menu_carga_nueva_partida.png",new Punto(120,80));
            jugar = new Sprite("./res/grafico/buttons/boton_jugar.png",new Punto(250,500));
            salir = new Sprite("./res/grafico/buttons/boton_atras.png",new Punto(378,630));
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
        name.setTextColor(Color.darkGray);
        name.setBorderColor(Color.transparent);
        name.setText("");
        name.setAcceptingInput(true);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        fondo.draw();
        menu_nombre.draw();
        name.render(container,g);
        this.notif.imprimirNotificaciones();
        for (MouseOverArea botone : botones) {
            botone.render(container, g);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
       comprobarEstado(container,game);
       this.notif.controlNotif(delta);
       this.notif.controlEstadoEspera(delta);
    }

    public void comprobarEstado(GameContainer container,StateBasedGame game) throws SlickException{
        if(estado == 0){
            estado = -1;
            if(!nameValue.equals("none")){
               Jugador j = new Jugador(nameValue,200,new Punto(230,200),100,0,20,50,1);
                if(almacenar.getMostrar().containsKey(nameValue)){
                    name.setText("");
                    game.addState(new MenuSelectLevelGame(container,j.getNombre(),musica));
                    game.enterState(-4);
                }else{
                    name.setText("");
                    System.err.println("No registrado");
                    this.notif.aniadirNotificacion(this.notif.getImgNotf()[7]);
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
