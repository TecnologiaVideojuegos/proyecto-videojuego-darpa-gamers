/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import characters.Jugador;
import data_level.DatosNivel;
import exception_serialization.*;
import graphic.Historia;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import location.Punto;
import menu.MenuPauseGame;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Senapi Aroal
 * @author Davidcawork
 */
public class Nivel3 extends BasicGameState{

    //Arraylist donde guardamos todas las escenas de ese nivel
    private ArrayList<Escena> escenas = new ArrayList<>();
    
    //Variable para obtener las funciones del teclado
    private Input entrada;
    
    //Variable que representa al jugador
    private Jugador j; 
    
    //Variable que indica cuantas escenas tiene este nivel
    private final int numEscenas = 4;  
    
    //Cantidad de objetos que tendrá cada escena
    private final int[] numObjetos = {0,3,1,0}; 
    
    //Cantidad de enemigos que tendrá cada escena
    private final int[] numEnemigos = {4,4,4,0};
    
    //Cantidad de cofres que tendrá cada escena
    private final int[] numCofres = {1,4,2,0};
    
    //Variable para extraer toda la información acerca del nivel especificado
    private DatosNivel datos  = new DatosNivel(numEscenas,numObjetos,numEnemigos,numCofres);

    private Music musica;
    
    //Reloj para controlar movimiento
    private int reloj;
    
    //Historia
    private Historia historia3;
    
    private MenuPauseGame menu;
    
    private AlmacenarAvatar mapa =  new AlmacenarAvatar();
    
    /**
     * Constructor de la clase Nivel1
     * 
     * @param nombre
     */
    public Nivel3(String nombre) throws SlickException{
        //Historia
        this.historia3 = new Historia(new Punto(105,462), 8000, new Image("./res/grafico/historia/Dialogo3.png"));
        
        datos.datosNivel3();
        j = mapa.cargarDatos(3).get(nombre).devolverJugador(datos);
        for(int i = 0;i<numEscenas;i++){
            Escena es;
            try {
                es = new Escena(new TiledMap("./res/mapas/Nivel3/layout_tmx/escena_"+(i+1)+".tmx","./res/mapas/Nivel3/resources_tsx"),datos.mapasNivel(i),datos.objetosNivel(i),datos.entradasNivel(i),datos.salidasNivel(i),datos.enemigosNivel(i),datos.cofresNivel(i));
                escenas.add(es);
            } catch (SlickException ex) {
                Logger.getLogger(Nivel1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        reloj = 0;
        menu = new MenuPauseGame(container,j);
        /*  Daño fijado a 50 en el primer nivel this.getNivelJugador()*50 */
        j.getHud().iniciarJugador(j.getHp());   
        entrada = container.getInput(); 
        musica = new Music("./res/audio/music/musicaNivel3.ogg");
        musica.loop(1.0f,0.5f);
        j.getSoundLVLup().play();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        escenas.get(j.getEscenario()).getMapa_escena().render(0,0);  
        j.getHud().imprimirRestosEnemigos();
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
        this.historia3.imprimirHistoria();
        
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
        
        this.historia3.controlHistoria(delta);
        menu.gestionarMenuPausa(entrada, container, game,musica);
        if(!menu.isGameOver()){
            if(!menu.isPausa()){        
                j.gestionarJugador(container, game, numEscenas, delta, entrada, datos, escenas,musica,mapa);
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
        return 3;
    }
    
}

