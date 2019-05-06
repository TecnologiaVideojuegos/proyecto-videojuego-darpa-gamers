/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package characters;

import data_level.DatosNivel;
import graphic.Hud;
import java.util.ArrayList;
import materials.Inventario;
import location.Punto;
import map.Escena;
import materials.Arco;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Senapi Aroal
 */
public class Jugador extends Ente{
    
    //Variables que representan la hitbox del personaje
    private final Rectangle PersUp,PersDown,PersL,PersR;
    
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
    
    //Variable que guarda el hud del jugador
    private final Hud hud;
    
    //Arco del jugador
    private final Arco arco;
    
    /**
     * Constructor de la clase Jugador
     * 
     * @param hp vida del ente
     * @param punto lugar del mapa donde se posiciona
     * @param sprite imagen del ente
     * @param velocidad velocidad a la que se mueve el ente
     * @param direccion dirección en la que se queda mirando
     * @param municion cantidad de proyectiles a disparar
     * @throws org.newdawn.slick.SlickException posibles exceptiones por la carga de la imagen
     * 
     */    
    public Jugador(int hp, Punto punto, SpriteSheet sprite, float velocidad,int direccion,int municion) throws SlickException{
        super(hp, punto, sprite, velocidad,direccion);
        this.experiencia = 0; //inicializado
        this.nivelJugador = 1; //inicializado
        this.nivelMapa = 1; //inicializado
        this.escenario = 0; //inicializado
        this.img = sprite;
        this.PersUp = new Rectangle((this.getPunto().getX()+2),this.getPunto().getY(),12,1);
        this.PersDown = new Rectangle((this.getPunto().getX()+2),(this.getPunto().getY()+16),12,1);
        this.PersL = new Rectangle(this.getPunto().getX(),(this.getPunto().getY()+2),1,12);
        this.PersR = new Rectangle((this.getPunto().getX()+16),(this.getPunto().getY()+2),1,12);
        this.hud = new Hud();
        this.arco = new Arco(municion);
        
    }

    /**
     * Get the value of arco
     * 
     * @return the value of arco 
     */
    public Arco getArco(){
        return arco;
    }
    
    /**
     * Get the value of hud
     *
     * @return the value of hud
     */
    public Hud getHud() {
        return hud;
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
            this.setDireccion(1);
        }
        if(entrada.isKeyDown(Input.KEY_RIGHT) && !escenas.get(this.getEscenario()).colisionConObjetos(this.getPersR())){
            this.getPunto().setX(this.getPunto().getX()+(this.getVelocidad() * (float)delta/1000));
            this.actualizarPosicion();
            this.setDireccion(0);
        }
        if(entrada.isKeyDown(Input.KEY_UP) && !escenas.get(this.getEscenario()).colisionConObjetos(this.getPersUp())){
           this.getPunto().setY(this.getPunto().getY()-(this.getVelocidad() * (float)delta/1000));
           this.actualizarPosicion();
           this.setDireccion(2);
        }
        if(entrada.isKeyDown(Input.KEY_DOWN) && !escenas.get(this.getEscenario()).colisionConObjetos(this.getPersDown())){
           this.getPunto().setY(this.getPunto().getY()+(this.getVelocidad() * (float)delta/1000));
           this.actualizarPosicion();
           this.setDireccion(3);
        }
    }
    
    /**
     * Método que gestiona los disparos
     * 
     * @param entrada dirección disparo
     * @param container obtener tamaños de la ventana
     * @param escena escena actual
     * @param delta tiempo transcurrido
     */
    public void controlDeProyectil(Input entrada,GameContainer container,Escena escena,int delta){
        this.getArco().actualizarProyectil(container,escena, delta);
        if(entrada.isKeyPressed(Input.KEY_SPACE) && this.getArco().getMunicion()!= 0){
            this.getArco().dispararFlecha(entrada,this);
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
     * 
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
     * 
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
     * Avanza al siguiente estado del juego (siguiente nivel)
     *
     * @param game necesario para acceder al siguiente nivel
     * @param numEscenarios indicador para verificar que se encuentra en el último escenario
     * @param escenas obtiene el último polígono adquirido en el escenario
     * @param datos
     */
    public void gestorCambiosMapas(StateBasedGame game,int numEscenarios,ArrayList<Escena> escenas,DatosNivel datos){
        if((this.getEscenario()==(numEscenarios-1)) && this.comprobarUltimoPoligono(escenas)){
            System.out.println("FINAL DEL NIVEL");
            this.getHud().anadirCorazon();
            ///this.avanzarMapa();
            //game.enterState(this.getNivelMapa());
        }else{
            this.comprobarLimite(escenas, datos);
        }
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
     * Método que indica si se colisiona con un monstruo o no
     * 
     * @param mon monstruos que se encuentran en esa escena
     */
    public void colisionMonstruo(ArrayList<Monstruo> mon){
        this.destruirEnemigo(mon);
        for(int i = 0;i<mon.size();i++){
            if(this.getPersDown().intersects(mon.get(i).getPersUp())){
                this.setPunto(new Punto(this.getPunto().getX(),this.getPunto().getY()-4));
                this.setHp(this.getHp()-mon.get(i).getDanyo());
                for(int a = 0;a<mon.get(i).getDanyo();a+=25){
                    this.getHud().quitarVida();
                }
            }else if(this.getPersUp().intersects(mon.get(i).getPersDown())){
                this.setPunto(new Punto(this.getPunto().getX(),this.getPunto().getY()+4));
                this.setHp(this.getHp()-mon.get(i).getDanyo());
                for(int a = 0;a<mon.get(i).getDanyo();a+=25){
                    this.getHud().quitarVida();
                }
            }else if(this.getPersL().intersects(mon.get(i).getPersR())){
                this.setPunto(new Punto(this.getPunto().getX()+4,this.getPunto().getY()));
                this.setHp(this.getHp()-mon.get(i).getDanyo());
                for(int a = 0;a<mon.get(i).getDanyo();a+=25){
                    this.getHud().quitarVida();
                }
            }else if(this.getPersR().intersects(mon.get(i).getPersL())){
                this.setPunto(new Punto(this.getPunto().getX()-4,this.getPunto().getY()));
                this.setHp(this.getHp()-mon.get(i).getDanyo());
                for(int a = 0;a<mon.get(i).getDanyo();a+=25){
                    this.getHud().quitarVida();
                }
            }
            this.actualizarPosicion();
        }
    }
    
    /**
     * Si el jugador muere la partida acaba
     * 
     * @param game cambiar entre estados
     */
    public void finPartida(StateBasedGame game){
        if(this.getHp()<=0){
            game.enterState(2);
        }
    }
    
    /**
     * Método que elimina los enemigos en caso de ser eliminado o que le quita vida
     * 
     * @param mon obtener los enemigos que hay en la escena actual
     */
    public void destruirEnemigo(ArrayList<Monstruo> mon){
        for(int i = 0;i<mon.size();i++){
            for(int j = 0;j<this.getArco().getFlecha().getColisiones().size();j++){
                if(this.getArco().getFlecha().getColisiones().get(j).intersects(mon.get(i).getPersDown()) || this.getArco().getFlecha().getColisiones().get(j).intersects(mon.get(i).getPersUp()) || this.getArco().getFlecha().getColisiones().get(j).intersects(mon.get(i).getPersL()) || this.getArco().getFlecha().getColisiones().get(j).intersects(mon.get(i).getPersR())){
                    this.getArco().getFlecha().getColisiones().remove(j);
                    this.getArco().getFlecha().getFlechas().remove(j);
                    int damage = this.getNivelJugador()*50; //daño que hace la flecha
                    mon.get(i).setHp(mon.get(i).getHp()-damage);
                }           
                if(mon.get(i).getHp()<=0){
                    mon.remove(i);
                    break;
                }
            }
        }
    }
    
    public void corregirBug(ArrayList<Escena> escena,DatosNivel datos){
        if(!escena.get(this.getEscenario()).getMapa_colision().contains(this.getPersUp()) && !escena.get(this.getEscenario()).getMapa_colision().contains(this.getPersDown()) && !escena.get(this.getEscenario()).getMapa_colision().contains(this.getPersL()) && !escena.get(this.getEscenario()).getMapa_colision().contains(this.getPersR()) ){
            this.setPunto(new Punto(respawn().getX(),respawn().getY()));
            this.actualizarPosicion();
        }else{
            for(int i = 0;i<escena.get(this.getEscenario()).getMapa_objetos().size();i++){
                if(escena.get(this.getEscenario()).getMapa_objetos().get(i).contains(this.getPersUp()) && escena.get(this.getEscenario()).getMapa_objetos().get(i).contains(this.getPersDown()) && escena.get(this.getEscenario()).getMapa_objetos().get(i).contains(this.getPersL()) && escena.get(this.getEscenario()).getMapa_objetos().get(i).contains(this.getPersR()) ){
                    this.setPunto(new Punto(respawn().getX(),respawn().getY()));
                    this.actualizarPosicion();
                }
            }
        }
    }
    
    public Punto[] nivelesRespawn(){
        Punto[][] niveles = {{new Punto(230,200),new Punto(233,292),new Punto(230,295)}};
        return niveles[this.getNivelMapa()-1];
    }
    
    public Punto respawn(){
        Punto[] escenarios = nivelesRespawn();
        return escenarios[this.getEscenario()];
    }
    
    /**
     * Método general que engloba todo lo necesario para gestionar la persona en el juego
     * 
     * @param container controlar el proyectil
     * @param game moverse entre niveles o menús
     * @param numEscenas cuantas escenas hay
     * @param delta tiempo que transcurre
     * @param entrada gestión del teclado
     * @param datos datos a obtener
     * @param escena escena en el que nos encontramos
     */
    public void gestionarJugador(GameContainer container,StateBasedGame game,int numEscenas,int delta,Input entrada,DatosNivel datos,ArrayList<Escena> escena){
        this.finPartida(game);
        this.corregirBug(escena, datos);
        this.gestorCambiosMapas(game, numEscenas, escena, datos);
        this.colisionMonstruo(escena.get(this.getEscenario()).getEnemigos());
        this.controlDeTeclado(delta, entrada, escena);
        this.controlDeProyectil(entrada, container,escena.get(this.getEscenario()), delta);
    }
}