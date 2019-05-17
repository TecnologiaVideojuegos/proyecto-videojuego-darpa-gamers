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
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Senapi Aroal
 */
public class ControladorFlechas {
    
    private final ArrayList<SpriteMovil> flechas;
    private final ArrayList<Rectangle> colisiones;

    public ControladorFlechas() {
        this.flechas = new ArrayList<>();
        this.colisiones = new ArrayList<>();
    }
      
    /**
     * Método que añade la flecha he indica la dirección del disparo
     * 
     * @param tipo dirección del disparo
     * @param x origen en eje x donde saldrá
     * @param y origen en eje y donde saldrá
     * @param dx dirección en eje x de disparo
     * @param dy dirección en eje y de disparo
     * @param ancho tamaño de la flecha a lo ancho
     * @param alto tamaño de la flecha a lo largo
     * @throws SlickException 
     */
    public void addFlecha(int evolucion,int tipo,float x,float y,float dx,float dy,float ancho,float alto) throws SlickException{
        SpriteSheet img = new SpriteSheet("./res/grafico/disparos/magia_level_" + evolucion +".png",24,24);
        SpriteMovil flecha = new SpriteMovil(img.getSprite(tipo,0),new Punto(x,y),new Punto(dx,dy));
        Rectangle contorno = new Rectangle(x,y,ancho,alto);
        flechas.add(flecha);
        colisiones.add(contorno);
    }
    
    public void draw(Graphics g){
        for(int i= 0;i<flechas.size();i++){
            flechas.get(i).draw();
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
                flechas.remove(i);
                colisiones.remove(i);
            }else if(flechas.get(i).getPosicion().getY()>container.getHeight() || flechas.get(i).getPosicion().getY()<0){
                flechas.remove(i);
                colisiones.remove(i);
            }else if(colisiones.get(i).intersects(escena.getMapa_colision())){
                flechas.remove(i);
                colisiones.remove(i);
            }
            //caso de que colisione on un enemigo
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
