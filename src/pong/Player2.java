package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Player2 {
    
    //vars eixos
    public int x,y;
    
    //vars coodenadas
    public int w,h;
    
    //meu teste / speed -> para o player movimentar mais rapido
    public double speed;
    
    public Player2(int x, int y){
        this.x = x;
        this.y = y;
        this.w = 30;
        this.h = 3;
        this.speed = 1.2;
    }
    
    //vars direcao
    public boolean right,left;
    
    //cuida da logica
    public void tick(){
        //verifica a direcao e anda
        if (right) {
            //desloca o player para a direita(eu add uma speed para movimentar mais rapido)
            x += speed;
        }else if(left){
            //desloca o player para a esquerda(eu add uma speed para movimentar mais rapido)
            x -= speed;
        }
        
        if (x+w > Game.WIDTH){
            x = Game.WIDTH - w;
        }else if(x < 0){
            x = 0;
        }
    }
    //renderixa os gráficos
    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect(x, y, w, h);
    }
    
}

