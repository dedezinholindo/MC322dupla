package mc322.macaconautas;

import java.awt.Rectangle;

public class Componente extends Rectangle implements IComponente{

	protected int speed;
	protected boolean isVisible;

	/**
	 * Inicializa um componente.
	 * @param x coordenada x do componente.
	 * @param y coordenada y do componente.
	 * @param width largura do componente.
	 * @param height altura do componente.
	 */
	public Componente(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.isVisible = true;
	}

	/**
	 * Atualiza o estado do componente em um frame.
	 */
	public void tick() {}

	/**
	 * Renderiza o componente na tela.
	 */
	public void render() {}

}