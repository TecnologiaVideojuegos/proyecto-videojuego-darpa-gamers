/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import characters.Jugador;
import imagen.Sprite;
import map.Escena;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import location.Punto;
import org.newdawn.slick.gui.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.*;

/**
 *
 * @author Senapi Aroal
 */
public class MenuPauseGame implements ComponentListener{
    
    private Sprite fondo,continuar,controles,salir,imgControl,atras,inicio,muerte;
    private MouseOverArea[] botones = new MouseOverArea[5];
    private boolean pausa,debug,control,gameOver;
    private int estado = -1;
    private GameContainer container;
    private Jugador j;

    public MenuPauseGame(GameContainer container,Jugador j) {
        try{
            this.j = j;
            this.pausa = false;
            this.debug = false;
            this.control = false;
            this.gameOver = false;
            this.container = container;
            this.fondo = new Sprite("./res/grafico/fonds/menu_pausa_nobotones_2.png");
            this.continuar = new Sprite("./res/grafico/buttons/boton_continuar.png",new Punto(250,250));
            this.controles = new Sprite("./res/grafico/buttons/boton_controles.png",new Punto(250,350));
            this.salir = new Sprite("./res/grafico/buttons/boton_salir.png",new Punto(378,500));
            this.atras = new Sprite("./res/grafico/buttons/boton_atras.png",new Punto(378,550));
            this.inicio = new Sprite("./res/grafico/buttons/boton_inicio.png",new Punto(250,400));
            this.imgControl = new Sprite("./res/grafico/game_utils/controles.png");
            this.muerte = new Sprite("./res/grafico/fonds/muerte"+ j.getNivelJugador() +".png");
            Sprite[] buttons = {continuar,controles,salir,atras,inicio};
            for(int i = 0;i<botones.length;i++){
                botones[i] = new MouseOverArea(container,buttons[i],(int)buttons[i].getPosicion().getX(),(int)buttons[i].getPosicion().getY(),buttons[i].getWidth(),buttons[i].getHeight(),this);         
                botones[i].setNormalColor(new Color(1,1,1,0.7f));
                botones[i].setMouseOverColor(new Color(1,1,1,0.9f));
            }
        }catch(SlickException ex){}
    }
    
    public void comprobarMenuPausa(Input entrada){
        if(entrada.isKeyPressed(Input.KEY_ESCAPE) && !control){
            if(pausa){
                pausa = false;
            }else{
                pausa = true;
            }
        }
        if(entrada.isKeyPressed(Input.KEY_F3) && !control){
            if(debug){
                debug = false;
            }else{
                debug = true;
            }
        }
        if(j.getHp() <= 0){
            this.gameOver = true;
        }
    }
    
    public void comprobarEstado(GameContainer container,StateBasedGame game,Music musica) throws SlickException{
        if(estado == 4){estado = 2;}
        switch (estado) {
            case 0:
                //Continuar
                estado = -1;
                pausa = false;
                break;
            case 1:
                //Controles
                estado = -1;
                pausa = false;
                control = true;
                break;
            case 2:
                //Salir
                estado = -1;    
                gameOver = false;
                try {
                    musica.stop();
                    game.enterState(0,FadeOutTransition.class.newInstance(), FadeInTransition.class.newInstance());
                } catch (InstantiationException ex) {
                    Logger.getLogger(MenuPauseGame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(MenuPauseGame.class.getName()).log(Level.SEVERE, null, ex); 
                }
                break;
            case 3:
                //Atras
                estado = -1;
                control = false;
                pausa = true;
                break;
            default:
                break;
        }
    }
    
    public void gestionarMenuPausa(Input entrada,GameContainer container,StateBasedGame game,Music musica) throws SlickException{
        this.comprobarMenuPausa(entrada);
        this.comprobarEstado(container, game,musica);
        
    }
    
    public void mostrarMenu(Graphics g,Jugador j,ArrayList<Escena> escenas){
        if(debug){
            g.draw(escenas.get(j.getEscenario()).getArea_entrada());
            g.draw(escenas.get(j.getEscenario()).getArea_salida());
            g.draw(escenas.get(j.getEscenario()).getMapa_colision());
            g.draw(j.getPersL());
            g.draw(j.getPersR());
            g.draw(j.getPersUp());
            g.draw(j.getPersDown()); 
            g.drawString("Eje x: " + String.format("%.3f",j.getPunto().getX()) + "  Eje y: " + String.format("%.3f",j.getPunto().getY()),20,20);
            g.drawString("Escenario " + j.getEscenario(),20,40);
            g.drawString("Municion " + j.getVarita().getMunicion(),20,60);
            g.drawString("Vida del jugador: " + j.getHp(),20,80);
            g.drawString("Velocidad del jugador: " + j.getVelocidad(),20 ,100 );
            g.drawString("Fuerza del jugador: " + j.getDanyo(),20 ,120 );
            g.drawString("Exp: " + j.getExperiencia() ,20,140);
            for(int i = 0;i<escenas.get(j.getEscenario()).getMapa_objetos().size();i++){
                g.draw(escenas.get(j.getEscenario()).getMapa_objetos().get(i));
            }
            for(int i = 0;i<escenas.get(j.getEscenario()).getEnemigos().size();i++){
                g.draw(escenas.get(j.getEscenario()).getEnemigos().get(i).getRango());
                g.draw(escenas.get(j.getEscenario()).getEnemigos().get(i).getPersDown());
                g.draw(escenas.get(j.getEscenario()).getEnemigos().get(i).getPersL());
                g.draw(escenas.get(j.getEscenario()).getEnemigos().get(i).getPersR());
                g.draw(escenas.get(j.getEscenario()).getEnemigos().get(i).getPersUp());  
                g.draw(escenas.get(j.getEscenario()).getEnemigos().get(i).getColision());
            }
            if(j.getBuffInv().getEstadoBuff()){
                g.drawString("Buff Invulnerabilidad: " + ((j.getBuffInv().getMaxTimeBuff()/1000) - (j.getBuffInv().getTimerEstadoBuff()/1000) ) + " seg" ,20,160);
            }
            if(j.getBuffFuerza().getEstadoBuff()){
                g.drawString("Buff Fuerza: " + ((j.getBuffFuerza().getMaxTimeBuff()/1000) - (j.getBuffFuerza().getTimerEstadoBuff()/1000) ) + " seg" ,20,180);
            }
            if(j.getBuffVelo().getEstadoBuff()){
                g.drawString("Buff Velocidad: " + ((j.getBuffVelo().getMaxTimeBuff()/1000) - (j.getBuffVelo().getTimerEstadoBuff()/1000) ) + " seg" ,20,200);
            }
        }
        if(pausa){
            fondo.draw();
            for(int i = 0;i<(botones.length-2);i++){
                botones[i].render(container, g);
            }
        }
        if(control){
            imgControl.draw();
            botones[3].render(container, g);
        }
        if(gameOver){
            muerte.draw();
            botones[4].render(container, g);
        }
    }
    
    public boolean isGameOver(){
        return gameOver;
    }
    
    public boolean isPausa() {
        return pausa;
    }

    public boolean isDebug() {
        return debug;
    }
    
    @Override
    /**
     * @see org.newdawn.slick.gui.ComponentListener#componentActivated(org.newdawn.slick.gui.AbstractComponent)
     */
    public void componentActivated(AbstractComponent source) {
        for(int i = 0;i<botones.length;i++){
            if (source == botones[i]) {
                estado = i;
            }
        }
    }
    
}
