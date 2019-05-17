/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import characters.Jugador;
import imagen.Sprite;
import location.Punto;
import org.newdawn.slick.gui.*;
import org.newdawn.slick.*;

/**
 *
 * @author Senapi Aroal
 */
public class MenuPauseGame implements ComponentListener{
    
    private Sprite fondo,continuar,salir;
    private MouseOverArea[] botones = new MouseOverArea[2];
    private boolean pausa,debug;
    private int estado = -1;
    private GameContainer container;

    public MenuPauseGame(GameContainer container) {
        try{
            this.pausa = false;
            this.debug = false;
            this.container = container;
            this.fondo = new Sprite("./res/fonds/fondo.png");
            this.continuar = new Sprite("./res/buttons/boton_inicio.png",new Punto(200,300));
            this.salir = new Sprite("./res/buttons/boton_salir.png");
            Sprite[] buttons = {continuar,salir};
            for(int i = 0;i<botones.length;i++){
                botones[i] = new MouseOverArea(container,buttons[i],(int)buttons[i].getPosicion().getX(),(int)buttons[i].getPosicion().getY(),buttons[i].getWidth(),buttons[i].getHeight(),this);         
                botones[i].setNormalColor(new Color(1,1,1,0.7f));
                botones[i].setMouseOverColor(new Color(1,1,1,0.9f));
            }
        }catch(SlickException ex){}
    }
    
    public void comprobarMenuPausa(Input entrada){
        if(entrada.isKeyPressed(Input.KEY_ESCAPE)){
            if(pausa){
                pausa = false;
            }else{
                pausa = true;
            }
        }
        if(entrada.isKeyPressed(Input.KEY_F3)){
            if(debug){
                debug = false;
            }else{
                debug = true;
            }
        }
    }
    
    public void mostrarMenu(Graphics g,Jugador j){
        if(debug){
            g.drawString("Eje x: " + String.format("%.3f",j.getPunto().getX()) + "  Eje y: " + String.format("%.3f",j.getPunto().getY()),20,20);
            g.drawString("Escenario " + j.getEscenario(),20,40);
            g.drawString("Municion " + j.getVarita().getMunicion(),20,60);
            g.drawString("Vida del jugador: " + j.getHp(),20,80);
            g.drawString("Velocidad del jugador: " + j.getVelocidad(),20 ,100 );
            g.drawString("Fuerza del jugador: " + j.getDanyo(),20 ,120 );
            g.drawString("Exp: " + j.getExperiencia() ,20,140);
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
            for (MouseOverArea botone : botones) {
                botone.render(container, g);
            }
        }
    }

    public void debug(Graphics g){
        
    }
    
    public boolean isPausa() {
        return pausa;
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
