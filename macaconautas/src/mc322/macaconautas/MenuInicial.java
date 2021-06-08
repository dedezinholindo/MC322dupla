package mc322.macaconautas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class MenuInicial extends Canvas implements Runnable, KeyListener, IModo {
	private JFrame f;
	
	public MenuInicial() {
		this.setPreferredSize(new Dimension(AppMacaconautas.WIDTH*AppMacaconautas.SCALE, AppMacaconautas.HEIGHT*AppMacaconautas.SCALE)); //setar size do JFrame
		initFrame();
		this.addKeyListener(this);
	}
	
	private void apagarVisibilidadeJanelas() {
		AppMacaconautas.f.setVisible(false);//fica aberto o tempo todo? Ou será excluido? ver sobrecarga 
		//apagar o do JOGO
		//loja
		//menu
	}
	
	private void initFrame() {
		f = new JFrame("MACACONAUTAS"); //titulo do jogo ou setTitle()
		f.add(this); //adicionar o que criamos para ficar visível
		f.setResizable(false); //nao pode redimensionar 
		f.pack(); //fazer o setPreferredSize funcionar de forma correta
		f.setLocationRelativeTo(null); //centro (tem que estar depois do pack)
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //fechar quando clicar no x e parar de vez
		f.setVisible(true); //deixar ele visivel
		apagarVisibilidadeJanelas();
	}
	
	public synchronized void start() throws InterruptedException{
		render();
	}
	
	private synchronized void stop() {
		
	}
	
	private void tick() {
		
	}
	
	private void render() {
		//renderizar the AppMacaconautas
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) { //significa que ainda nao existe nenhum buffer strategy
			this.createBufferStrategy(3); //sequencia de buffers que colocamos na tela para otimizar a renderizacao (entre 2 ou 3)	
			return; //"break"
		}
		//fundo
		Graphics g = bs.getDrawGraphics(); //podemos gerar imagem, retangulo, string
		g.setColor(Color.BLACK);
		g.fillRect(0,0, AppMacaconautas.WIDTH*AppMacaconautas.SCALE,AppMacaconautas.HEIGHT*AppMacaconautas.SCALE); //aparece um retangulo na tela (x,y,largura,altura)
		bs.show(); //mostra o grafico
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
