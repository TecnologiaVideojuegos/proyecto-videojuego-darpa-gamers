/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import java.util.ArrayList;
import location.Punto;
import materials.Buff;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Senapi Aroal
 */
public class Hud {
    
    private int numCorazonesMin;
    private final int numCorazonesMax = 6;
    private final ArrayList<Image> listaCorazones;
    private final Image[] listaBasica;
    private final Image [] listaEstadosPociones;
    private final Image exp_municion;
    private final Image open_cofre;
    private boolean estado_cerca_cofre;
    
    /**
     * Constructor de la clase Hud
     * 
     * @throws org.newdawn.slick.SlickException
     */
    public Hud() throws SlickException{ 
        this.numCorazonesMin = 2;
        listaEstadosPociones = new Image[3];
        listaCorazones = new ArrayList<>();
        listaBasica = new Image[5];
        exp_municion = new Image("./res/grafico/hud_exp/hud_exp_mun.png");
        open_cofre = new Image("./res/grafico/game_utils/icon_cofre_2.png");
        estado_cerca_cofre = false;
        inicio();
        cargar_img_estados_pociones();
    }
   
    
    /**
     * 
     * Carga las imagenes de los estados de las pociones
     * 
     * @throws SlickException se trata por error de obtener la imagen 
     * 
     */
    public void cargar_img_estados_pociones() throws SlickException{
        
        listaEstadosPociones[0] = (new Image("./res/grafico/estados_pociones/estado_dmg.png"));
        listaEstadosPociones[1] = (new Image("./res/grafico/estados_pociones/estado_invul.png")); 
        listaEstadosPociones[2] = (new Image("./res/grafico/estados_pociones/estado_velo.png")); 
        
    }
    
    
    /**
     * 
     * Metodo para mostrar imagenes de los estados actuales
     * 
     * @param fuerza buff
     * @param invul buff
     * @param velo  buff
     */
    public void imprime_estados(Buff fuerza, Buff invul, Buff velo){
        
        if(fuerza.getEstadoBuff()){
            this.dibujarElem(this.listaEstadosPociones[0], (new Punto(950, 150)));
            
        }
        if(invul.getEstadoBuff()){
            this.dibujarElem(this.listaEstadosPociones[1], (new Punto(950, 200)));
            
        }
        if(velo.getEstadoBuff()){
            this.dibujarElem(this.listaEstadosPociones[2], (new Punto(950, 250)));
            
        }
        
    }
    
    /**
     * 
     * Metodo para pintar estados 
     * 
     * @param img
     * @param punto 
     */
    public void dibujarElem(Image img,Punto punto){
        img.draw(punto.getX(),punto.getY());
    }
    
    /**
     * inicializa los dibujos gráficos de la vida del jugador
     * 
     * @throws SlickException se trata por error de obtener la imagen 
     */
    private void inicio() throws SlickException{
        for(int i = 0;i<=100;i+=25){
            listaBasica[(i/25)] = (new Image("./res/grafico/corazones_jugador/corazon_" + i +".png"));
        }
    }
    
    /**
     * añade los corazones mínimos con los que el jugador comenzará la historia
     * 
     */
    public void iniciarJugador(int hp){
        for(int i = 0;i<numCorazonesMin;i++){
            listaCorazones.add(listaBasica[0]);            
        }
        for(int i = 0;i<hp;i+=25){
            this.anadirVida();
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
     * añade un corazón más a la lista de los que tiene el jugador actualmente
     * (incrementa la vida en un 100%)
     * 
     */
    public void anadirCorazon(){
        if(this.numCorazonesMax != this.getListaCorazones().size()){
            this.numCorazonesMin++;
            this.getListaCorazones().add(listaBasica[0]);
            for(int i = 0;i<4;i++){
                this.anadirVida();
            }
        }else{
            System.out.println("Limite max");
        }
    }

    public int getNumCorazonesMin() {
        return numCorazonesMin;
    }

    public void setNumCorazonesMin(int numCorazonesMin) {
        this.numCorazonesMin = numCorazonesMin;
    }
    
    
    
    /**
     * 
     * Metodo para pintar la munición y la experiencia
     * 
     * @param g para pintar las strings en pantalla 
     * @param municion del personaje 
     * @param exp  del personaje
     */
    public void imprimirHudExpMunicion(Graphics g, int municion, int exp){
        this.exp_municion.draw( 2.0f, 10.0f,0.7f);
        g.drawString(exp+"", 100.0f, 25.0f);
        g.drawString(""+municion, 125.0f, 68.0f);
        
    }
    
    /**
     * 
     * Metodo para imprimir al jugador si está cerca de un cofre la tecla R
     * 
     * @param punto_jugador posicion a hacer el draw
     */
    public void imprimirTeclaAbrirCofre(Punto punto_jugador){
    
        if(this.estado_cerca_cofre)
            this.open_cofre.draw( punto_jugador.getX(), punto_jugador.getY());
    }
    
    /**
     * 
     * @return Devuelve si el jugador está cerca de un cofre
     */
    public boolean getEstadoCercaCofre(){
        return this.estado_cerca_cofre;
    }
    
     /**
     * 
     */
    public void setEstadoCercaCofre(boolean state){
        this.estado_cerca_cofre = state;
    }
    
}
