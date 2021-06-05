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

public class ControleJogo extends Canvas implements Runnable, KeyListener {
	
	public final static int MACACO_WIDTH = 32;
	public final static int MACACO_HEIGHT = 32;
	private boolean isRunning;
	private Thread thread;
	private JFrame f;
	public Macaco macaco;
	
	public Espaco espaco;
	
	public ControleJogo() {
		this.setPreferredSize(new Dimension(AppMacaconautas.WIDTH*AppMacaconautas.SCALE, AppMacaconautas.HEIGHT*AppMacaconautas.SCALE)); //setar size do JFrame
		initFrame();
		this.addKeyListener(this);
		macaco = new Macaco(15, 0, MACACO_WIDTH, MACACO_HEIGHT);
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

	public void tick() {
		//Update the AppMacaconautas
		macaco.tick();
		espaco.tick();
		checarColisoes();
		
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
	
	//TIRAR DAQUI??
	public void render() {
		//renderizar the AppMacaconautas
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) { //significa que ainda nao existe nenhum buffer strategy
			this.createBufferStrategy(3); //sequencia de buffers que colocamos na tela para otimizar a renderizacao (entre 2 ou 3)	
			return; //"break"
		}
		Graphics g = bs.getDrawGraphics(); //podemos gerar imagem, retangulo, string
		g.setColor(Color.PINK);
		g.fillRect(0,0, AppMacaconautas.WIDTH*AppMacaconautas.SCALE,AppMacaconautas.HEIGHT*AppMacaconautas.SCALE); //aparece um retangulo na tela (x,y,largura,altura)
		macaco.render(g);
		espaco.render(g);
		bs.show(); //mostra o grafico
	}
	
	public void checarColisaoObstaculos() {
		Rectangle formaMacaco = macaco.getBounds();
		Rectangle formaAlien = null;
		for (int i = 0; i < espaco.getObstaculosPorSessao(); i++) {
			formaAlien = espaco.getObstaculos().get(i).getBounds();
			if (formaMacaco.intersects(formaAlien)) {
				//criar set visivel (atributo na interface alem de speed)
				System.exit(0);
			}
		}
	}
	public void checarColisoes() { //usar get 
		checarColisaoObstaculos();
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
