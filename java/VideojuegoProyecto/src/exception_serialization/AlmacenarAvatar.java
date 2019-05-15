/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception_serialization;

import java.io.*;
import java.util.*;

/**
 *
 * @author Senapi Aroal
 */
public class AlmacenarAvatar{
    
    private HashMap<String,Info_Jugador> jugadores;
    
    public AlmacenarAvatar(){
        this.jugadores = new HashMap<>();
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
        try
        {
        ObjectInputStream subida = new ObjectInputStream(new FileInputStream("./level_saves/level"+nivel+".dat"));
        
            while (true){
             jugadores = (HashMap)subida.readObject();
            }
        }catch(IOException io){
        }catch(ClassNotFoundException c){
        }
        return jugadores;  
    }
    
}
