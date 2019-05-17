/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package materials;
import java.util.ArrayList;
import location.Punto;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Senapi Aroal
 * @author Davidcawork
 */
public class Inventario {
    
    private final int tamanio_slot;
    private boolean help_state;
    private ArrayList<Objeto> [] inventario;
    private final Image[] listaSlots;
    private final Image[] listaNumObj;
    private final Image[] listaPociones;
    private final Image[] listaImagenesInfoPoc;

    /**
     * Constructor de la clase Inventario
     * 
     * @param tamanio establece el tamanio de los slots del inventario
     * @throws org.newdawn.slick.SlickException
     *
     */
    public Inventario(int tamanio) throws SlickException {
        this.tamanio_slot = tamanio;
        this.inventario = new ArrayList[5];
        
        for(int i = 0; i < 5;i++){
            this.inventario[i] = new ArrayList<Objeto>();
        }
        
        help_state = false;
        listaSlots = new Image[5];
        listaNumObj = new Image[9];
        listaPociones = new Image[5];
        listaImagenesInfoPoc = new Image[1];
        
        cargar_imagenes();
        imprimir_Inventario_init();
    }
    
    /**
     * 
     * @return true si se debe mostrar la imagen ayuda pociones
     */
    public boolean getHelpState(){
        return this.help_state;
    }
    
    /**
     * 
     * @param x boolean value
     */
    public void setHelpState(boolean x){
        this.help_state = x;
    }
    
    /**
     * 
     * Metodo para imprimir la imagen de ayuda sobre pociones
     */
    public void imprimirImagenInfoPociones(){
        if(this.getHelpState()){
            //this.dibujarElem(this.listaImagenesInfoPoc[0], (new Punto(100,100)));
            this.listaImagenesInfoPoc[0].draw(265.0f,100.0f);
        }
    }
    /**
     * 
     * @return lista de imagenes de info del inventario
     */
    public Image [] getImagenInvPocionesInfo(){
        return listaImagenesInfoPoc;
    }
    
    /**
     * Get the value of inventario
     *
     * @return the value of inventario
     */
    public ArrayList<Objeto>[] getInventario() {
        return inventario;
    }

    /**
     * Set the value of inventario
     * 
     * @param inventario 
     */
    public void setInventario(ArrayList<Objeto>[] inventario) {
        this.inventario = inventario;
    }
    
    /**
     * Get the value of tamanio_slot
     *
     * @return the value of tamanio_slot
     */
    public int getTamanioSlot() {
        return tamanio_slot;
    }
    
    public final void cargar_imagenes() throws SlickException{
        
        //Guardamos en un array de Obj tipo Image los slots del inventario
        for(int i = 0; i < 5; i++){
            listaSlots[i] = (new Image("./res/grafico/inventario/sprite_inventario_4_" + (i+1) +".png"));  
        }
        
        //Guardamos en un array de Obj tipo Image los números de objetos del inventario
        for(int i = 0; i < 9; i++){
            listaNumObj[i] = (new Image("./res/grafico/num_obj/num_obj_" + (i+1) +".png"));  
        }
        
        //Guardamos en un array de Obj tipo Image las pociones
        //Podríamos hacerlo en un bucle tambien cambiando de nombre a las imagenes
        //Pero así se ve más claro que es cada cosa
        listaPociones[0] = (new Image("./res/grafico/pociones/hp_2_32px.png"));
        listaPociones[1] = (new Image("./res/grafico/pociones/velo_2_32px.png"));
        listaPociones[2] = (new Image("./res/grafico/pociones/exp_2_32px.png"));
        listaPociones[3] = (new Image("./res/grafico/pociones/def_2_32px.png"));
        listaPociones[4] = (new Image("./res/grafico/pociones/fuerza_2_32px.png"));
        
        listaImagenesInfoPoc[0] = (new Image("./res/grafico/game_utils/info_poc.png"));
            
    }
    
    /**
     * Para pintar los slots del inventario
     * 
     */
    
    public final void imprimir_Inventario_init(){
        //System.out.println("Mostramos inventario");
        for(int i =0; i < 5; i++){
            this.dibujarElem(listaSlots[i],(new Punto((i*32*1.5f)+300, 650)));    
        }
    }
    
    /**
     * 
     * Metodo para hacer el render del invenatrio
     */
    public void imprimirInventario(){
        this.imprimir_Inventario_init();
        
        for(int i=0; i < 5; i++){   
            if (!this.inventario[i].isEmpty()){
                
                //Pintar pocion
                this.dibujarElem(this.listaPociones[i], (new Punto((i*32*1.5f)+300, 650)));
                
                //Pintar numero de pocion
                this.dibujarElem(this.listaNumObj[this.inventario[i].size() - 1], (new Punto((i*32*1.5f)+300, 650)));
            }
        }
    }

    /**
     * dibujar los elementos del inventario
     * 
     * @param img imagen a mostrar
     * @param punto posición donde se mostrará
     */
    public void dibujarElem(Image img,Punto punto){
        img.draw(punto.getX(),punto.getY(),1.5f);
        //img.draw(tamanio_slot, tamanio_slot, tamanio_slot);
    }
    
    /**
     * 
     * Metodo para añadir un obj al inventario segun su id: [0..4]
     * 
     * 
     * @param obj obj añadir
     * @param id id del objeto añadir
     * 
     */
    public void AddObj(Objeto obj, int id){
        if(this.inventario[id].size() < this.tamanio_slot)
            this.inventario[id].add(obj);
    }
    
    /**
     * 
     * Metodo para eliminar un obj del inventario segun su id: [0..4]
     * 
     * 
     * @param obj obj añadir
     * @param id id del objeto añadir
     * 
     */
    public void RemoveObj(int id){
        this.inventario[id].remove(this.inventario[id].size() - 1);
    }
    
    /**
     * 
     * @param id que identifica el tipo de objeto:
     * 
     *          id == 0  --> Vida
     *          id == 1  --> Velocidad
     *          id == 2  --> Experiencia
     *          id == 3  --> Defensa
     *          id == 4  --> Fuerza
     * 
     * @return true si quedan pociones de ese tipo, false en caso contrario 
     */
    public boolean QuedanObj(int id){
        
        try {
            return !(this.inventario[id].isEmpty());
            
        } catch (Exception e) {
            System.out.println("Id invalido");
            return false;
        }           
    }
    
}
