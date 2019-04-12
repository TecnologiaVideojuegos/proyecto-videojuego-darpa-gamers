/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sonidos;

import org.newdawn.slick.*;

/**
 *
 * @author Senapi Aroal
 */
public class JuegoSonido extends BasicGame{

    private float px,py; //posicion de nuestro cuerpo
    private float ox,oy; //posicion de origen del cuerpo
    private float dx,dy; //posicion destino del cuerpo
    private float vx,vy; //velocidad de movimiento del cuerpo
    private Semaforo estado;
    private int dc; //contador;
    public static final float T = 0.5f;
    private Sound disparo;
    private Sound disparo1;
    private Music musica;
    
    public JuegoSonido() {
        super("Prueba sonido");
        
    }

    @Override
    public void init(GameContainer container) throws SlickException {
          disparo = new Sound("sonidos/iceball.wav");
          disparo1 = new Sound("sonidos/flaunch.wav");
          musica = new Music("musica/Flags.ogg");
          px = py = 100;
          estado = Semaforo.DETENIDO;
          musica.loop();
    }
    
    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        g.fillRect(px, py, 5, 5);
    }
     @Override
    public void mouseReleased(int button, int x, int y) {
       if(button != 0)return;
       
       dx = (float) x;
       dy = (float) y;
       ox = px;
       oy = py;
       
       vx = (dx-ox) / T;
       vy = (dy-oy) / T;
       
       dc = 0;
       estado = Semaforo.MOVIMIENTO;
    }
    
    @Override
    public void update(GameContainer container, int delta) throws SlickException {
         if(estado == Semaforo.MOVIMIENTO){
           px += vx * (float) delta / 1000;
           py += vy * (float) delta / 1000;
           dc += delta;
           
           if(dc > T*1000){
               estado = Semaforo.DETENIDO;
               px = dx;
               py = dy;
           }
       }
        if(container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
            disparo.play();
        }
        if(container.getInput().isMousePressed(Input.MOUSE_RIGHT_BUTTON)){
            disparo1.play();
        }
    }
    enum Semaforo{
    DETENIDO,
    MOVIMIENTO
}

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            AppGameContainer app = new AppGameContainer(new JuegoSonido());
            app.setDisplayMode(600,330,false);
            app.setShowFPS(false);
            app.start();
        }catch(SlickException slick){
            slick.printStackTrace();
        }
    }
}
