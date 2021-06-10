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

public class ControleJogo extends Canvas implements Runnable, KeyListener, IModo {

	private char jogoState; //N para normal, P para pausado (uso do pause) e O para Game Over
	private boolean isRunning;
	//private boolean estaSuspensa;
	private Thread thread;
	private JFrame f;
	private Macaco macaco;
	private Espaco espaco;
	private static ArrayList <Laser> lasers; 

	public ControleJogo() throws InterruptedException {
		this.setPreferredSize(new Dimension(AppMacaconautas.WIDTH*AppMacaconautas.SCALE, AppMacaconautas.HEIGHT*AppMacaconautas.SCALE)); //setar size do JFrame
		initFrame();
		this.addKeyListener(this);
		macaco = new Macaco(15, 0);
		espaco = new Espaco();
		lasers = new ArrayList<Laser>();
		jogoState = 'N';
		isRunning = true;
		//estaSuspensa = false;
	}

	private void initFrame() {
		f = new JFrame("MACACONAUTAS"); //titulo do jogo ou setTitle()
		f.add(this); //adicionar o que criamos para ficar visível
		f.setResizable(false); //nao pode redimensionar 
		f.pack(); //fazer o setPreferredSize funcionar de forma correta
		f.setLocationRelativeTo(null); //centro (tem que estar depois do pack)
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //fechar quando clicar no x e parar de vez
		f.setVisible(true); //deixar ele visivel
	}

	public char getJogoState() {
		return jogoState;
	}

	public static ArrayList<Laser> getLasers() {
		return lasers;
	}

	public static void setLasers(ArrayList<Laser> lasers) {
		ControleJogo.lasers = lasers;
	}

	private void tick() {
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
			//pause(); //tecla do teclado
			break;

		case 'O':
			// Mostrar resultados por alguns segundos e voltar pro menu
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stop();
			break;
		}
	}

	public synchronized void start() throws InterruptedException { //synchronized para evitar que a thread use/mude o mesmo recurso ao mesmo tempo
		this.isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	/*private synchronized void pause() {
		estaSuspensa = true;
	}*/

	public synchronized void stop() {
		f.dispose();
		this.isRunning = false;
	}

	//TIRAR DAQUI??
	private void render() {
		//renderizar the AppMacaconautas
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) { //significa que ainda nao existe nenhum buffer strategy
			this.createBufferStrategy(3); //sequencia de buffers que colocamos na tela para otimizar a renderizacao (entre 2 ou 3)	
			return; //"break"
		}
		//fundo
		Graphics g = bs.getDrawGraphics(); //podemos gerar imagem, retangulo, string
		g.setColor(Color.PINK);
		g.fillRect(0,0, AppMacaconautas.WIDTH*AppMacaconautas.SCALE,AppMacaconautas.HEIGHT*AppMacaconautas.SCALE); //aparece um retangulo na tela (x,y,largura,altura)

		macaco.render(g);
		espaco.render(g);
		for (int i = 0; i < lasers.size(); i++) {
			lasers.get(i).render(g);
		}
		//tirar daqui para renderizar o menu
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

	private boolean checarColisaoObstaculo() {
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

	private boolean checarColisaoAlien() {
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

	private boolean checarColisaoLaser() {
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

	private int checarColisaoBanana() {
		Rectangle formaMacaco = macaco.getBounds();
		Rectangle formaBanana = null;
		for (int i = 0; i < espaco.getBananasNaSessao(); i++) {
			formaBanana = espaco.getBananas().get(i).getBounds();
			if (formaMacaco.intersects(formaBanana)) {
				return i;
			}
		}
		return -1;
	}

	private void checarColisoes() { 
//		if(checarColisaoObstaculo() || checarColisaoAlien() || checarColisaoLaser()){
//			jogoState = 'O';
//		}
		int b = checarColisaoBanana();
		if (b != -1) {
			ArrayList<Banana> bananas = espaco.getBananas();
			bananas.remove(b);
			espaco.setBananas(bananas); //remocao da banana
			espaco.setBananasNaSessao(espaco.getBananasNaSessao() - 1);
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

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			macaco.setIsGoingUp(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			macaco.setIsGoingUp(false);
		} 
	}


}
