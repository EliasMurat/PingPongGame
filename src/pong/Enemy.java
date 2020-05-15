package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy {

    //vars tamanho
    public int w, h;

    //double para ficar mais real (posição do enemy)
    public double x, y;

    //metodo construtor
    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        this.w = 30;
        this.h = 3;
    }

    //cuida da logica
    public void tick() {
        x += (Game.ball.x - x - 5) * 0.085;
        
        if (x + w > Game.WIDTH) {
            x = Game.WIDTH - w;
        } else if (x < 0) {
            x = 0;
        }
    }

    //renderixa os gráficos
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int) x, (int) y, w, h);

    }

}
