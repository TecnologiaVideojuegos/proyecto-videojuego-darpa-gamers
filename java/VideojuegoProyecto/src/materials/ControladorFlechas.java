/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package materials;

import imagen.SpriteMovil;
import java.util.ArrayList;
import location.*;
import map.Escena;
import menu.MenuPauseGame;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Senapi Aroal
 */
public class ControladorFlechas {
    
    private final ArrayList<SpriteMovil> flechas;
    private final ArrayList<Rectangle> colisiones;
    private final Sound colision;

    public ControladorFlechas() throws SlickException{
        this.flechas = new ArrayList<>();
        this.colisiones = new ArrayList<>();
        this.colision = new Sound("./res/audio/sounds/colision.ogg");
    }
      
    /**
     * Método que añade la flecha he indica la dirección del disparo
     * 
     * @param evolucion
     * @param tipo dirección del disparo
     * @param x origen en eje x donde saldrá
     * @param y origen en eje y donde saldrá
     * @param dx dirección en eje x de disparo
     * @param dy dirección en eje y de disparo
     * @param px origen del polígono eje x
     * @param py origne del polígono eje y
     * @param ancho tamaño de la flecha a lo ancho
     * @param alto tamaño de la flecha a lo largo
     * @throws SlickException 
     */
    public void addFlecha(int evolucion,int tipo,float x,float y,float dx,float dy,float px,float py,float ancho,float alto) throws SlickException{
        SpriteSheet img = new SpriteSheet("./res/grafico/disparos/magia_level_" + evolucion +".png",24,24);
        SpriteMovil flecha = new SpriteMovil(img.getSprite(tipo,0),new Punto(x,y),new Punto(dx,dy));
        Rectangle contorno = new Rectangle(px,py,ancho,alto);
        flechas.add(flecha);
        colisiones.add(contorno);
    }
    
    public void draw(Graphics g,MenuPauseGame menu){
        for(int i= 0;i<flechas.size();i++){
            flechas.get(i).draw();
            if(menu.isDebug()){
                g.draw(colisiones.get(i));
            }
        }
    }
    
    public void update(GameContainer container,Escena escena,int delta){
        for(int i= 0;i<flechas.size();i++){
            flechas.get(i).actualizarCoordenadas(delta);
            this.actualizarPoligono(i, delta,flechas.get(i).getVelocidad());
            delete(container,escena,delta);
        }
    }
    
    public void delete(GameContainer container,Escena escena,int delta){
        for(int i = 0;i<flechas.size();i++){
            if(flechas.get(i).getPosicion().getX()>container.getWidth() || flechas.get(i).getPosicion().getX()<0){
                colision.play(1.0f,0.5f);
                flechas.remove(i);
                colisiones.remove(i);
            }else if(flechas.get(i).getPosicion().getY()>container.getHeight() || flechas.get(i).getPosicion().getY()<0){
                colision.play(1.0f,0.5f);
                flechas.remove(i);
                colisiones.remove(i);
            }else if(colisiones.get(i).intersects(escena.getMapa_colision())){
                colision.play(1.0f,0.5f);
                flechas.remove(i);
                colisiones.remove(i);
            }else if(!escena.getMapa_colision().contains(colisiones.get(i))){
                colision.play(1.0f,0.5f);
                flechas.remove(i);
                colisiones.remove(i);
            }
        }
    }
    
    public void actualizarPoligono(int index,int delta,Vector velocidad){
        float x = colisiones.get(index).getX() + velocidad.getX() * ((float) delta/1000);
        float y = colisiones.get(index).getY() + velocidad.getY() * ((float) delta/1000);
        colisiones.get(index).setX(x);
        colisiones.get(index).setY(y);
    }

    public ArrayList<Rectangle> getColisiones() {
        return colisiones;
    }

    public ArrayList<SpriteMovil> getFlechas() {
        return flechas;
    }

}
