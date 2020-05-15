package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.JOptionPane;

public class Ball {

    public boolean pronto;
    
    //vars tamanho
    public int w, h;

    //double para ficar mais real (posição do enemy)
    public double x, y;

    public double dx, dy;
    public double speed = 1.8;

    //metodo construtor
    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        this.w = 4;
        this.h = 4;
    
        int angle = new Random().nextInt(150 - 30) + 30 + 1;
        dx = Math.cos(Math.toRadians(angle));
        dy = Math.sin(Math.toRadians(angle));
    
    }

    //cuida da logica
    public void tick() {
        
        if (x+(dx * speed) + w >= Game.WIDTH) {
            dx*=-1;
        }else if(x+(dx*speed) < 0){
            dx*=-1;
        }
        
        if (y >= Game.HEIGHT) {
            //Ponto do inimigo / playerD
            System.out.println("Red wins");
            new Game();
            return;
        }else if(y < 0){
            //Ponto do jogador
            System.out.println("blue wins");
            new Game();
            return;
        }
        
        Rectangle bounds = new Rectangle((int)(x+(dx*speed)),(int)(y+(dy*speed)),w,h);
        
        Rectangle boundsPlayer = new Rectangle(Game.player.x,Game.player.y,Game.player.w,Game.player.h);
        //Rectangle boundsPlayerD = new Rectangle(Game.playerD.x,Game.playerD.y,Game.playerD.w,Game.playerD.h);
        Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x,(int)Game.enemy.y,Game.enemy.w,Game.enemy.h);
        
        if (bounds.intersects(boundsPlayer)) {
        int angle = new Random().nextInt(150 - 30) + 30 + 1;
            dx = Math.cos(Math.toRadians(angle));
            dy = Math.sin(Math.toRadians(angle));
            if (dy > 0) {
                dy*=-1;   
            }
        }
        /*
        else if(bounds.intersects(boundsPlayerD)){
            int angle = new Random().nextInt(150 - 30) + 30 + 1;
            dx = Math.cos(Math.toRadians(angle));
            dy = Math.sin(Math.toRadians(angle));
            if (dy < 0) {
                dy*=-1;   
            }
        }
        */
        
        else if(bounds.intersects(boundsEnemy)){
            int angle = new Random().nextInt(150 - 30) + 30 + 1;
            dx = Math.cos(Math.toRadians(angle));
            dy = Math.sin(Math.toRadians(angle));
            if (dy < 0) {
                dy*=-1;   
            }
        }
        
        
        x += dx * speed;
        y += dy * speed;
        
    }

    //renderixa os gráficos
    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect((int) x, (int) y, w, h);

    }

}
