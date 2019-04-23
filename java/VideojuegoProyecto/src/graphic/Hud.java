/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import java.util.ArrayList;
import location.Punto;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Senapi Aroal
 */
public class Hud {
    
    private final int numCorazonesMin = 2;
    private final int numCorazonesMax = 6;
    private final ArrayList<Image> listaCorazones;
    private final Image[] listaBasica;
    
    /**
     * Constructor de la clase Hud
     * 
     * @throws org.newdawn.slick.SlickException
     */
    public Hud() throws SlickException{
        listaCorazones = new ArrayList<>();
        listaBasica = new Image[5];
        inicio();
    }

    /**
     * inicializa los dibujos gráficos de la vida del jugador
     * 
     * @throws SlickException se trata por error de obtener la imagen 
     */
    private void inicio() throws SlickException{
        for(int i = 0;i<=100;i+=25){
            listaBasica[(i/25)] = (new Image("./res/corazones_jugador/corazon_" + i +".png"));
        }
    }
    
    /**
     * añade los corazones mínimos con los que el jugador comenzará la historia
     * 
     */
    public void iniciarJugador(){
        for(int i = 0;i<numCorazonesMin;i++){
            listaCorazones.add(listaBasica[4]);
        }
    }
    
    /**
     * imprime los corazones llamando a dibujar especificando la posición en el escenario
     * 
     */
    public void imprimirCorazones(){
        for(int i = 0; i<listaCorazones.size();i++){
            this.dibujarCorazon(listaCorazones.get(i),(new Punto((i*32)+800,40)));  
        }
    }
    
    /**
     * dibujar los corazones 
     * 
     * @param img imagen a mostrar
     * @param punto posición donde se mostrará
     */
    public void dibujarCorazon(Image img,Punto punto){
        img.draw(punto.getX(),punto.getY());
    }
    
    /**
     * Get the value of listaCorazones
     *
     * @return the value of listaCorazones
     */
    public ArrayList<Image> getListaCorazones() {
        return listaCorazones;
    }
    
    /**
     * método que averigua la imagen en la que está actualmente
     * 
     * @param img imagen que queremos averiguar
     * @return devuelve un entero indicando su posición en el array
     */
    public int valorActual(Image img){
        for(int i=0;i<5;i++){
            if(img.equals(listaBasica[i])){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * incremente la vida del jugador en un 25% (de forma gráfica)
     * si excede el máximo no añade más
     * 
     */
    public void anadirVida(){
        for(int i = 0;i<this.getListaCorazones().size();i++){
            if(!(this.getListaCorazones().get(i).equals(listaBasica[4]))){
                this.getListaCorazones().set(i,listaBasica[(valorActual(this.getListaCorazones().get(i))+1)]);
                break;
            }
        }
    }
    
    /**
     * decrementa la vida del jugador en un 25% (de forma gráfica)
     * si excede el mínimo el jugador habrá muerto
     * 
     */
    public void quitarVida(){
        for(int i = (this.getListaCorazones().size()-1);i>=0;i--){
            if(!(this.getListaCorazones().get(i).equals(listaBasica[0]))){
                this.getListaCorazones().set(i,listaBasica[(valorActual(this.getListaCorazones().get(i))-1)]);
                break;
            }
        }
    }
    
    /** 
     * añade un corazón más a la listta de los que tiene el jugador actualmente
     * (incrementa la vida en un 100%)
     * 
     */
    public void anadirCorazon(){
        if(this.numCorazonesMax != this.getListaCorazones().size()){
            this.getListaCorazones().add(listaBasica[0]);
            for(int i = 0;i<4;i++){
                this.anadirVida();
            }
        }else{
            System.out.println("Limite max");
        }
    }
}
