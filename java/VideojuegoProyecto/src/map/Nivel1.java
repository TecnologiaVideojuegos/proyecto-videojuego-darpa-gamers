/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import characters.*;
import data_level.DatosNivel;
import java.util.ArrayList;
import materials.Cofre;
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
    private ArrayList<Escena> escenas;
    
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
    
    //Cantidad de enemigos que tendrá cada escena
    private final int[] numEnemigos = {1,0,2};
    
    //Cantidad de cofres que tendrá cada escena
    private final int[] numCofres = {1, 1, 0};
    
    //Variable para extraer toda la información acerca del nivel especificado
    private DatosNivel datos;

    //Reloj para controlar movimiento
    private int reloj;
    
    
    /**
     * Constructor de la clase Nivel1
     * 
     */   
    public Nivel1(){
        
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        
        
        escenas = new ArrayList<>(); 
        datos = new DatosNivel(numEscenas,numObjetos,numEnemigos,numCofres);
        datos.datosNivel1();
        reloj = 0;
        /*  Daño fijado a 50 en el primer nivel this.getNivelJugador()*50 */
        j = new Jugador(200,datos.getEntradas(0),new SpriteSheet("./res/Character1.png",48,72),VELOCIDAD,0,50,50);
        j.getHud().iniciarJugador();
        for(int i = 0;i<numEscenas;i++){ 
            Escena es = new Escena(new TiledMap("./map/level1/test_escena"+(i+1)+".tmx","map/level1"),datos.mapasNivel(i),datos.objetosNivel(i),datos.entradasNivel(i),datos.salidasNivel(i),datos.enemigosNivel(i),datos.cofresNivel(i));
            escenas.add(es);
        }
        entrada = container.getInput(); 
           
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        escenas.get(j.getEscenario()).getMapa_escena().render(0,0);
        j.getHud().imprimirCorazones();
        j.getInventario().imprimirInventario();
        j.getHud().imprime_estados( j.getBuffFuerza(), j.getBuffInv(), j.getBuffVelo());
        j.getInventario().imprimirImagenInfoPociones();
        j.getNoti().imprimirNotificaciones();
        
        for(int i = 0; i < escenas.get(j.getEscenario()).getCofres().size(); i++){
            escenas.get(j.getEscenario()).getCofres().get(i).imprimir_cofre();
        }
        /*for(int i = 0;i<escenas.get(j.getEscenario()).getMapa_objetos().size();i++){
            g.draw(escenas.get(j.getEscenario()).getMapa_objetos().get(i));
        }*/
        for(int i = 0;i<escenas.get(j.getEscenario()).getEnemigos().size();i++){
            g.draw(escenas.get(j.getEscenario()).getEnemigos().get(i).getRango());
            g.draw(escenas.get(j.getEscenario()).getEnemigos().get(i).getPersDown());
            g.draw(escenas.get(j.getEscenario()).getEnemigos().get(i).getPersL());
            g.draw(escenas.get(j.getEscenario()).getEnemigos().get(i).getPersR());
            g.draw(escenas.get(j.getEscenario()).getEnemigos().get(i).getPersUp());
        }
        /*g.draw(escenas.get(j.getEscenario()).getArea_entrada());
        g.draw(escenas.get(j.getEscenario()).getArea_salida());*/
        g.draw(escenas.get(j.getEscenario()).getMapa_colision());
        j.getVarita().getFlecha().draw(g);
        g.draw(j.getPersL());
        g.draw(j.getPersR());
        g.draw(j.getPersUp());
        g.draw(j.getPersDown());
        g.drawString("Escenario " + j.getEscenario(),20,20);
        g.drawString("Municion " + j.getVarita().getMunicion(),20,40);
        g.drawString("Vida del jugador: " + j.getHp(),20,60);
        g.drawString("Velocidad del jugador: " + j.getVelocidad(),20 ,80 );
        g.drawString("Fuerza del jugador: " + j.getDanyo(),20 ,100 );
        g.drawString("Exp: " + j.getExperiencia() ,20,120);
        if(j.getBuffInv().getEstadoBuff()){
            g.drawString("Buff Invulnerabilidad: " + ((j.getBuffInv().getMaxTimeBuff()/1000) - (j.getBuffInv().getTimerEstadoBuff()/1000) ) + " seg" ,20,140);
        }
        if(j.getBuffFuerza().getEstadoBuff()){
            g.drawString("Buff Fuerza: " + ((j.getBuffFuerza().getMaxTimeBuff()/1000) - (j.getBuffFuerza().getTimerEstadoBuff()/1000) ) + " seg" ,20,160);
        }
        if(j.getBuffVelo().getEstadoBuff()){
            g.drawString("Buff Velocidad: " + ((j.getBuffVelo().getMaxTimeBuff()/1000) - (j.getBuffVelo().getTimerEstadoBuff()/1000) ) + " seg" ,20,180);
        }
        g.drawString("Número enemigos: " + datos.enemigosNivel(j.getEscenario()).size(),20,220);
        
        /*
        for(int i = 0;i<escenas.get(j.getEscenario()).getEnemigos().size();i++){
            g.drawString("Velocidad: "+escenas.get(j.getEscenario()).getEnemigos().get(i).getVelocidad(),20,120+(20*i));
        }*/
        for(int i = 0;i<escenas.get(j.getEscenario()).getEnemigos().size();i++){
            g.drawString("Vida Enemigos: "+escenas.get(j.getEscenario()).getEnemigos().get(i).getHp(),20,240+(20*i));
        }
        
        
    }
    
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        reloj+= delta;
        j.gestionarJugador(container, game, numEscenas, delta, entrada, datos, escenas);
        for(int i = 0;i<escenas.get(j.getEscenario()).getEnemigos().size();i++){
            escenas.get(j.getEscenario()).getEnemigos().get(i).realizarMovimiento(j, escenas.get(j.getEscenario()), delta, reloj);
        }
        if(reloj >2000){
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
