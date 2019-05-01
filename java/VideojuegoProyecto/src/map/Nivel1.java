/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import characters.*;
import data_level.DatosNivel;
import java.util.ArrayList;
import location.Punto;
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
    private final int numEscenas = 1;  
    
    //Cantidad de objetos que tendrá cada escena
    private final int[] numObjetos = {2,0,0}; 
    
    //Variable para extraer toda la información acerca del nivel especificado
    private final DatosNivel datos;

    //Reloj para controlar movimiento
    private int reloj;
    
    private Monstruo enemigo;
    /**
     * Constructor de la clase Nivel1
     * 
     */   
    public Nivel1(){
        datos = new DatosNivel(numEscenas,numObjetos);
        datos.datosNivel1();
        reloj = 0;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
           j = new Jugador(100,datos.getRespawn(0),new SpriteSheet("./res/Character1.png",48,72),VELOCIDAD,0,50);
           //enemigo = new Monstruo(100,new Punto(480,110),new SpriteSheet("./res/flecha.png",24,22),VELOCIDAD,0,64,"Pasivo");
           j.getHud().iniciarJugador();
           for(int i = 0;i<numEscenas;i++){ 
               Escena es = new Escena(new TiledMap("./map/level1/test_escena"+(i+1)+".tmx","map/level1"),datos.mapasNivel(i),datos.objetosNivel(i),datos.entradasNivel(i),datos.salidasNivel(i));
               escenas.add(es);
           }
           entrada = container.getInput(); 
           
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        escenas.get(j.getEscenario()).getMapa_escena().render(0,0);
        j.getHud().imprimirCorazones();
        /*for(int i = 0;i<escenas.get(j.getEscenario()).getMapa_objetos().size();i++){
            g.draw(escenas.get(j.getEscenario()).getMapa_objetos().get(i));
        }
        g.draw(escenas.get(j.getEscenario()).getArea_entrada());
        g.draw(escenas.get(j.getEscenario()).getArea_salida());
        g.draw(escenas.get(j.getEscenario()).getMapa_colision());*/
        j.getArco().getFlecha().draw(g);
        g.draw(j.getPersL());
        g.draw(j.getPersR());
        g.draw(j.getPersUp());
        g.draw(j.getPersDown());
        g.drawString("escenario " + j.getEscenario(),20,20);
        g.drawString("municion " + j.getArco().getMunicion(),20,40);
    }
    
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        reloj+= delta;
        j.siguienteNivel(game,numEscenas,escenas);
        j.controlDeTeclado(delta,entrada,escenas);
        j.controlDeProyectil(entrada, container,escenas.get(j.getEscenario()), delta);
        j.comprobarLimite(escenas,datos);
    }
    
    public void corregirBug(){
        if(!escenas.get(j.getEscenario()).detectarPermanenciaMapa(j.getPersDown()) && !escenas.get(j.getEscenario()).detectarPermanenciaMapa(j.getPersUp()) && !escenas.get(j.getEscenario()).detectarPermanenciaMapa(j.getPersL()) && !escenas.get(j.getEscenario()).detectarPermanenciaMapa(j.getPersR())){
            System.out.println(datos.getRespawn(j.getEscenario()).getX() + " " + datos.getRespawn(j.getEscenario()).getY());
            j.setPunto(new Punto(500,300));
            j.actualizarPosicion();
              System.out.println(datos.getRespawn(0).getX() + " " + datos.getRespawn(0).getY());
  
            //datos.setRespawn(j.getEscenario(),j.getPunto());
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
