/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package characters;

import data_level.DatosNivel;
import materials.Inventario;
import location.Punto;
import imagen.SpriteDinamica;
import java.util.ArrayList;
import map.Escena;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @version 0.1.10
 * @author Senapi Aroal
 */
public class Jugador extends Ente{

    //Variables que representan la hitbox del personaje
    private final Rectangle PersUp;
    private final Rectangle PersDown;
    private final Rectangle PersL;
    private final Rectangle PersR;
    
    //Variable que representa la experiencia que tiene el personaje
    private int experiencia;
    
    //Variable que contiene el inventario del personaje para guardar objetos
    private Inventario inventario;
    
    //Variable que represente el nive que posee el personaje
    private int nivelJugador;
    
    //Variable que indica el nivel del mapa en el que se encuentra el personaje
    private int nivelMapa;
    
    //Variable que indica en que escenario de un nivel se encuentra el personaje
    private int escenario;
    
    //Variable que guarda la imagen del personaje
    private final SpriteSheet img;
    
    /**
     * Constructor de la clase Jugador
     * 
     * @param hp vida del ente
     * @param punto lugar del mapa donde se posiciona
     * @param sprite imagen del ente
     * @param velocidad velocidad a la que se mueve el ente
     * @throws org.newdawn.slick.SlickException posibles exceptiones por la carga de la imagen
     * 
     */    
    public Jugador(int hp, Punto punto, SpriteSheet sprite, float velocidad) throws SlickException{
        super(hp, punto, sprite, velocidad);
        this.experiencia = 0; //inicializado
        this.nivelJugador = 1; //inicializado
        this.nivelMapa = 1; //inicializado
        this.escenario = 0; //inicializado
        this.img = sprite;
        this.PersUp = new Rectangle((this.getPunto().getX()+2),this.getPunto().getY(),12,1);
        this.PersDown = new Rectangle((this.getPunto().getX()+2),(this.getPunto().getY()+16),12,1);
        this.PersL = new Rectangle(this.getPunto().getX(),(this.getPunto().getY()+2),1,12);
        this.PersR = new Rectangle((this.getPunto().getX()+16),(this.getPunto().getY()+2),1,12);
    }

    /**
     * Get the value of inventario
     *
     * @return the value of inventario
     */
    public Inventario getInventario() {
        return inventario;
    }

    /**
     * Set the value of inventario
     *
     * @param inventario new value of inventario
     */
    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }
    /**
     * Get the value of experiencia
     *
     * @return the value of experiencia
     */
    public int getExperiencia() {
        return experiencia;
    }

    /**
     * Set the value of experiencia
     *
     * @param experiencia new value of experiencia
     */
    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }
    
    /**
     * Añade nueva experiencia ganada
     *
     * @param exp experiencia que gana el personaje
     */
    public void anadirExperiencia(int exp){
        this.setExperiencia(this.getExperiencia()+exp);
    }
    
    /**
     * Incrementa el nivel del jugador en 1
     *
     */
    public void subirNivelJugador(){
        this.nivelJugador++;
    }

    /**
     * Incrementa el escenario en 1
     *
     */
    public void avanzarEscenario(){
            this.escenario++;  
    }
    
    /**
     * Decrementa el escenario en 1
     *
     */
    public void retrocederEscenario(){
            this.escenario--;        
    }
    
    /**
     * Incrementa el mapa en 1
     *
     */
    public void avanzarMapa(){
        this.nivelMapa++;
    }
    
    /**
     * Get the value of jugador
     *
     * @return the value of nivelJugador
     */
    public int getNivelJugador() {
        return nivelJugador;
    }

    /**
     * Get the value of nivelMapa
     *
     * @return the value of nivelMapa
     */
    public int getNivelMapa() {
        return nivelMapa;
    }

    /**
     * Get the value of escenario
     *
     * @return the value of escenario
     */
    public int getEscenario() {
        return escenario;
    }

    /**
     * Obtiene el polígono superior del personaje
     *
     * @return el polígono superior del personaje
     */
    public Rectangle getPersUp() {
        return PersUp;
    }

    /**
     * Obtiene el polígono inferior del personaje
     *
     * @return el polígono inferior del personaje
     */
    public Rectangle getPersDown() {
        return PersDown;
    }

    /**
     * Obtiene el polígono izquierdo del personaje
     *
     * @return el polígono izquierdo del personaje
     */
    public Rectangle getPersL() {
        return PersL;
    }

    /**
     * Obtiene el polígono derecho del personaje
     *
     * @return el polígono derecho del personaje
     */
    public Rectangle getPersR() {
        return PersR;
    }
    
    /**
     * Evalua el teclado de acuerdo a la tecla pulsada
     *
     * @param delta tiempo que pasa en milisegundos
     * @param entrada tipo Input para la gestión del teclado
     * @param escenas ArrayList de Escena para averiguar en qé escena estamos
     */
    public void controlDeTeclado(int delta,Input entrada,ArrayList<Escena> escenas){
        if(entrada.isKeyDown(Input.KEY_LEFT) && !escenas.get(this.getEscenario()).colisionConObjetos(this.getPersL())){
            this.getPunto().setX(this.getPunto().getX()-(this.getVelocidad() * (float)delta/1000));
            this.actualizarPosicion();
        }
        if(entrada.isKeyDown(Input.KEY_RIGHT) && !escenas.get(this.getEscenario()).colisionConObjetos(this.getPersR())){
            this.getPunto().setX(this.getPunto().getX()+(this.getVelocidad() * (float)delta/1000));
            this.actualizarPosicion();
        }
        if(entrada.isKeyDown(Input.KEY_UP) && !escenas.get(this.getEscenario()).colisionConObjetos(this.getPersUp())){
           this.getPunto().setY(this.getPunto().getY()-(this.getVelocidad() * (float)delta/1000));
           this.actualizarPosicion();
        }
        if(entrada.isKeyDown(Input.KEY_DOWN) && !escenas.get(this.getEscenario()).colisionConObjetos(this.getPersDown())){
           this.getPunto().setY(this.getPunto().getY()+(this.getVelocidad() * (float)delta/1000));
           this.actualizarPosicion();
        }
    }
    
    /**
     * Actualiza la posición de los polígonos de acuerdo a la posición del personaje
     *
     */
    public void actualizarPosicion(){
        this.getPersL().setX(this.getPunto().getX());
        this.getPersR().setX((this.getPunto().getX()+16));
        this.getPersUp().setX(this.getPunto().getX()+2);
        this.getPersDown().setX((this.getPunto().getX()+2));
        this.getPersL().setY((this.getPunto().getY()+2));
        this.getPersR().setY((this.getPunto().getY()+2));
        this.getPersUp().setY((this.getPunto().getY()));
        this.getPersDown().setY((this.getPunto().getY()+16));
    }
    
    /**
     * Comprueba si el personaje puede cambiar de escena o de nivel
     *
     * @param escenas ArrayList de Escena para averiguar en qé escena estamos
     * @param datos tipo DatosNivel que contiene la información de ese nivel
     */
    public void comprobarLimite(ArrayList<Escena> escenas,DatosNivel datos){
        if((escenas.get(this.getEscenario()).colisionEntrada(this.getPersL())) || (escenas.get(this.getEscenario()).colisionEntrada(this.getPersR())) || (escenas.get(this.getEscenario()).colisionEntrada(this.getPersUp())) || (escenas.get(this.getEscenario()).colisionEntrada(this.getPersDown()))){ //si colisiona con un poligono que indica entrar a otro escenario
            Punto point = this.guardarPosEntrada(escenas);
            this.avanzarEscenario();  //avanzamos a la siguiente escena
            datos.setEntradas((this.getEscenario()-1),point);
            this.setPunto(datos.getSalidas((this.getEscenario()-1)));  //establecemos la posición del personaje en esa nueva escena
            this.actualizarPosicion();  //y actualizamos todo
        }else if((escenas.get(this.getEscenario()).colisionSalida(this.getPersR())) || (escenas.get(this.getEscenario()).colisionSalida(this.getPersL())) || (escenas.get(this.getEscenario()).colisionSalida(this.getPersUp())) || (escenas.get(this.getEscenario()).colisionSalida(this.getPersDown()))){ //si colisiona con un poligono que indica retroceder a otro escenario
            Punto point = this.guardarPosSalida(escenas);
            this.retrocederEscenario(); //retroceder a la anterior escena
            datos.setSalidas(this.getEscenario(),point);
            this.setPunto(datos.getEntradas(this.getEscenario())); //establecemos la posición del personaje en esa nueva escena
            this.actualizarPosicion(); //y actualizamos todo
        }
    }   
    
    /**
     * Guarda la posición del personaje en la escena antes de cambiar a la siguiente
     *
     * @param escenas ArrayList de Escena para averiguar en qué escena estamos
     * @return devuelve el punto donde se quedó antes de cambiar de escena
     */
    public Punto guardarPosEntrada(ArrayList<Escena> escenas){
        Punto point;
        if(escenas.get(this.getEscenario()).colisionEntrada(this.getPersL())){
            point = new Punto((this.getPunto().getX()+20),this.getPunto().getY()); //guardamos el punto anterior en caso de volver a esa escena
        }else if(escenas.get(this.getEscenario()).colisionEntrada(this.getPersR())){
            point = new Punto((this.getPunto().getX()-20),this.getPunto().getY()); //guardamos el punto anterior en caso de volver a esa escena
        }else if(escenas.get(this.getEscenario()).colisionEntrada(this.getPersUp())){
            point = new Punto(this.getPunto().getX(),(this.getPunto().getY()+20)); //guardamos el punto anterior en caso de volver a esa escena
        }else{
            point = new Punto(this.getPunto().getX(),(this.getPunto().getY()-20)); //guardamos el punto anterior en caso de volver a esa escena
        }
        return point;
    }
    
    /**
     * Guarda la posición del personaje en la escena antes de cambiar a la anterior
     *
     * @param escenas ArrayList de Escena para averiguar en qué escena estamos
     * @return devuelve el punto donde se quedó antes de cambiar de escena
     */
    public Punto guardarPosSalida(ArrayList<Escena> escenas){
        Punto point;
        if(escenas.get(this.getEscenario()).colisionSalida(this.getPersL())){
            point = new Punto((this.getPunto().getX()+20),this.getPunto().getY()); //guardamos el punto anterior en caso de volver a esa escena
        }else if(escenas.get(this.getEscenario()).colisionSalida(this.getPersR())){
            point = new Punto((this.getPunto().getX()-20),this.getPunto().getY()); //guardamos el punto anterior en caso de volver a esa escena
        }else if(escenas.get(this.getEscenario()).colisionSalida(this.getPersUp())){
            point = new Punto(this.getPunto().getX(),(this.getPunto().getY()+20)); //guardamos el punto anterior en caso de volver a esa escena
        }else{
            point = new Punto(this.getPunto().getX(),(this.getPunto().getY()-20)); //guardamos el punto anterior en caso de volver a esa escena
        }
        return point;
    }
    
    /**
     * Comprueba si colisiona con el último polígono 
     *
     * @param escenas obtiene el último polígono adquirido en el escenario
     * @return devuelve un booleano indicando si colisiona o no
     */
    public boolean comprobarUltimoPoligono(ArrayList<Escena> escenas){
        return ((escenas.get(this.getEscenario()).colisionEntrada(this.getPersL())) || (escenas.get(this.getEscenario()).colisionEntrada(this.getPersR())) || (escenas.get(this.getEscenario()).colisionEntrada(this.getPersUp())) || (escenas.get(this.getEscenario()).colisionEntrada(this.getPersDown())));       
    }
    
    /**
     * Avanza al siguiente estado del juego (siguiente nivel)
     *
     * @param game necesario para acceder al siguiente nivel
     * @param numEscenarios indicador para verificar que se encuentra en el último escenario
     * @param escenas obtiene el último polígono adquirido en el escenario
     */
    public void siguienteNivel(StateBasedGame game,int numEscenarios,ArrayList<Escena> escenas){
        if((this.getEscenario()==numEscenarios) && this.comprobarUltimoPoligono(escenas)){
            this.avanzarMapa();
            game.enterState(this.getNivelMapa());
        }
    }
}
