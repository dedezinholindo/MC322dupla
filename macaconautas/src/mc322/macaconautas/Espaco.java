package mc322.macaconautas;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

//renderizar so o que esta aparecendo na tela
public class Espaco {
	//lembrar de apagar os dois depois de cada sessao (ou reiniciar)!! (laser ja apaga)
	private static ArrayList<Obstaculo> obstaculos; 
	private static ArrayList<Alien> aliens;
	private static ArrayList<Banana> bananas;
	private int obstaculosNaSessao; //variaveis pois irao aumentando com o tempo
	private int aliensNaSessao;
	private int bananasNaSessao; 
	private final static int BIGBOSS = 3000;

	//ver se tem como diminuir ou evitar repeticao
	public Espaco() {
		obstaculosNaSessao = 30;
		aliensNaSessao = 20;
		bananasNaSessao = 10;
		iniciarArrayItens();
	}
	
	public void iniciarArrayItens() {
		obstaculos = new ArrayList<Obstaculo>();
		for (int i = 0; i < obstaculosNaSessao; i ++) {
			Random aleatorioDois = new Random();
			int valory = aleatorioDois.nextInt((AppMacaconautas.HEIGHT * AppMacaconautas.SCALE - 16) + 1);
			int valorx = aleatorioDois.nextInt((AppMacaconautas.WIDTH * AppMacaconautas.SCALE - 16 + BIGBOSS) + 1) + AppMacaconautas.WIDTH * AppMacaconautas.SCALE; //aleatorio.nextInt((max - min) + 1) + min;
			obstaculos.add(new Obstaculo(valorx, valory, Obstaculo.OBSTACULO_WIDTH,Obstaculo.OBSTACULO_HEIGHT));
		}
		aliens = new ArrayList<Alien>();
		for (int i = 0; i < aliensNaSessao; i ++) {
			Random aleatorioDois = new Random();
			int valory = aleatorioDois.nextInt((AppMacaconautas.HEIGHT * AppMacaconautas.SCALE - 16) + 1);
			int valorx = aleatorioDois.nextInt((AppMacaconautas.WIDTH * AppMacaconautas.SCALE - 16 + BIGBOSS) + 1) + AppMacaconautas.WIDTH * AppMacaconautas.SCALE; //aleatorio.nextInt((max - min) + 1) + min;
			aliens.add(new Alien(valorx, valory, Alien.ALIEN_WIDTH,Alien.ALIEN_HEIGHT));
		}
		bananas = new ArrayList<Banana>();
		for (int i = 0; i < bananasNaSessao; i ++) {
			Random aleatorioDois = new Random();
			int valory = aleatorioDois.nextInt((AppMacaconautas.HEIGHT * AppMacaconautas.SCALE - 16) + 1);
			int valorx = aleatorioDois.nextInt((AppMacaconautas.WIDTH * AppMacaconautas.SCALE - 16 + BIGBOSS) + 1) + AppMacaconautas.WIDTH * AppMacaconautas.SCALE; //aleatorio.nextInt((max - min) + 1) + min;
			bananas.add(new Banana(valorx, valory, Banana.BANANA_WIDTH, Banana.BANANA_HEIGHT));
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
	
	public static ArrayList<Banana> getBananas() {
		return bananas;
	}

	public int getBananasNaSessao() {
		return bananasNaSessao;
	}

	public void setBananasNaSessao(int bananasNaSessao) {
		this.bananasNaSessao = bananasNaSessao;
	}
	

	public static void setBananas(ArrayList<Banana> bananas) {
		Espaco.bananas = bananas;
	}

	private void tickPecasRegulares() {
		for(int i = 0; i < obstaculosNaSessao; i++) {
			obstaculos.get(i).tick();
		}
		for(int i = 0; i < aliensNaSessao; i++) {
			aliens.get(i).tick();
		}
		for(int i = 0; i < bananasNaSessao; i++) {
			bananas.get(i).tick();
		}
	}
	
	public void tick() {
		tickPecasRegulares();
	}
	
	private void renderPecasRegulares(Graphics g) {
		for(int i = 0; i < obstaculosNaSessao; i++) {
			obstaculos.get(i).render(g);
		}
		for(int i = 0; i < aliensNaSessao; i++) {
			aliens.get(i).render(g);
		}
		for(int i = 0; i < bananasNaSessao; i++) {
			bananas.get(i).render(g);
		}
	}
	
	public void render(Graphics g) {
		renderPecasRegulares(g);
	}
	
	
	
}
