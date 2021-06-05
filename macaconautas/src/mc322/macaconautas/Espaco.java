package mc322.macaconautas;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

//renderizar so o que esta aparecendo na tela
public class Espaco {
	
	private static ArrayList<Obstaculo> obstaculos; //apagar os dois depois de cada sessao (ou reiniciar)
	private static ArrayList<Alien> aliens;
	private int obstaculosNaSessao; 
	private int aliensNaSessao;
	private final static int BIGBOSS = 3000;

	public Espaco() {
		obstaculosNaSessao = 30;
		aliensNaSessao = 20;
		obstaculos = new ArrayList<Obstaculo>();
		for (int i = 0; i < obstaculosNaSessao; i ++) {
			Random aleatorioDois = new Random();
			int valory = aleatorioDois.nextInt((AppMacaconautas.HEIGHT * AppMacaconautas.SCALE - 16) + 1);
			int valorx = aleatorioDois.nextInt((AppMacaconautas.WIDTH * AppMacaconautas.SCALE - 16 + BIGBOSS) + 1) + AppMacaconautas.WIDTH * AppMacaconautas.SCALE; //aleatorio.nextInt((max - min) + 1) + min;
			obstaculos.add(new Obstaculo(valorx, valory, 16,16));
		}
		aliens = new ArrayList<Alien>();
		for (int i = 0; i < aliensNaSessao; i ++) {
			Random aleatorioDois = new Random();
			int valory = aleatorioDois.nextInt((AppMacaconautas.HEIGHT * AppMacaconautas.SCALE - 16) + 1);
			int valorx = aleatorioDois.nextInt((AppMacaconautas.WIDTH * AppMacaconautas.SCALE - 16 + BIGBOSS) + 1) + AppMacaconautas.WIDTH * AppMacaconautas.SCALE; //aleatorio.nextInt((max - min) + 1) + min;
			aliens.add(new Alien(valorx, valory, Alien.ALIEN_WIDTH,Alien.ALIEN_HEIGHT));
		}
	}
	
	public int getObstaculosNaSessao() {
		return obstaculosNaSessao;
	}
	
	public static void setObstaculos(ArrayList<Obstaculo> obstaculos) {
		Espaco.obstaculos = obstaculos;
	}

	public static void setAliens(ArrayList<Alien> aliens) {
		Espaco.aliens = aliens;
	}

	public void setObstaculosNaSessao(int obstaculosNaSessao) {
		this.obstaculosNaSessao = obstaculosNaSessao;
	}

	public void setAliensNaSessao(int aliensNaSessao) {
		this.aliensNaSessao = aliensNaSessao;
	}

	public static ArrayList<Obstaculo> getObstaculos() {
		return obstaculos;
	}

	public int getAliensNaSessao() {
		return aliensNaSessao;
	}
	
	public static ArrayList<Alien> getAliens() {
		return aliens;
	}
	
	public void tick() {
		for(int i = 0; i < obstaculosNaSessao; i++) {
			obstaculos.get(i).tick();
		}
		for(int i = 0; i < aliensNaSessao; i++) {
			aliens.get(i).tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < obstaculosNaSessao; i++) {
			obstaculos.get(i).render(g);
		}
		for(int i = 0; i < aliensNaSessao; i++) {
			aliens.get(i).render(g);
		}
	}
	
	
	
}
