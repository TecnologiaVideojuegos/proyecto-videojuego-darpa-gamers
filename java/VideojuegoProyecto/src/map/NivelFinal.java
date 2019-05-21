/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import characters.Jugador;
import data_level.DatosNivel;
import exception_serialization.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 *
 * @author Senapi Aroal
 */
public class NivelFinal extends BasicGameState{

    
    private AlmacenarAvatar mapa = new AlmacenarAvatar();
    private Music musica;
    private DatosNivel datos;
    private Jugador j;

    public NivelFinal(String nombre) {
        datos = new DatosNivel();
        datos.datosNivel3();
        j = mapa.cargarDatos(3).get(nombre).devolverJugador(datos);
        mapa.cargarNombres();
            if(mapa.getNombres().size() == 0){
            try {
                mapa.meterNombre(new Info_Jugador(j));
                mapa.guardarNombre();
            } catch (SlickException ex) {
                Logger.getLogger(NivelFinal.class.getName()).log(Level.SEVERE, null, ex);
            }
                    }
            for(int i = 0;i< mapa.getNombres().size();i++){
                if(!mapa.getNombres().get(i).getNombre().equals(nombre)){
                    try {
                        mapa.meterNombre(new Info_Jugador(j));
                        mapa.guardarNombre();
                        break;
                    } catch (SlickException ex) {
                        Logger.getLogger(NivelFinal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
    }
    
    
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        j.imprimirJugador();
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        
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
    
}
