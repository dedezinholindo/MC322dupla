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


//tentar criar frame aqui e quando passar pros outros estados do jogo ir mudando com base nesse frame de origem
public class AppMacaconautas extends Canvas {
	public static JFrame f;
	public final static int WIDTH = 160;
	public final static int HEIGHT = 120;
	public final static int SCALE = 4;
	private static char appState; //"L" para Loja, "M" para menu inicial e "J" para jogo
	
	
	public AppMacaconautas() {
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE)); //setar size do JFrame
		initFrame();
		appState = 'M';
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
	
	public static void main(String[] args) throws InterruptedException {	//throws para sleep	
		AppMacaconautas app = new AppMacaconautas();
		appState = 'J'; //TESTE PARA INICIAR O JOGO
		Thread.currentThread().sleep(10); //necessário para sumir a outra janela - instantaneo ele trava
		if (appState == 'M') {
			//abrir menu
		} else if (appState == 'L') {
			//abrir loja
		} else {
			ControleJogo jogo = new ControleJogo();
			jogo.start();
		}
	}

	
}
	

