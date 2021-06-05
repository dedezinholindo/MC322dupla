package mc322.macaconautas;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Espaco {
	
	private ArrayList<Obstaculo> obstaculos; 
	private ArrayList<Alien> aliens;
	private int obstaculosPorSessao; 
	private int aliensPorSessao;
	private final static int BIGBOSS = 3000;

	public Espaco() {
		obstaculosPorSessao = 30;
		aliensPorSessao = 20;
		obstaculos = new ArrayList<Obstaculo>();
		for (int i = 0; i < obstaculosPorSessao; i ++) {
			Random aleatorioDois = new Random();
			int valory = aleatorioDois.nextInt((AppMacaconautas.HEIGHT * AppMacaconautas.SCALE - 16) + 1);
			int valorx = aleatorioDois.nextInt((AppMacaconautas.WIDTH * AppMacaconautas.SCALE - 16 + BIGBOSS) + 1) + AppMacaconautas.WIDTH * AppMacaconautas.SCALE; //aleatorio.nextInt((max - min) + 1) + min;
			obstaculos.add(new Obstaculo(valorx, valory, 16,16));
		}
		aliens = new ArrayList<Alien>();
		for (int i = 0; i < aliensPorSessao; i ++) {
			Random aleatorioDois = new Random();
			int valory = aleatorioDois.nextInt((AppMacaconautas.HEIGHT * AppMacaconautas.SCALE - 16) + 1);
			int valorx = aleatorioDois.nextInt((AppMacaconautas.WIDTH * AppMacaconautas.SCALE - 16 + BIGBOSS) + 1) + AppMacaconautas.WIDTH * AppMacaconautas.SCALE; //aleatorio.nextInt((max - min) + 1) + min;
			aliens.add(new Alien(valorx, valory, Alien.ALIEN_WIDTH,Alien.ALIEN_HEIGHT));
		}
	}
	
	public int getObstaculosPorSessao() {
		return obstaculosPorSessao;
	}
	
	public ArrayList<Obstaculo> getObstaculos() {
		return obstaculos;
	}

	public int getAliensPorSessao() {
		return aliensPorSessao;
	}
	
	public ArrayList<Alien> getAliens() {
		return aliens;
	}
	
	public void tick() {
		for(int i = 0; i < obstaculosPorSessao; i++) {
			obstaculos.get(i).tick();
		}
		for(int i = 0; i < aliensPorSessao; i++) {
			aliens.get(i).tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < obstaculosPorSessao; i++) {
			obstaculos.get(i).render(g);
		}
		for(int i = 0; i < aliensPorSessao; i++) {
			aliens.get(i).render(g);
		}
	}
	
	
	
}
