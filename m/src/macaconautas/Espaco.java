package macaconautas;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Espaco {
	
	public ArrayList<Obstaculo> obstaculos; //usar get 
	public int inimigosPorSection; //usar get
	private final static int bigBoss = 3000;

	public Espaco() {
		inimigosPorSection = 20;
		obstaculos = new ArrayList<Obstaculo>();
		for (int i = 0; i < inimigosPorSection; i ++) {
			Random aleatorioDois = new Random();
			int valory = aleatorioDois.nextInt((AppMacaconautas.HEIGHT * AppMacaconautas.SCALE - 16) + 1);
			int valorx = aleatorioDois.nextInt((AppMacaconautas.WIDTH * AppMacaconautas.SCALE - 16 + bigBoss) + 1) + AppMacaconautas.WIDTH * AppMacaconautas.SCALE; //aleatorio.nextInt((max - min) + 1) + min;
			obstaculos.add(new Obstaculo(valorx, valory, 16,16));
		}
	}
	
	public void tick() {
		for(int i = 0; i < inimigosPorSection; i++) {
			obstaculos.get(i).tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < inimigosPorSection; i++) {
			obstaculos.get(i).render(g);
		}
	}
	
	
	
}
