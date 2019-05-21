/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import characters.*;
import data_level.DatosNivel;
import exception_serialization.AlmacenarAvatar;
import graphic.Historia;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import location.Punto;
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
    private final int[] numEnemigos = {0,1,2,2};
    
    //Cantidad de cofres que tendrá cada escena
    private final int[] numCofres = {3,1,2,1};
    
    //Variable para extraer toda la información acerca del nivel especificado
    private DatosNivel datos  = new DatosNivel(numEscenas,numObjetos,numEnemigos,numCofres);

    //Reloj para controlar movimiento
    private int reloj;
    
    //Menú que se encargar del modo pausa y modo debug
    private MenuPauseGame menu;
    
    //Música del nivel 1
    private Music music;
    
    //Historia lvl1
    private Historia historia1;
    
    
    
    
    private AlmacenarAvatar mapa =  new AlmacenarAvatar();
    
    /**
     * Constructor de la clase Nivel1
     * 
     * @param nombre
     */
    public Nivel1(String nombre) throws SlickException{
        //Historia
        this.historia1 = new Historia(new Punto(100,546), 8000, new Image("./res/grafico/historia/Dialogo1.png"));
        
        datos.datosNivel1();
        j = mapa.cargarDatos(1).get(nombre).devolverJugador(datos);
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
        music = new Music("./res/audio/music/musicaNivel1.ogg");
        menu = new MenuPauseGame(container,j);
        /*  Daño fijado a 50 en el primer nivel this.getNivelJugador()*50 */
        j.getHud().iniciarJugador(j.getHp());   
        entrada = container.getInput(); 
        music.loop(1.0f,0.2f);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        //Mapa
        escenas.get(j.getEscenario()).getMapa_escena().render(0,0);     
        j.getHud().imprimirRestosEnemigos();
        
        //Ataques
        j.getVarita().getFlecha().draw(g,menu);
        
        //Cofres
        for(int i = 0; i < escenas.get(j.getEscenario()).getCofres().size(); i++){
            escenas.get(j.getEscenario()).getCofres().get(i).imprimir_cofre();
            escenas.get(j.getEscenario()).getCofres().get(i).imprimirTeclaAbrirCofre();
        }
        
        //Imprimir jugador
        if(!menu.isGameOver()){
            j.imprimirJugador();
        }
        
        //Imprimir enemigos
        for(int i = 0;i<escenas.get(j.getEscenario()).getEnemigos().size();i++){
            escenas.get(j.getEscenario()).getEnemigos().get(i).imprimirEnemigo();
        }  
        
        //Historia
        this.historia1.imprimirHistoria();
        
        //HUD e inventario
        j.getInventario().imprimirImagenInfoPociones();   
        j.getHud().imprimirCorazones();
        j.getInventario().imprimirInventario();
        j.getHud().imprime_estados( j.getBuffFuerza(), j.getBuffInv(), j.getBuffVelo());
        j.getHud().imprimirHudExpMunicion(g,j.getVarita().getMunicion() , j.getExperiencia());
        j.getNoti().imprimirNotificaciones();
        
        
        
        menu.mostrarMenu(g,j,escenas);
    }
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        reloj+= delta;
        this.historia1.controlHistoria(delta);
        menu.gestionarMenuPausa(entrada, container, game,music);
        if(!menu.isGameOver()){
            if(!menu.isPausa()){
                j.gestionarJugador(container, game, numEscenas, delta, entrada, datos, escenas,music,mapa);
            }
        }else{
            j.setPunto(new Punto());
            j.actualizarPosicion();
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