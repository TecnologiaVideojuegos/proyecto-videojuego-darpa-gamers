/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import characters.Jugador;
import data_level.DatosNivel;
import java.util.ArrayList;
import materials.ControladorFlechas;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.*;

/**
 *
 * @author Senapi Aroal
 */
public class Nivel1 extends BasicGameState{

    //Arraylist donde guardamos todas las escenas de ese nivel
    private final ArrayList<Escena> escenas = new ArrayList<>(); 
    
    //Velocidad
    private final float VELOCIDAD = 100.0f; 
    
    //Variable para obtener las funciones del teclado
    private Input entrada;
    
    //Variable que representa al jugador
    private Jugador j; 
    
    //Variable que indica cuantas escenas tiene este nivel
    private final int numEscenas = 3;  
    
    //Cantidad de objetos que tendrá cada escena
    private final int[] numObjetos = {2,0,0}; 
    
    //Variable para extraer toda la información acerca del nivel especificado
    private final DatosNivel datos;

    
    private ControladorFlechas flechas;
    /**
     * Constructor de la clase Nivel1
     * 
     */   
    public Nivel1(){
        datos = new DatosNivel(numEscenas,numObjetos);
        datos.datosNivel1();
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
           j = new Jugador(100,datos.getInicio(),new SpriteSheet("./res/Character1.png",48,72),VELOCIDAD,0);
           j.getHud().iniciarJugador();
           for(int i = 0;i<numEscenas;i++){ 
               Escena es = new Escena(new TiledMap("./map/level1/test_escena"+(i+1)+".tmx","map/level1"),datos.mapasNivel(i),datos.objetosNivel(i),datos.entradasNivel(i),datos.salidasNivel(i));
               escenas.add(es);
           }
           entrada = container.getInput(); 
           flechas = new ControladorFlechas();
           
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        escenas.get(j.getEscenario()).getMapa_escena().render(0,0);
        j.getHud().imprimirCorazones();
        flechas.draw(g);
        /*for(int i = 0;i<escenas.get(j.getEscenario()).getMapa_objetos().size();i++){
            g.draw(escenas.get(j.getEscenario()).getMapa_objetos().get(i));
        }
        g.draw(escenas.get(j.getEscenario()).getArea_entrada());
        g.draw(escenas.get(j.getEscenario()).getArea_salida());
        g.draw(escenas.get(j.getEscenario()).getMapa_colision());*/
        g.draw(j.getPersL());
        g.draw(j.getPersR());
        g.draw(j.getPersUp());
        g.draw(j.getPersDown());
        g.drawString("escenario " + j.getEscenario(),20,20);
        g.drawString("municion " + j.getMunicion(),20,40);
    }
    
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        j.siguienteNivel(game,numEscenas,escenas);
        j.controlDeTeclado(delta,entrada,escenas);
        j.comprobarLimite(escenas,datos);
        flechas.update(container,escenas.get(j.getEscenario()),delta);
        if(entrada.isKeyPressed(Input.KEY_X)){
            j.getHud().quitarVida();
        }
        if(entrada.isKeyPressed(Input.KEY_Y)){
            j.getHud().anadirVida();
        }
        if(entrada.isKeyPressed(Input.KEY_SPACE) && j.getMunicion()!= 0){
            controlDisparo();
        }
    }
 
    public void controlDisparo() throws SlickException{
        if(entrada.isKeyDown(Input.KEY_RIGHT) && entrada.isKeyDown(Input.KEY_UP)){
            
            j.decrementarMunicion();
            flechas.addFlecha(j.getPersUp().getMaxX(),j.getPersR().getMaxY(),300,-300);
            
        }else if(entrada.isKeyDown(Input.KEY_RIGHT) && entrada.isKeyDown(Input.KEY_DOWN)){
            
            j.decrementarMunicion();
            flechas.addFlecha(j.getPersDown().getMinX(),j.getPersR().getMinY(),300,300);
            
        }else if(entrada.isKeyDown(Input.KEY_LEFT) && entrada.isKeyDown(Input.KEY_UP)){
            
            j.decrementarMunicion();
            flechas.addFlecha(j.getPersUp().getMinX(),j.getPersL().getMaxY(),-300,-300);
            
        }else if(entrada.isKeyDown(Input.KEY_LEFT) && entrada.isKeyDown(Input.KEY_DOWN)){
            
            j.decrementarMunicion();
            flechas.addFlecha(j.getPersDown().getMaxX(),j.getPersL().getMinY(),-300,300);
            
        }else if(entrada.isKeyDown(Input.KEY_RIGHT)){
            
            j.decrementarMunicion();
            flechas.addFlecha(j.getPersR().getX(),j.getPersR().getY(),300,0);
            
        }else if(entrada.isKeyDown(Input.KEY_LEFT)){
            
            j.decrementarMunicion();
            flechas.addFlecha(j.getPersL().getX(),j.getPersL().getY(),-300,0);
            
        }else if(entrada.isKeyDown(Input.KEY_UP)){
            
            j.decrementarMunicion();
            flechas.addFlecha(j.getPersUp().getX(),j.getPersUp().getY(),0,-300);
            
        }else if(entrada.isKeyDown(Input.KEY_DOWN)){
            
            j.decrementarMunicion();
            flechas.addFlecha(j.getPersDown().getX(),j.getPersDown().getY(),0,300);
            
        }
        
    }
    
    @Override
    public void mouseClicked(int button, int x, int y, int clickCount){
            System.out.println("Eje x: " +x + " Eje y: " + y);
    }
       
    @Override
    public int getID() {
        return 1;
    }
       
}
