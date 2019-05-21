/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import exception_serialization.*;
import graphic.Notificaciones;
import imagen.Sprite;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import location.Punto;
import map.*;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.*;

/**
 *
 * @author Senapi Aroal
 * @author Davidcawork
 */
public class MenuSelectLevelGame extends BasicGameState implements ComponentListener{
    
    private AlmacenarAvatar mapa1 = new AlmacenarAvatar();
    private AlmacenarAvatar mapa2 = new AlmacenarAvatar();
    private AlmacenarAvatar mapa3 = new AlmacenarAvatar();
    private Sprite fondo,level1,level2,level3;
    private MouseOverArea[] botones = new MouseOverArea[3];
    private String nombre;
    private int estado = -1;
    private final int nivelMax = 3;
    private final Notificaciones notif;
    private Music musica;

    public MenuSelectLevelGame(GameContainer container,String nombre,Music musica) throws SlickException {
        notif = new Notificaciones(3000);
        this.musica = musica;
        try{
            this.nombre = nombre;
            
            fondo = new Sprite("./res/grafico/fonds/fondo.png");
            level1 = new Sprite("./res/grafico/buttons/boton_LEVEL1.png",new Punto(78,400));
            level2 = new Sprite("./res/grafico/buttons/boton_LEVEL2.png",new Punto(384,400));
            level3 = new Sprite("./res/grafico/buttons/boton_LEVEL3.png",new Punto(690,400)); 
            Sprite boton[] = {level1,level2,level3};
            for(int i = 0;i<botones.length;i++){
                botones[i] = new MouseOverArea(container,boton[i],(int)boton[i].getPosicion().getX(),(int)boton[i].getPosicion().getY(),(int)boton[i].getWidth(),(int)boton[i].getHeight(),this);
                botones[i].setNormalColor(new Color(1,1,1,0.7f));
                botones[i].setMouseOverColor(new Color(1,1,1,0.9f));
            }
            mapa1.cargarDatos(1);
            System.out.println(mapa1.toString());
            mapa2.cargarDatos(2);
            System.out.println(mapa2.toString());
            mapa3.cargarDatos(3);
            System.out.println(mapa3.toString());
        }catch(SlickException ex){}
    }
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        fondo.draw();
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
        switch (estado) {
            case 0:
                //Level1
                estado = -1;
                if(mapa1.getMostrar().size() > 0 && mapa1.getMostrar().containsKey(nombre)){
                    try {
                        for(int i = 2;i<(nivelMax+1);i++){//borrar toda información desde el nivel 2 en adelante
                            if(mapa1.getMostrar().containsKey(nombre)){
                                mapa1.bajaJugador(mapa1.getMostrar().get(nombre));
                                mapa1.guardarDatos(i); 
                            }
                        }
                        musica.stop();
                        game.addState(new Nivel1(nombre));
                        game.enterState(1,FadeOutTransition.class.newInstance(), FadeInTransition.class.newInstance());
                    } catch (InstantiationException ex) {
                        Logger.getLogger(MenuSelectLevelGame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(MenuSelectLevelGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    System.err.println("Nivel no alcanzado");
                    this.notif.aniadirNotificacion(this.notif.getImgNotf()[8]);
                }
                break;
            case 1:
                //Level2
                estado = -1;
                if(mapa2.getMostrar().size() > 0 && mapa2.getMostrar().containsKey(nombre)){
                    try {
                        for(int i = 3;i<(nivelMax+1);i++){//borrar toda información desde el nivel 2 en adelante
                            if(mapa2.getMostrar().containsKey(nombre)){
                                mapa2.bajaJugador(mapa2.getMostrar().get(nombre));
                                mapa2.guardarDatos(i); 
                            }
                        }
                        game.addState(new Nivel2(nombre));
                        game.enterState(2,FadeOutTransition.class.newInstance(), FadeInTransition.class.newInstance());
                    } catch (InstantiationException ex) {
                        Logger.getLogger(MenuSelectLevelGame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(MenuSelectLevelGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    System.err.println("Nivel no alcanzado");
                    this.notif.aniadirNotificacion(this.notif.getImgNotf()[8]);
                }
                break;
            case 2:
                //Level3
                estado = -1;
                if(mapa3.getMostrar().size() > 0 && mapa3.getMostrar().containsKey(nombre)){
                    try {
                        for(int i = 4;i<(nivelMax+1);i++){//borrar toda información desde el nivel 2 en adelante
                            if(mapa3.getMostrar().containsKey(nombre)){
                                mapa3.bajaJugador(mapa3.getMostrar().get(nombre));
                                mapa3.guardarDatos(i); 
                            }
                        }
                        game.addState(new Nivel3(nombre));
                        game.enterState(3,FadeOutTransition.class.newInstance(), FadeInTransition.class.newInstance());
                    } catch (InstantiationException ex) {
                        Logger.getLogger(MenuSelectLevelGame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(MenuSelectLevelGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    System.err.println("Nivel no alcanzado");
                    this.notif.aniadirNotificacion(this.notif.getImgNotf()[8]);
                }
                /*
                if(almacenar.cargarDatos(3).size() > 0 && almacenar.cargarDatos(3).containsKey(nombre)){
                    game.addState(new MenuRankingGame(container));
                    game.enterState(3);
                }else{
                    System.err.println("Nivel no alcanzado");
                    this.notif.aniadirNotificacion(this.notif.getImgNotf()[8]);
                }*/
                break;
            default:
                break;
        }
    }
    
    @Override
    public void componentActivated(AbstractComponent source) {
        for(int i = 0;i<botones.length;i++){
            if (source == botones[i]) {
                estado = i;
            }
        }
    }
    
    @Override
    public int getID() {
        return -4;
    }
}
