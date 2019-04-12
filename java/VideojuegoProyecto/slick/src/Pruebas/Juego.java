/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;

import org.newdawn.slick.*;

/**
 *
 * @author Senapi Aroal
 */
public class Juego extends BasicGame{

    private AppGameContainer contenedor;
    
    public Juego(String title) throws SlickException{
        super(title);
        contenedor = new AppGameContainer(this);
        contenedor.setDisplayMode(850,480, false); //Modificar tamaño de la ventana
        contenedor.setShowFPS(false);
    }

    @Override
    public void init(GameContainer container) throws SlickException {
       
    }

    public void iniciar() throws SlickException{
        contenedor.start();
    }
    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        g.drawString("Hola a todos putos",250,250);
    }
    public static void main(String args[]){
        try{
            Juego j1 = new Juego("Prueba");
            j1.iniciar();
        }catch(SlickException slick){
            slick.printStackTrace();
        }
    }
}
