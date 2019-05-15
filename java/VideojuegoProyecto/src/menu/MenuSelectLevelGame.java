/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import exception_serialization.*;
import imagen.Sprite;
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
 */
public class MenuSelectLevelGame extends BasicGameState implements ComponentListener{
    
    private Sprite fondo,level1,level2,level3;
    private MouseOverArea[] botones = new MouseOverArea[3];
    //private boolean anadidoLevel1 = false,anadidoLevel2 = false,anadidoLevel3 = false;
    private AlmacenarAvatar almacenar;
    private String nombre;
    private int estado = -1;
    private final int nivelMax = 3;

    public MenuSelectLevelGame(GameContainer container,String nombre) {
        try{
            this.almacenar = new AlmacenarAvatar();
            this.nombre = nombre;
            fondo = new Sprite("./res/fonds/fondo.png");
            level1 = new Sprite("./res/buttons/boton_LEVEL1.png",new Punto(78,400));
            level2 = new Sprite("./res/buttons/boton_LEVEL2.png",new Punto(384,400));
            level3 = new Sprite("./res/buttons/boton_LEVEL3.png",new Punto(690,400)); 
            Sprite boton[] = {level1,level2,level3};
            for(int i = 0;i<botones.length;i++){
                botones[i] = new MouseOverArea(container,boton[i],(int)boton[i].getPosicion().getX(),(int)boton[i].getPosicion().getY(),(int)boton[i].getWidth(),(int)boton[i].getHeight(),this);
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
                //Levl1
                estado = -1;
                if(almacenar.cargarDatos(1).size() > 0 && almacenar.cargarDatos(1).get(nombre).getNivelMapaMax()>=1){
            try {
                for(int i = 2;i<=nivelMax;i++){//borrar toda información desde el nivel 2 en adelante
                    if(almacenar.cargarDatos(i).containsKey(nombre)){
                        almacenar.bajaJugador(almacenar.cargarDatos(i).get(nombre));
                        almacenar.guardarDatos(i);                      
                    }
                }
                game.addState(new Nivel1(nombre));
                game.enterState(1,FadeOutTransition.class.newInstance(), FadeInTransition.class.newInstance());
            } catch (InstantiationException ex) {
                Logger.getLogger(MenuSelectLevelGame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(MenuSelectLevelGame.class.getName()).log(Level.SEVERE, null, ex);
            }
                }else{
                    System.err.println("Nivel no alcanzado");
                }
                break;
            case 1:
                //Levl2
                estado = -1;
                if(almacenar.cargarDatos(2).size() > 0 && almacenar.cargarDatos(2).get(nombre).getNivelMapaMax()>=2){
                    for(int i = 3;i<=nivelMax;i++){//borrar toda información desde el nivel 2 en adelante
                        if(almacenar.cargarDatos(i).containsKey(nombre)){
                            almacenar.bajaJugador(almacenar.cargarDatos(i).get(nombre));
                            almacenar.guardarDatos(i);
                        } 
                    }
                    game.addState(new MenuRankingGame(container));
                    game.enterState(2);
                }else{
                    System.err.println("Nivel no alcanzado");
                }
                break;
            case 2:
                //Level3
                estado = -1;
                if(almacenar.cargarDatos(3).size() > 0 && almacenar.cargarDatos(3).get(nombre).getNivelMapaMax()>=3){
                    for(int i = 4;i<=nivelMax;i++){//borrar toda información desde el nivel 2 en adelante
                        if(almacenar.cargarDatos(i).containsKey(nombre)){
                            almacenar.bajaJugador(almacenar.cargarDatos(i).get(nombre));
                            almacenar.guardarDatos(i);
                        } 
                    }
                    game.addState(new MenuRankingGame(container));
                    game.enterState(3);
                }else{
                    System.err.println("Nivel no alcanzado");
                }
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
