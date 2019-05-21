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
import java.util.logging.Level;
import java.util.logging.Logger;
import location.Punto;
import menu.MenuCreditsGame;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Senapi Aroal
 */
public class NivelFinal extends BasicGameState{

    private TiledMap map;
    private AlmacenarAvatar mapa = new AlmacenarAvatar();
    private AlmacenarAvatar mapa1 = new AlmacenarAvatar();
    private Music musica;
    private DatosNivel datos;
    private Jugador j;
   private long timer;
    
    //Historia 
    private Historia historia_final;

    
     public NivelFinal(String nombre) throws SlickException {
        timer = 0;
        historia_final = new Historia(new Punto(300,200),10000, new Image("./res/grafico/historia/Dialogo_final.png"));
        try{
            map = new TiledMap("./res/mapas/NivelFinal/layout_tmx/escena_1.tmx","./res/mapas/NivelFinal/resources_tsx");
            musica = new Music("./res/audio/music/calm.ogg");
            mapa.cargarNombres();
            /*
            if(mapa.getNombres().size() == 0){
                mapa.meterNombre(new Info_Jugador(j));
                mapa.guardarNombre();
                    }
            for(int i = 0;i< mapa.getNombres().size();i++){
                if(!mapa.getNombres().get(i).getNombre().equals(nombre)){
                        mapa.meterNombre(new Info_Jugador(j));
                        mapa.guardarNombre();
                        break;    
                }
            }*/
        }catch(SlickException ex){}
        musica.loop();
    }
    
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        this.map.render(0,0);
        this.historia_final.imprimirHistoria();
        
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        this.historia_final.controlHistoria(delta);
        controlFin(delta, game, container);
    }
    
    @Override
    public void enter(GameContainer container,StateBasedGame game) throws SlickException{
        container.getInput().clearKeyPressedRecord();
        init(container,game);
    }
    
    //Estado siguiente al último nivel jugable
    @Override
    public int getID() {
        return 4;
    }
    
    public void controlFin(int delta, StateBasedGame game, GameContainer x){
        
            this.timer += delta ;
            
           
            if( this.timer > 30000){
                //Ir a creditos 
                game.addState(new MenuCreditsGame(x));                    
                game.enterState(-7);
                
            }
        }
}
    

