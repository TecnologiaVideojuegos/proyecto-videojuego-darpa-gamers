/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parte_logica;

/**
 *
 * @author Senapi Aroal
 */
public class Inventario {
    
    private int tamanio;
    private Objeto[] inventario;

    /**
     * Constructor de la clase Inventario
     * 
     * @param tamanio establece el tamanio del array de objetos
     *
     */
    public Inventario(int tamanio) {
        this.tamanio = tamanio;
        this.inventario = new Objeto[tamanio];
    }

    /**
     * Get the value of inventario
     *
     * @return the value of inventario
     */
    public Objeto[] getInventario() {
        return inventario;
    }

    /**
     * Get the value of tamanio
     *
     * @return the value of tamanio
     */
    public int getTamanio() {
        return tamanio;
    }

    /**
     * muestra el inventario a nivel lógico
     *
     *
     */
    public void mostrarInventario(){
        for(int i = 0;i<tamanio;i++){
            System.out.println(inventario[i]); //caso logico
        }
    }
    
    /**
     * mira el objeto que hay en la posición indicada
     *
     * @param index posición de la casilla
     * 
     * @return el objeto en esa poisición
     */
    public Objeto mirarCasilla(int index){
        return inventario[index];
    }
    
    /**
     * recoge el elemento encontrado
     *
     * @param obj objeto a meter
     * 
     * @return  si ha sido recogido o no
     */
    public boolean recogerElemento(Objeto obj){
        int i = 0;
        boolean recogido = false;
        while(i!=this.getTamanio() && !recogido){
            if(mirarCasilla(i)==null){
                inventario[i] = obj;
                recogido = true;
            }
            i++;
        }
        return recogido;
    }
    
    /**
     * tira el elemento seleccionado
     *
     * @param obj objeto a tirar
     * 
     * @return  si ha sido tirado o no
     */
    public boolean tirarElemento(Objeto obj){
        int i = 0;
        boolean tirado = false;
        while(i!=this.getTamanio() && !tirado){
            if(mirarCasilla(i)!=null && mirarCasilla(i).equals(obj)){
                inventario[i] = null;
                tirado = true;
            }
            i++;
        }
        return tirado;
    }
}
