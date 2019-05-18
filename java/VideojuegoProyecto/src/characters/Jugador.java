/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package characters;

import data_level.DatosNivel;
import exception_serialization.AlmacenarAvatar;
import graphic.Hud;
import graphic.Notificaciones;
import java.util.ArrayList;
import java.util.Random;
import materials.Inventario;
import location.Punto;
import map.Escena;
import materials.*;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Senapi Aroal
 * @author Davidcawork
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
    
    //Variable que indica el nivel máximo del mapa que ha alcanzado el jugador
    private int nivelMapaMax;
    
    //Variable que indica en que escenario de un nivel se encuentra el personaje
    private int escenario;
    
    //Variable que guarda la imagen del personaje
    private final SpriteSheet img;
    
    //Variable que guarda el hud del jugador
    private final Hud hud;
    
    //Arco del jugador
    private final Varita varita;
    
    //Vida max
    private final int vida_max;
    
    //Velo base
    private final float velo_base;
    
    //Dmg base
    private final int danyo_base;
    
    //Buff personaje invulnerable
    private final Buff buff_invulnerable;
    
    //Buff personaje invulnerable
    private final Buff buff_fuerza;
    
    //Buff personaje invulnerable
    private final Buff buff_velocidad;
    
    //Notificador
    private final Notificaciones notif;
    
    //Nombre
    private String nombre;
    
    /**
     * Constructor de la clase Jugador
     * 
     * @param nombre
     * @param hp vida del ente
     * @param punto lugar del mapa donde se posiciona
     * @param sprite imagen del ente
     * @param velocidad velocidad a la que se mueve el ente
     * @param direccion dirección en la que se queda mirando
     * @param municion cantidad de proyectiles a disparar
     * @param danio daño del juagor 
     * @throws org.newdawn.slick.SlickException posibles exceptiones por la carga de la imagen
     * 
     */    
    public Jugador(String nombre,int hp, Punto punto, SpriteSheet sprite, float velocidad,int direccion,int municion,int danio,int nivelMapa) throws SlickException{
        super(hp, punto, sprite, velocidad,direccion, danio);
        this.nombre = nombre;
        this.experiencia = 0; //inicializado
        this.nivelJugador = 1; //inicializado
        this.vida_max = hp * this.nivelJugador;
        this.velo_base = velocidad;
        this.danyo_base = danio;
        this.nivelMapa = nivelMapa; //inicializado
        this.nivelMapaMax = this.nivelMapa;
        this.escenario = 0; //inicializado
        this.img = sprite;
        this.PersUp = new Rectangle((this.getPunto().getX()+2),this.getPunto().getY(),12,1);
        this.PersDown = new Rectangle((this.getPunto().getX()+2),(this.getPunto().getY()+16),12,1);
        this.PersL = new Rectangle(this.getPunto().getX(),(this.getPunto().getY()+2),1,12);
        this.PersR = new Rectangle((this.getPunto().getX()+16),(this.getPunto().getY()+2),1,12);
        this.hud = new Hud();
        this.varita = new Varita(municion);
        this.inventario = new Inventario(9); // Para que los slots del inventario vayan de 1-9
        this.buff_invulnerable = new Buff(20000);
        this.buff_fuerza = new Buff(60000);
        this.buff_velocidad = new Buff(60000);
        this.notif = new Notificaciones(3000);
        
    }

    
    
    /**
     * Get the value of NivelMapaMax
     * 
     * @return the value of nivel del mapa max
     */
    public int getNivelMapaMax() {
        return nivelMapaMax;
    }

    /**
     * Set value of NivelMapaMax
     * 
     * @param nivelMapaMax the new value of nivelMapaMax
     */
    public void setNivelMapaMax(int nivelMapaMax) {
        this.nivelMapaMax = nivelMapaMax;
    }
    
    /**
     * Get the value of nombre
     * 
     * @return the value of nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set value of nombre
     * 
     * @param nombre the new value of nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * 
     * @return devuelve el notificador
     */
    public Notificaciones getNoti(){
        return this.notif;
    }
    /**
     * 
     * @return buff inv
     */
    public Buff getBuffInv(){
        return this.buff_invulnerable;
    }
    
    /**
     * 
     * @return buff Fuerza
     */
    public Buff getBuffFuerza(){
        return this.buff_fuerza;
    }
    
    /**
     * 
     * @return buff Velo
     */
    public Buff getBuffVelo(){
        return this.buff_velocidad;
    }
    
    /**
     * Get the value of Varita
     * 
     * @return the value of Varita 
     */
    public Varita getVarita(){
        return varita;
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
     * Get the value of jugador
     *
     * @param nivel nivel
     */
    public void setNivelJugador(int nivel) {
        this.nivelJugador = nivel;
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
        if(entrada.isKeyDown(Input.KEY_LEFT) && !escenas.get(this.getEscenario()).colisionConPoligonos(this.getPersL())){
            this.getPunto().setX(this.getPunto().getX()-(this.getVelocidad() * (float)delta/1000));
            this.actualizarPosicion();
            this.setDireccion(1);
        }
        if(entrada.isKeyDown(Input.KEY_RIGHT) && !escenas.get(this.getEscenario()).colisionConPoligonos(this.getPersR())){
            this.getPunto().setX(this.getPunto().getX()+(this.getVelocidad() * (float)delta/1000));
            this.actualizarPosicion();
            this.setDireccion(0);
        }
        if(entrada.isKeyDown(Input.KEY_UP) && !escenas.get(this.getEscenario()).colisionConPoligonos(this.getPersUp())){
           this.getPunto().setY(this.getPunto().getY()-(this.getVelocidad() * (float)delta/1000));
           this.actualizarPosicion();
           this.setDireccion(2);
        }
        if(entrada.isKeyDown(Input.KEY_DOWN) && !escenas.get(this.getEscenario()).colisionConPoligonos(this.getPersDown())){
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
        this.getVarita().actualizarProyectil(container,escena, delta);
        if(entrada.isKeyPressed(Input.KEY_SPACE) && this.getVarita().getMunicion()!= 0){
            this.getVarita().dispararFlecha(entrada,this);
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
    public void gestorCambiosMapas(StateBasedGame game,int numEscenarios,ArrayList<Escena> escenas,DatosNivel datos,AlmacenarAvatar alm){
        if((this.getEscenario()==(numEscenarios-1)) && this.comprobarUltimoPoligono(escenas)){
            if(escenas.get(this.getEscenario()).getEnemigos().size()==0){
                System.out.println("FINAL DEL NIVEL");
                this.getHud().anadirCorazon();
                if(this.getNivelMapaMax() == this.getNivelMapa()){
                    this.nivelMapaMax++;
                }
                this.avanzarMapa();
                game.enterState(this.getNivelMapa());
            }
        }else{
            if(escenas.get(this.getEscenario()).getEnemigos().size()==0){
                this.comprobarLimite(escenas, datos);
            }
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
                this.setPunto(new Punto(this.getPunto().getX(),this.getPunto().getY()-32));
                if(!this.buff_invulnerable.getEstadoBuff()){
                    this.setHp(this.getHp()-mon.get(i).getDanyo());
                    for(int a = 0;a<mon.get(i).getDanyo();a+=25){
                        this.getHud().quitarVida();
                    }
                }
            }else if(this.getPersUp().intersects(mon.get(i).getPersDown())){
                this.setPunto(new Punto(this.getPunto().getX(),this.getPunto().getY()+32));
                if(!this.buff_invulnerable.getEstadoBuff()){
                    this.setHp(this.getHp()-mon.get(i).getDanyo());
                    for(int a = 0;a<mon.get(i).getDanyo();a+=25){
                        this.getHud().quitarVida();
                    }
                }
            }else if(this.getPersL().intersects(mon.get(i).getPersR())){
                this.setPunto(new Punto(this.getPunto().getX()+32,this.getPunto().getY()));
                if(!this.buff_invulnerable.getEstadoBuff()){
                    this.setHp(this.getHp()-mon.get(i).getDanyo());
                    for(int a = 0;a<mon.get(i).getDanyo();a+=25){
                        this.getHud().quitarVida();
                    }
                }
            }else if(this.getPersR().intersects(mon.get(i).getPersL())){
                this.setPunto(new Punto(this.getPunto().getX()-32,this.getPunto().getY()));
                if(!this.buff_invulnerable.getEstadoBuff()){
                    this.setHp(this.getHp()-mon.get(i).getDanyo());
                    for(int a = 0;a<mon.get(i).getDanyo();a+=25){
                        this.getHud().quitarVida();
                    }
                }
            }else if(this.getPersL().intersects(mon.get(i).getPersR()) && this.getPersUp().intersects(mon.get(i).getPersDown())){
                this.setPunto(new Punto(this.getPunto().getX()+32,this.getPunto().getY()+32));
                if(!this.buff_invulnerable.getEstadoBuff()){
                    this.setHp(this.getHp()-mon.get(i).getDanyo());
                    for(int a = 0;a<mon.get(i).getDanyo();a+=25){
                        this.getHud().quitarVida();
                    }
                }
            }else if(this.getPersL().intersects(mon.get(i).getPersR()) && this.getPersDown().intersects(mon.get(i).getPersUp())){
                this.setPunto(new Punto(this.getPunto().getX()+32,this.getPunto().getY()-32));
                if(!this.buff_invulnerable.getEstadoBuff()){
                    this.setHp(this.getHp()-mon.get(i).getDanyo());
                    for(int a = 0;a<mon.get(i).getDanyo();a+=25){
                        this.getHud().quitarVida();
                    }
                }
            }else if(this.getPersR().intersects(mon.get(i).getPersL()) && this.getPersUp().intersects(mon.get(i).getPersDown())){
                this.setPunto(new Punto(this.getPunto().getX()-32,this.getPunto().getY()+32));
                if(!this.buff_invulnerable.getEstadoBuff()){
                    this.setHp(this.getHp()-mon.get(i).getDanyo());
                    for(int a = 0;a<mon.get(i).getDanyo();a+=25){
                        this.getHud().quitarVida();
                    }
                }
            }else if(this.getPersR().intersects(mon.get(i).getPersL()) && this.getPersDown().intersects(mon.get(i).getPersUp())){
                this.setPunto(new Punto(this.getPunto().getX()-32,this.getPunto().getY()-32));
                if(!this.buff_invulnerable.getEstadoBuff()){
                    this.setHp(this.getHp()-mon.get(i).getDanyo());
                    for(int a = 0;a<mon.get(i).getDanyo();a+=25){
                        this.getHud().quitarVida();
                    }
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
            for(int j = 0;j<this.getVarita().getFlecha().getColisiones().size();j++){
                if(this.getVarita().getFlecha().getColisiones().get(j).intersects(mon.get(i).getPersDown()) || this.getVarita().getFlecha().getColisiones().get(j).intersects(mon.get(i).getPersUp()) || this.getVarita().getFlecha().getColisiones().get(j).intersects(mon.get(i).getPersL()) || this.getVarita().getFlecha().getColisiones().get(j).intersects(mon.get(i).getPersR())){
                    this.getVarita().getFlecha().getColisiones().remove(j);
                    this.getVarita().getFlecha().getFlechas().remove(j);
                    mon.get(i).setHp(mon.get(i).getHp()- super.getDanyo());
                    mon.get(i).setComportamiento("Perseguir");
                }           
                if(mon.get(i).getHp()<=0){
                    
                    //Añadir municion aleatoria y notificar
                    Random random = new Random();
                    this.varita.addMunicion(random.nextInt(50));
                    this.notif.aniadirNotificacion(this.notif.getImgNotf()[5]);
                    this.anadirExperiencia((int)Math.sqrt((double)mon.get(i).getDanyo()) + 15*this.nivelJugador);
                    
                    mon.remove(i);
                    break;
                }
            }
        }
    }
    
    @Override
    public void corregirBug(Escena escena){
        if(!escena.getMapa_colision().contains(this.getPersUp()) && !escena.getMapa_colision().contains(this.getPersDown()) && !escena.getMapa_colision().contains(this.getPersL()) && !escena.getMapa_colision().contains(this.getPersR()) ){
            this.setPunto(new Punto(respawn().getX(),respawn().getY()));
            this.actualizarPosicion();
        }else{
            for(int i = 0;i<escena.getMapa_objetos().size();i++){
                if(escena.getMapa_objetos().get(i).contains(this.getPersUp()) && escena.getMapa_objetos().get(i).contains(this.getPersDown()) && escena.getMapa_objetos().get(i).contains(this.getPersL()) && escena.getMapa_objetos().get(i).contains(this.getPersR()) ){
                    this.setPunto(new Punto(respawn().getX(),respawn().getY()));
                    this.actualizarPosicion();
                }
            }
        }
    }
    
    public Punto[] nivelesRespawn(){
        Punto[][] niveles = {{new Punto(80,655),new Punto(370,450),new Punto(940,215),new Punto(900,420)}};
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
    public void gestionarJugador(GameContainer container,StateBasedGame game,int numEscenas,int delta,Input entrada,DatosNivel datos,ArrayList<Escena> escena,AlmacenarAvatar alm){
        this.finPartida(game);
        this.corregirBug(escena.get(this.getEscenario()));
        this.gestorCambiosMapas(game, numEscenas, escena, datos,alm);
        this.colisionMonstruo(escena.get(this.getEscenario()).getEnemigos());
        this.controlDeTeclado(delta, entrada, escena);
        this.controlDeProyectil(entrada, container,escena.get(this.getEscenario()), delta);
        this.controlPocion(entrada);
        this.buff_invulnerable.controlBuff(delta);
        this.controlBuffFuerza(delta);
        this.controlBuffVelocidad(delta);
        controlMenuAyudaPociones(entrada);
        this.notif.controlNotif(delta);
        this.notif.controlEstadoEspera(delta);
        this.controlColisionCofres(escena.get(this.getEscenario()).getCofres(), entrada);
        this.controlColisionCofresLoot(escena.get(this.getEscenario()).getCofres(), entrada);
    }
    
    
    /**
     * 
     * Metodo para saber que pocion estamos tomando
     * 
     * @param entrada para saber que pocion estamos tomando
     * 
     */
    public void controlPocion(Input entrada){
    
            if(entrada.isKeyPressed(Input.KEY_1)){
                this.consumirPocion(0);
            }else if(entrada.isKeyPressed(Input.KEY_2)){
                this.consumirPocion(1);
            }else if(entrada.isKeyPressed(Input.KEY_3)){
                this.consumirPocion(2);
            }else if(entrada.isKeyPressed(Input.KEY_4)){
                this.consumirPocion(3);
            }else if(entrada.isKeyPressed(Input.KEY_5)){
                this.consumirPocion(4);
            }else if(entrada.isKeyPressed(Input.KEY_6)){
                this.aniadirPocion(0);
                this.notif.aniadirNotificacion(this.notif.getImgNotf()[0]);
                this.aniadirPocion(1);
                this.notif.aniadirNotificacion(this.notif.getImgNotf()[1]);
                this.aniadirPocion(2);
                this.notif.aniadirNotificacion(this.notif.getImgNotf()[2]);
                this.aniadirPocion(3);
                this.notif.aniadirNotificacion(this.notif.getImgNotf()[3]);
                this.aniadirPocion(4);
                this.notif.aniadirNotificacion(this.notif.getImgNotf()[4]);
            }
    }
    
    /**
     *  Método para consumir una poción del inventario según el id de poción
     * 
     *   @param id_pocion que identifica el tipo de objeto:
     * 
     *          id == 0  --> Vida
     *          id == 1  --> Velocidad
     *          id == 2  --> Experiencia
     *          id == 3  --> Defensa
     *          id == 4  --> Fuerza
     *  En el motodo tambien modificaremos las caracteristicas los atributos del jugador
     *  en función de la poción consumida
     * 
     */
    public void consumirPocion(int id_pocion){
        
        if(this.inventario.QuedanObj(id_pocion)){
            switch(id_pocion){
                //Hp
                case 0:
                    this.inventario.RemoveObj(id_pocion);
                    //Modificar hp del jugador
                    if(super.getHp() < this.vida_max){
                        super.setHp( super.getHp() + 25 );
                        this.hud.anadirVida();
                        if(super.getHp() > this.vida_max){
                          super.setHp(this.vida_max);
                        }   
                    }
                    //Sonido consumir poción
                    break;
                
                //Velo
                case 1:
                    this.inventario.RemoveObj(id_pocion);
                    //Modificar velocidad del jugador
                    super.setVelocidad( super.getVelocidad() + 5.0f);
                    this.buff_velocidad.setEstadoBuff(true);
                    this.buff_velocidad.setTimerEstadoBuff(0);
                    //Sonido consumir poción
                    break;
                
                //Exp
                case 2:
                    this.inventario.RemoveObj(id_pocion);
                    //Subir experiencia del jugador this.experiencia += 100; p.e
                    this.anadirExperiencia(200);
                    //Sonido consumir poción
                    break;
                
                //Def
                case 3:
                    this.inventario.RemoveObj(id_pocion);
                    // Estado invulnerable
                    this.buff_invulnerable.setEstadoBuff(true);
                    this.buff_invulnerable.setTimerEstadoBuff(0);
                    //Sonido consumir poción
                    break;
                
                //Fuerza
                case 4:
                    this.inventario.RemoveObj(id_pocion);
                    //Modificar el daño que hacemos con el arma Varita
                    super.setDanyo(super.getDanyo() + 25);
                    this.buff_fuerza.setEstadoBuff(true);
                    this.buff_fuerza.setTimerEstadoBuff(0);
                    //Sonido consumir poción
                    break;
            }
        }
    }
    
    
    /**
     * Metodo para añadir una poción al inventario del jugador 
     * 
     * @param id_pocion 
     */
    public void aniadirPocion(int id_pocion){
        this.inventario.AddObj((new Objeto(id_pocion,"pocion", "pocion comun")), id_pocion);
    }
    
    /**
     * 
     * 
     * @param delta tiempo en ms desde la ultima que se hizo 
     *              un update en el nivel.
     */
    public void controlBuffFuerza(int delta){
        
        if(this.buff_fuerza.getEstadoBuff()){
            this.buff_fuerza.setTimerEstadoBuff(this.buff_fuerza.getTimerEstadoBuff() + delta );
            
            // Despues de x segundos se termin el buff
            if( this.buff_fuerza.getTimerEstadoBuff() > this.buff_fuerza.getMaxTimeBuff()){
                this.buff_fuerza.setEstadoBuff(false);
                super.setDanyo(danyo_base);
                
            }
        }
    }
    
    /**
     * 
     * 
     * @param delta tiempo en ms desde la ultima que se hizo 
     *              un update en el nivel.
     */
    public void controlBuffVelocidad(int delta){
        
        if(this.buff_velocidad.getEstadoBuff()){
            this.buff_velocidad.setTimerEstadoBuff(this.buff_velocidad.getTimerEstadoBuff() + delta );
            
            // Despues de x segundos se termin el buff
            if( this.buff_velocidad.getTimerEstadoBuff() > this.buff_velocidad.getMaxTimeBuff()){
                this.buff_velocidad.setEstadoBuff(false);
                super.setVelocidad(velo_base);
            }
        }
    }
    
    /**
     * 
     * @param entrada controlar el mensaje informtivo sobre las pociones
     */
    public void controlMenuAyudaPociones(Input entrada){   
        if(entrada.isKeyDown(Input.KEY_TAB)){
                this.inventario.setHelpState(true);
        }else{
            this.inventario.setHelpState(false);
        }
    
    }
    
    public void controlColisionCofres(ArrayList<Cofre> cofres, Input entrada){
    
          for(int i =0; i < cofres.size(); i++){
              if(this.getPersDown().intersects(cofres.get(i).getCofreRect())){
                this.setPunto(new Punto(this.getPunto().getX(),this.getPunto().getY()-1));
                
                
            }else if(this.getPersUp().intersects(cofres.get(i).getCofreRect())){
                this.setPunto(new Punto(this.getPunto().getX(),this.getPunto().getY()+1));
              
                
            }else if(this.getPersL().intersects(cofres.get(i).getCofreRect())){
                this.setPunto(new Punto(this.getPunto().getX()+1,this.getPunto().getY()));
                
               
            }else if(this.getPersR().intersects(cofres.get(i).getCofreRect())){
                this.setPunto(new Punto(this.getPunto().getX()-1,this.getPunto().getY()));
                
            }
            this.actualizarPosicion();
          }  
    }
    
    
    public void controlColisionCofresLoot(ArrayList<Cofre> cofres, Input entrada){
    
          for(int i =0; i < cofres.size(); i++){
              if(this.getPersDown().intersects(cofres.get(i).getCofreRectLootArea())){
                if(entrada.isKeyDown(Input.KEY_R) && (!cofres.get(i).getEstadoCofre())){
                    //Recoger drop
                    for(int j = 0; j < cofres.get(i).getListaIdsCofre().size(); j++){
                        //Añadir pocion al inventario
                        this.aniadirPocion(cofres.get(i).getListaIdsCofre().get(j));
                        
                        //Notificar del drop
                        this.notif.aniadirNotificacion(this.notif.getImgNotf()[cofres.get(i).getListaIdsCofre().get(j)]);
                            
                    }
                    
                    //Sacar el sonido de apertura
                    //cofres.get(i).getSonidoCofre().play();
                    
                    
                    //Cambiar de estado el cofre a abierto
                    cofres.get(i).setEstadoCofre(true);
                    
                }
                
            }else if(this.getPersUp().intersects(cofres.get(i).getCofreRectLootArea())){
               
                if(entrada.isKeyDown(Input.KEY_R) && (!cofres.get(i).getEstadoCofre())){
                    //Recoger drop
                    
                    for(int j = 0; j < cofres.get(i).getListaIdsCofre().size(); j++){
                        //Añadir pocion al inventario
                        this.aniadirPocion(cofres.get(i).getListaIdsCofre().get(j));
                        
                        //Notificar del drop
                        this.notif.aniadirNotificacion(this.notif.getImgNotf()[cofres.get(i).getListaIdsCofre().get(j)]);
                            
                    }
                    //Sacar el sonido de apertura
                    //cofres.get(i).getSonidoCofre().play();
                    
                    //Cambiar de estado el cofre a abierto
                    cofres.get(i).setEstadoCofre(true);
                    
                }
                
            }else if(this.getPersL().intersects(cofres.get(i).getCofreRectLootArea())){
                if(entrada.isKeyDown(Input.KEY_R) && (!cofres.get(i).getEstadoCofre())){
                    //Recoger drop
                    
                    for(int j = 0; j < cofres.get(i).getListaIdsCofre().size(); j++){
                        //Añadir pocion al inventario
                        this.aniadirPocion(cofres.get(i).getListaIdsCofre().get(j));
                        
                        //Notificar del drop
                        this.notif.aniadirNotificacion(this.notif.getImgNotf()[cofres.get(i).getListaIdsCofre().get(j)]);
                            
                    }
                    
                    //Sacar el sonido de apertura
                    //cofres.get(i).getSonidoCofre().play();
                    
                    
                    //Cambiar de estado el cofre a abierto
                    cofres.get(i).setEstadoCofre(true);
                    
                }
               
            }else if(this.getPersR().intersects(cofres.get(i).getCofreRectLootArea())){
                
                if(entrada.isKeyDown(Input.KEY_R) && (!cofres.get(i).getEstadoCofre())){
                    //Recoger drop
                    
                    for(int j = 0; j < cofres.get(i).getListaIdsCofre().size(); j++){
                        //Añadir pocion al inventario
                        this.aniadirPocion(cofres.get(i).getListaIdsCofre().get(j));
                        
                        //Notificar del drop
                        this.notif.aniadirNotificacion(this.notif.getImgNotf()[cofres.get(i).getListaIdsCofre().get(j)]);
                            
                    }
                    
                    //Sacar el sonido de apertura
                    //cofres.get(i).getSonidoCofre().play();
                    //Mirar error de ejecución del sonido
                    
                    //Cambiar de estado el cofre a abierto
                    cofres.get(i).setEstadoCofre(true);
                }
                
            }
            this.actualizarPosicion();
          }  
    }
    
}