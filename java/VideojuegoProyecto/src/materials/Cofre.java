/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package materials;

import java.util.ArrayList;
import java.util.Random;
import location.Punto;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.*;

/**
 *
 * @author Davidcawork
 */
public final class Cofre {
    
    private Punto loc_cofre;
    private SpriteSheet sheet_cofre;
    private boolean estado_cofre;
    private ArrayList<Integer> lista_id_pociones;
    private Rectangle cofreRect;
    private Rectangle cofreRect_lootArea;
    private Sound sonido_apertura_cofre;
    private boolean estado_cerca_cofre;
    private final Image open_cofre;
    
    /**
     * 
     * Constructor de la clase cofre
     * 
     * @param x localizacion x del cofre
     * @param y localizacion y del cofre
     * @throws org.newdawn.slick.SlickException
     */
    public Cofre(int x, int y) throws SlickException{
        
        /*Cargar el sonido de apertura del cofre    */
        //this.sonido_apertura_cofre = new Sound("./res/audio/sounds/open_chest.ogg");
        this.sonido_apertura_cofre = null;
        /*Cargar el spritesheet del cofre */
        cargar_animacion_cofre();
        
        /*  Estado cofre, false == cerrado, true == abierto */
        this.estado_cofre = false;
        
        this.lista_id_pociones = new ArrayList<>();
        cargar_id_pociones_probabilidad();
        
        this.loc_cofre = new Punto(x, y);
        this.cofreRect = new Rectangle(x, y, 32.0f, 32.0f);
        this.cofreRect_lootArea = new Rectangle(x - 10, y - 10 , 62.0f, 62.0f);
        estado_cerca_cofre = false;
        open_cofre = new Image("./res/grafico/game_utils/icon_cofre_2.png");
        
    }
    
    /**
     * 
     * Metodo que carga el srpite sheet del cofre
     * 
     * @throws SlickException 
     */
    public void cargar_animacion_cofre() throws SlickException{
        this.sheet_cofre = new SpriteSheet("./res/grafico/game_utils/chest2.png",32,32);
    }
    
    /**
     * 
     * Según la definicion de Laplace de probabilidad 
     *   
     *       Suponemos que todos los posibles numeros aleatorios generados por el obj random 
     *       son equiprobables, entonces con nuestra condicion del if estamos consiguiendo una prob tal 
     *       que sería el numero de casos favorables entre todos los posibles resultados del experimento.
     *   
     *       En el primer caso tendríamos 1/12 de probabilidad(8.33%). Escogemos un posible valor
     *       en nuestros caso el 0(nos genera numeros de 0 - 11) entre todos los posibles valores generados.
     *   
     *       La probabilidad de cumplir esa condicion es de un 8.33%
     *   
     *       Id_pociones: 0 == hp
     *                    1 == velo
     *                    2 == exp
     *                    3 == Invulnerabilidad
     *                    4 == Fuerza
     */
    public void cargar_id_pociones_probabilidad(){
        
        //Invulnerabilidad
        if(0 == new Random().nextInt(12)){
            this.lista_id_pociones.add(3);
        
        //Fuerza
        } else if(0 == new Random().nextInt(6)){
            this.lista_id_pociones.add(4);
        
        //Velo
        }else if(0 == new Random().nextInt(3)){
            this.lista_id_pociones.add(1);
        
        //Hp
        }else if(0 == new Random().nextInt(2)){
            this.lista_id_pociones.add(0);
        }
        
        //Exp siempre vamos a dar por abrir un cofre, lo que será aleatorio será el numero de esxp que vamos a dar
        // Vamos a dar de 1 - 3 botellas de exp
        int exp = (new Random().nextInt(3) +1);
        for(int i = 0; i <exp; i++){
            this.lista_id_pociones.add(2);
        }
        
    }
    
    
    /**
     * 
     * Metodo que dibuja el cofre en funcion del atributo punto y el estado del mismo
     * 
     */
    public void imprimir_cofre(){
    
        if(!this.getEstadoCofre()){
            this.sheet_cofre.getSprite(0, 0).draw(this.getPuntoCofre().getX(), this.getPuntoCofre().getY());
        }else{
            this.sheet_cofre.getSprite(1, 0).draw(this.getPuntoCofre().getX(), this.getPuntoCofre().getY());
        }
            
    }
    
    /**
     * 
     * @return El estado de cofre 
     */
    public boolean getEstadoCofre(){
        return this.estado_cofre;
    }
    
    /**
     * 
     * @param state Estado a poner al estado del cofre
     */
    public void setEstadoCofre(boolean state){
        this.estado_cofre = state;
    }
    
    /**
     * 
     * @return La lista con los ids del loot del cofre
     */
    public ArrayList<Integer> getListaIdsCofre(){
        return this.lista_id_pociones;
    }
    
    /**
     * 
     * @param aux lista de ids de pociones como loot al cofre
     */
    public void setListaIdsCofre( ArrayList<Integer> aux){
        this.lista_id_pociones = aux;
    }
    
    /**
     * 
     * @return get el atributo punto del cofre 
     */
    public Punto getPuntoCofre(){
        return this.loc_cofre;
    }
    
    /**
     * 
     * @param aux Punto a asignar al atributo punto
     */
    public void setPuntoCofre(Punto aux){
        this.loc_cofre = aux;
    }
    
    /**
     * 
     * @return Devuelve el rectangulo para gestionar la colision en jugador
     */
    public Rectangle getCofreRect(){
        return this.cofreRect;
    }
    
        /**
     * 
     * @return Devuelve el rectangulo para gestionar la colision en jugador
     */
    public Rectangle getCofreRectLootArea(){
        return this.cofreRect_lootArea;
    }
    
    /**
     * 
     * @return Retorna el sonido de apertura del cofre
     */
    public Sound getSonidoCofre(){
        return this.sonido_apertura_cofre;
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
    
    
    /**
     * 
     * Metodo para imprimir al jugador si está cerca de un cofre la tecla R
     * 
     * @param punto_jugador posicion a hacer el draw
     */
    public void imprimirTeclaAbrirCofre(){
    
        if(this.estado_cerca_cofre)
            this.open_cofre.draw( this.loc_cofre.getX() + 8.0f, this.loc_cofre.getY() -20.0f);
    }
    
}