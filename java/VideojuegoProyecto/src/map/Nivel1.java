/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import characters.*;
import data_level.DatosNivel;
import exception_serialization.AlmacenarAvatar;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import menu.MenuPauseGame;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.*;

/**
 *
 * @author Senapi Aroal
 * @author Davidcawork
 */
public class Nivel1 extends BasicGameState{

    //Arraylist donde guardamos todas las escenas de ese nivel
    private ArrayList<Escena> escenas = new ArrayList<>();
    
    //Variable para obtener las funciones del teclado
    private Input entrada;
    
    //Variable que representa al jugador
    private Jugador j; 
    
    //Variable que indica cuantas escenas tiene este nivel
    private final int numEscenas = 4;  
    
    //Cantidad de objetos que tendrá cada escena
    private final int[] numObjetos = {1,0,0,0}; 
    
    //Cantidad de enemigos que tendrá cada escena
    private final int[] numEnemigos = {0,0,0,1};
    
    //Cantidad de cofres que tendrá cada escena
    private final int[] numCofres = {3,1,2,1};
    
    //Variable para extraer toda la información acerca del nivel especificado
    private DatosNivel datos  = new DatosNivel(numEscenas,numObjetos,numEnemigos,numCofres);

    //Reloj para controlar movimiento
    private int reloj;
    
    private MenuPauseGame menu;
    
    private AlmacenarAvatar almacenar = new AlmacenarAvatar();
    /**
     * Constructor de la clase Nivel1
     * 
     * @param nombre
     */
    public Nivel1(String nombre){
        datos.datosNivel1();
        j = almacenar.cargarDatos(1).get(nombre).devolverJugador(datos);
        for(int i = 0;i<numEscenas;i++){
            Escena es;
            try {
                es = new Escena(new TiledMap("./res/mapas/Nivel1/layout_tmx/escena_"+(i+1)+".tmx","./res/mapas/Nivel1/resources_tsx"),datos.mapasNivel(i),datos.objetosNivel(i),datos.entradasNivel(i),datos.salidasNivel(i),datos.enemigosNivel(i),datos.cofresNivel(i));
                escenas.add(es);
            } catch (SlickException ex) {
                Logger.getLogger(Nivel1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        reloj = 0;
        menu = new MenuPauseGame(container);
        /*  Daño fijado a 50 en el primer nivel this.getNivelJugador()*50 */
        j.getHud().iniciarJugador();   
        entrada = container.getInput(); 
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        escenas.get(j.getEscenario()).getMapa_escena().render(0,0);
        g.draw(j.getPersL());
        g.draw(j.getPersR());
        g.draw(j.getPersUp());
        g.draw(j.getPersDown());      
        j.getVarita().getFlecha().draw(g);
        
        
        for(int i = 0; i < escenas.get(j.getEscenario()).getCofres().size(); i++){
            escenas.get(j.getEscenario()).getCofres().get(i).imprimir_cofre();
        }
        for(int i = 0;i<escenas.get(j.getEscenario()).getMapa_objetos().size();i++){
            g.draw(escenas.get(j.getEscenario()).getMapa_objetos().get(i));
        }
       
        for(int i = 0;i<escenas.get(j.getEscenario()).getEnemigos().size();i++){
            g.draw(escenas.get(j.getEscenario()).getEnemigos().get(i).getRango());
            g.draw(escenas.get(j.getEscenario()).getEnemigos().get(i).getPersDown());
            g.draw(escenas.get(j.getEscenario()).getEnemigos().get(i).getPersL());
            g.draw(escenas.get(j.getEscenario()).getEnemigos().get(i).getPersR());
            g.draw(escenas.get(j.getEscenario()).getEnemigos().get(i).getPersUp());
            
        }
        j.imprimirJugador();
        //Imprimir enemigos
        for(int i = 0;i<escenas.get(j.getEscenario()).getEnemigos().size();i++){
            escenas.get(j.getEscenario()).getEnemigos().get(i).imprimirEnemigo();
        }
        
        g.draw(escenas.get(j.getEscenario()).getArea_entrada());
        g.draw(escenas.get(j.getEscenario()).getArea_salida());
        g.draw(escenas.get(j.getEscenario()).getMapa_colision());
        /*
        for(int i = 0;i<escenas.get(j.getEscenario()).getEnemigos().size();i++){
            g.drawString("Velocidad: "+escenas.get(j.getEscenario()).getEnemigos().get(i).getVelocidad(),20,120+(20*i));
        }*/
        for(int i = 0;i<escenas.get(j.getEscenario()).getEnemigos().size();i++){
            g.drawString("Vida Enemigos: "+escenas.get(j.getEscenario()).getEnemigos().get(i).getHp(),20,240+(20*i));
        }        
        j.getInventario().imprimirImagenInfoPociones();   
        
         j.getHud().imprimirCorazones();
        j.getInventario().imprimirInventario();
        j.getHud().imprime_estados( j.getBuffFuerza(), j.getBuffInv(), j.getBuffVelo());
        
        j.getNoti().imprimirNotificaciones();
        menu.mostrarMenu(g,j);
    }
    
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        reloj+= delta;
        menu.gestionarMenuPausa(entrada, container, game);
        if(!menu.isPausa()){
            j.gestionarJugador(container, game, numEscenas, delta, entrada, datos, escenas,almacenar);
        } 
        for(int i = 0;i<escenas.get(j.getEscenario()).getEnemigos().size();i++){
            escenas.get(j.getEscenario()).getEnemigos().get(i).realizarMovimiento(j, escenas.get(j.getEscenario()), delta, reloj);
        }
        if(reloj > 2000){
            reloj = 0;
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
    public int getID() {
        return 1;
    }
       
}