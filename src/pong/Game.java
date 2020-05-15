package pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Game extends Canvas implements Runnable, KeyListener {

    //vars para configurar a dimenções da tela
    //Largura
    public static int WIDTH = 180;
    //Altura
    public static int HEIGHT = 150;
    //escala
    public static int SCALE = 3;

    //Como o JFrame não é feito para renderizar as coisa
    //Cria/Instancia :
    public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

    //Declara a var dos player
    //static pode acessar atraves de outro obj/class
    public static Player player;
    public static Enemy enemy;
    public static Ball ball;
    public static Player2 playerD;

    //Metodo construtor Game
    public Game() {
        //configura o tamanho com as vars
        this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        //para controlar o player
        this.addKeyListener(this);
        //Instancia player / fala na onde começa
        player = new Player(100, HEIGHT - 3);
        enemy = new Enemy(100, 0);
        ball = new Ball(100, HEIGHT/2);
        playerD =  new Player2(100, 0);
        
    }
    
    public static void main(String[] args) {
        //Instancia o jogo
        Game game = new Game();
        //Instancia um novo frame(Janela) + Titulo
        JFrame frame = new JFrame("Pong");
        //nao deixa o user redimensionar a janela
        frame.setResizable(false);
        //Ação padrao para fechar o jogo
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        //add componentes
        frame.add(game);
        //add pack do canvas
        frame.pack();
        //janela no centro da tela
        frame.setLocationRelativeTo(null);
        //janela bisivel
        frame.setVisible(true);

        //Thread é rodar duas ou mais funções ao mesmo tempo sem dar erro
        new Thread(game).start();
    }

    //cuida da logica
    public void tick() {
        player.tick();
        enemy.tick();
        ball.tick();
        playerD.tick();
    }

    //renderixa os gráficos
    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = layer.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        //renderiza o player 1
        player.render(g);

        //renderiza o player 2
        //playerD.render(g);
        
        //renderixa o enemy
        enemy.render(g);
        
        //renderiza a bola
        ball.render(g);
        
        g = bs.getDrawGraphics();
        //desenha layer
        g.drawImage(layer, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);

        //renderixa o jogador
        bs.show();
    }
    
    //Game looping
    @Override
    public void run() {
        //Pergunta se quer jogar com o bot ou amigo
        JOptionPane.showMessageDialog(null, "Você está preparado?","Bem-Vindo(a)",JOptionPane.OK_OPTION);
        //controle de FPS (não proficional/usado quando o projeto é pequeno)
        requestFocus();
        while (true) {
            tick();
            render();
            try {
                Thread.sleep(1000 / 60);                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //metodos para controlar eventos do teclado
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    //Verifica se apertou uma tecla
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = true;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = true;
        }
        /*PLAYER - 2
        else if (e.getKeyCode() == KeyEvent.VK_D) {
            playerD.right = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_A){
            playerD.left = true;
        }
         /**/
    }

    //Verifica se soltou uma tecla
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = false;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = false;
        }
        /*PLAYER - 2
        else if (e.getKeyCode() == KeyEvent.VK_D) {
            playerD.right = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_A){
            playerD.left = false;
        }
         /**/
    }
    
}
