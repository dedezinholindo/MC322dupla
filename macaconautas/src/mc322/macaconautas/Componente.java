package mc322.macaconautas;

import java.awt.Rectangle;

public class Componente extends Rectangle implements IComponente{
	
	protected int speed;
	protected boolean isVisible;
	
	public Componente(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	@Override
	public void tick() {
		
		
	}
	@Override
	public void render() {
				
	}
	
	
	

}
