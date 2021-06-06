package mc322.macaconautas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

public class ControleJogo extends Canvas implements Runnable, KeyListener {
	
	public final static int MACACO_WIDTH = 32;
	public final static int MACACO_HEIGHT = 32;
	private char jogoState; //N para normal, P para pausado (uso do pause) e O para Game Over
	private boolean isRunning;
	private Thread thread;
	private JFrame f;
	private Macaco macaco;
	private Espaco espaco;
	private static ArrayList <Laser> lasers; 
	
	public ControleJogo() {
		this.setPreferredSize(new Dimension(AppMacaconautas.WIDTH*AppMacaconautas.SCALE, AppMacaconautas.HEIGHT*AppMacaconautas.SCALE)); //setar size do JFrame
		initFrame();
		this.addKeyListener(this);
		macaco = new Macaco(15, 0, MACACO_WIDTH, MACACO_HEIGHT);
		espaco = new Espaco();
		lasers = new ArrayList<Laser>();
		jogoState = 'N';
	}
	
	private void apagarVisibilidadeJanelas() {
		AppMacaconautas.f.setVisible(false); //fica aberto o tempo todo? Ou será excluido? ver sobrecarga 
		//loja
		//menu
	}
	
	public void initFrame() {
		f = new JFrame("MACACONAUTAS"); //titulo do jogo ou setTitle()
		f.add(this); //adicionar o que criamos para ficar visível
		f.setResizable(false); //nao pode redimensionar 
		f.pack(); //fazer o setPreferredSize funcionar de forma correta
		f.setLocationRelativeTo(null); //centro (tem que estar depois do pack)
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //fechar quando clicar no x e parar de vez
		f.setVisible(true); //deixar ele visivel
		apagarVisibilidadeJanelas();
	}
	
	
	public static ArrayList<Laser> getLasers() {
		return lasers;
	}

	public static void setLasers(ArrayList<Laser> lasers) {
		ControleJogo.lasers = lasers;
	}

	public void tick() {
		//Update the AppMacaconautas
		switch(jogoState) {
			case 'N':
				macaco.tick();
				espaco.tick();
				checarColisoes();
				for (int i = 0; i < lasers.size(); i++) {
					lasers.get(i).tick();
				}
				break;
				
			case 'P':
				//pause
				break;
				
			case 'O':
				//stop();
				break;
		}
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
		for (int i = 0; i < lasers.size(); i++) {
			lasers.get(i).render(g);
		}
		if (jogoState == 'O') {
			//TELA GAME OVER
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(0,0,0,100)); //transparencia
			g2.fillRect(0, 0, AppMacaconautas.WIDTH * AppMacaconautas.SCALE, AppMacaconautas.HEIGHT * AppMacaconautas.SCALE);
			
			//FRASE GAME OVER
			g.setFont(new Font("arial", Font.BOLD, 30));
			g.setColor(Color.WHITE);
			g.drawString("GAME OVER", AppMacaconautas.WIDTH * AppMacaconautas.SCALE/2 - 120 , AppMacaconautas.HEIGHT * AppMacaconautas.SCALE/2 - 100);
		}
		bs.show(); //mostra o grafico
	}
	
	public boolean checarColisaoObstaculos() {
		Rectangle formaMacaco = macaco.getBounds();
		Rectangle formaObstaculo = null;
		for (int i = 0; i < espaco.getObstaculosNaSessao(); i++) {
			formaObstaculo = espaco.getObstaculos().get(i).getBounds();
			if (formaMacaco.intersects(formaObstaculo)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checarColisaoAlien() {
		Rectangle formaMacaco = macaco.getBounds();
		Rectangle formaAlien = null;
		for (int i = 0; i < espaco.getAliensNaSessao(); i++) {
			formaAlien = espaco.getAliens().get(i).getBounds();
			if (formaMacaco.intersects(formaAlien)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checarColisaoLaser() {
		Rectangle formaMacaco = macaco.getBounds();
		Rectangle formaLaser = null;
		for (int i = 0; i < lasers.size(); i++) {
			formaLaser = lasers.get(i).getBounds();
			if (formaMacaco.intersects(formaLaser)) {
				return true;
			}
		}
		return false;
	}
	
	
	public void checarColisoes() { 
		if(checarColisaoObstaculos() || checarColisaoAlien() || checarColisaoLaser()){
			jogoState = 'O';
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
