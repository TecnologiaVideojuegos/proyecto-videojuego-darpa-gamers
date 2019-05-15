/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception_serialization;

/**
 *
 * @author Senapi Aroal
 */
public class JuegoException extends Exception{
    
     /**
     * No se ha seleccionado la dificultad
     */
    public static final String DIFICULTAD_NO_SELECCIONADA = "No se ha seleccionado ninguna dificultad.";
    
    /**
     * Nombre ya introducido
     */
    public static final String NOMBRE_YA_REGISTRADO = "El nombre introducido ya está registrado.";
    
    /**
     * Nombre en blanco
     */
    public static final String NOMBRE_EN_BLANCO = "No se ha especificado un nombre para el jugador.";
    
    /**
     * Nivel no disponible
     */
    public static final String NIVEL_NO_DISPONIBLE = "No se puede jugar al nivel seleccionado.";
    
    /**
     * La id de la poción es errónea
     */
    public static final String ID_POCION_NI_VALIDA = "La id de la poción no es válida";
    
    public JuegoException(){
        super("Se ha producido una excepción en e juego.");
    }
    
    public JuegoException(String txt){
        super(txt);
    }
    
}
