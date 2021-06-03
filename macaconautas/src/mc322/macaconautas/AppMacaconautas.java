package mc322.macaconautas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;



public class AppMacaconautas extends Canvas implements Runnable, KeyListener {
	private boolean isRunning;
	private Thread thread;
	public static JFrame f;
	public final static int WIDTH = 160;
	public final static int HEIGHT = 120;
	public final static int SCALE = 4;
	public Macaco macaco;
	
	public Espaco espaco;
	
	
	public AppMacaconautas() {
		this.addKeyListener(this);
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE)); //setar size do JFrame
		initFrame();
		macaco = new Macaco(10,10);
		espaco = new Espaco();
	}
	
	public void initFrame() {
		f = new JFrame("MACACONAUTAS"); //titulo do jogo ou setTitle()
		f.add(this); //adicionar o que criamos para ficar visível
		f.setResizable(false); //nao pode redimensionar 
		f.pack(); //fazer o setPreferredSize funcionar de forma correta
		f.setLocationRelativeTo(null); //centro (tem que estar depois do pack)
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //fechar quando clicar no x e parar de vez
		f.setVisible(true); //deixar ele visivel
	}
	
	public static void main(String[] args) {
		AppMacaconautas AppMacaconautas = new AppMacaconautas();
		AppMacaconautas.start();
	}
	
	public void tick() {
		//Update the AppMacaconautas
		macaco.tick();
		espaco.tick();
		checarColisao();
		
		}
	
	public synchronized void start() { //synchronized para evitar que a thread use/mude o mesmo recurso ao mesmo tempo
		this.isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void render() {
		//renderizar the AppMacaconautas
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) { //significa que ainda nao existe nenhum buffer strategy
			this.createBufferStrategy(3); //sequecia de buffers que colocamos na tela para otimizar a renderizacao (entre 2 ou 3)
			
			return; //"break"
		}
		Graphics g = bs.getDrawGraphics(); //podemos gerar imagem, retangulo, string
		g.setColor(Color.PINK);
		g.fillRect(0,0,WIDTH*SCALE,HEIGHT*SCALE); //aparece um retangulo na tela (x,y,largura,altura)
		macaco.render(g);
		espaco.render(g);
		bs.show(); //mostra o grafico
	}
	
	public void checarColisao() { //usar get 
		Rectangle formaMacaco = macaco.getBounds();
		Rectangle formaInimigo = null;
		for (int i = 0; i < espaco.inimigosPorSection; i++) {
			formaInimigo = espaco.obstaculos.get(i).getBounds();
			if (formaMacaco.intersects(formaInimigo)) {
				//criar set visivel (atributo na interface alem de speed)
				System.exit(0);
			}
		}
	}
	
	
	public void run() {
		while (this.isRunning) {
				tick();
				render();
				try {
					Thread.sleep(1000/60); //60 FPS
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			}
		
		stop();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			macaco.setUp(true);
}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			macaco.setUp(false);
		} 
	}
	
}
	

