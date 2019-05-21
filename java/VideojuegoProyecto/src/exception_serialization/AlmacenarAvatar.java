/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception_serialization;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Senapi Aroal
 */
public class AlmacenarAvatar{
    
    private HashMap<String,Info_Jugador> jugadores;
    private ArrayList<String> nombres;
    
    public AlmacenarAvatar(){
        this.jugadores = new HashMap<>();
        this.nombres = new ArrayList<>();
    }
    
    /**
     * Añade un jugador a la lista
     * 
     * @param objug jugador a añadir
     * @return si la operación ha sido o no un éxito
     */
    public boolean altaJugador(Info_Jugador objug) {//registramos el jugador
        if (!jugadores.containsKey(objug.getNombre())) {
            jugadores.put(objug.getNombre(),objug);
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Borra un jugador de la lista
     * 
     * @param objug jugador a borrado
     * @return si la operación ha sido o no un éxito
     */
    public boolean bajaJugador(Info_Jugador objug){//borramos el jugador
        if(jugadores.containsKey(objug.getNombre())){
            jugadores.remove(objug.getNombre());
            return true;
        }else{
            return false;
        }
    }
      
    /**
     * Añadimos el nombre
     * @param jug jugador
     */
    public void meterNombre(Info_Jugador jug){//guardamos en nif de la persona registrada
        nombres.add(jug.getNombre());}
    

    /**
     * Borramos el nombre
     * @param jug jugador
     */
    public void eliminarNombre(Info_Jugador jug){//borramos el nif de la presona registrada
        nombres.remove(jug.getNombre());
        }
    
    /**
     * Método que muestra el contenido del hashmap a partir del nivel
     * 
     * @return hashmap con la información de ese nivel
     */
    public HashMap<String,Info_Jugador> getMostrar(){
        return jugadores;
    }
    
    /**
     * Método que guarda la información en el fichero especificado por el nivel
     * 
     */
    public void guardarDatos(int nivel){//guardamos los jugadores registrados en un archivo
        try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("./level_saves/level"+nivel+".dat"))){
            oos.writeObject(jugadores);
        }catch(IOException io){
        }
    }
    
    /**
     * Método que carga la información del un nivel especificado y lo incorpora al arraylist general
     * 
     * @param nivel
     * @return el arrayñist general con el hashmap cargado en su interior
     */
    public HashMap<String,Info_Jugador> cargarDatos(int nivel){//obtenemos del archivo los jugadores registrados
        ObjectInputStream subida;
        try
        {
        subida = new ObjectInputStream(new FileInputStream("./level_saves/level"+nivel+".dat"));
        
            while (true){
             jugadores = (HashMap)subida.readObject();
            }
        }catch(IOException io){
        }catch(ClassNotFoundException c){
        }
        
        return jugadores;  
    }
    
    /**
     * Método que guarda la información en el fichero especificado por el nivel
     * 
     */
    public void guardarNombre(){//guardamos los jugadores registrados en un archivo
        try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("./level_saves/nombres.dat"))){
            oos.writeObject(nombres);
        }catch(IOException io){
        }
    }
    
    /**
     * Método para cargar los nombres registrados
     * @return dni
     */
    public ArrayList cargarNombres(){  //cargamos los datos de un archivo para obtener los dni de los jugadores registrados
        try{
        ObjectInputStream subida = new ObjectInputStream(new FileInputStream("./level_saves/nombres.dat"));
        while(true){
            nombres = (ArrayList)subida.readObject();
        }
        }catch(EOFException e){
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AlmacenarAvatar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AlmacenarAvatar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlmacenarAvatar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nombres;
    }
    
    /**
     * Método que ordena los jugadores registrados por experiencia
     * @return Jug
     */
    public ArrayList<Info_Jugador> ordenarMarcador(){//metodo para ordenar los jugadores registrados segun las puntuaciones totales
        ArrayList<Info_Jugador> Jug= new ArrayList();
        for(int i=0;i<nombres.size();i++){
            Jug.add(jugadores.get(nombres.get(i)));          
        }
        Comparator PuntDeJug = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Info_Jugador j1 = (Info_Jugador) o1;
                Info_Jugador j2 = (Info_Jugador) o2;
                Integer puntos1 = j1.getExperiencia();
                Integer puntos2 = j2.getExperiencia();
                return puntos2.compareTo(puntos1);               
            }
        };
        Collections.sort(Jug,PuntDeJug);
        return Jug;  
    }
    
}
