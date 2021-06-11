package mc322.macaconautas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle; //vai ter toda a colisão e todos os métodos necessários
import java.awt.image.BufferedImage;

public class Macaco extends Componente {

	private final static int MACACO_WIDTH = 32;
	private final static int MACACO_HEIGHT = 32;

	private final static String MACACO_SPRITE_SHEET_PATH = "res/spritesheet.png";
	private final static int MACACO_SPRITE_X = 0;
	private final static int MACACO_SPRITE_Y = 0;
	private final static int MACACO_SPRITE_WIDTH = 32;
	private final static int MACACO_SPRITE_HEIGHT = 32;
	private final static int MACACO_QUANTIDADE_SPRITES = 5;

	private final static int MACACO_MAX_FRAMES_ANIMACAO = 3;

	private final static int GOING_UP_SPEED = 5;
	private final static int GOING_DOWN_SPEED = 3;

	private boolean isGoingUp; // indica se o macaco está indo para cima.
	private boolean isWalking; // indica se o macaco está andando no chão.

	/**
	 * Inicializa um macaco.
	 * @param x coordenada x do macaco.
	 * @param y coordenada y do macaco.
	 */
	public Macaco(int x, int y) {
		super(x, y, MACACO_WIDTH, MACACO_HEIGHT);
		this.quantidadeSprites = MACACO_QUANTIDADE_SPRITES;
		this.sprites = new BufferedImage[this.quantidadeSprites];
		SpriteSheet spriteSheet = new SpriteSheet(MACACO_SPRITE_SHEET_PATH);
		for (int i = 0; i < this.quantidadeSprites; i++) {
			this.sprites[i] = spriteSheet.getSprite(MACACO_SPRITE_X, MACACO_SPRITE_Y, MACACO_SPRITE_WIDTH, MACACO_HEIGHT);
		}
	}

	/**
	 * Altera o estado de movimento para cima do macaco.
	 * @param isGoingUp novo estado de movimento para cima do macaco.
	 */
	public void setIsGoingUp(boolean isGoingUp) {
		this.isGoingUp = isGoingUp;
	}

	/**
	 * Atualiza o estado do macaco em um frame.
	 */
	public void tick() {
		this.isWalking = false;
		if (isGoingUp) {
			this.y -= GOING_UP_SPEED;
		} else { 
			this.y += GOING_DOWN_SPEED;
		}
		if (this.y + this.height > AppMacaconautas.HEIGHT * AppMacaconautas.SCALE) {
			this.y = AppMacaconautas.HEIGHT * AppMacaconautas.SCALE - this.height;
		} else if (this.y <= 0) {
			this.y = 0;
			this.isWalking = true;
		}
	}

	/**
	 * Renderiza o macaco na tela.
	 * @param g
	 */
	public void render (Graphics g) {
		if (this.isVisible) {
			BufferedImage sprite;
			if (this.isGoingUp) {
				sprite = this.sprites[1];
			} else if (this.isWalking) {
				sprite = this.sprites[2 + this.frameAnimacao];
				this.frameAnimacao++;
				if (this.frameAnimacao == MACACO_MAX_FRAMES_ANIMACAO) {
					this.frameAnimacao = 0;
				}
			} else {
				sprite = this.sprites[0];
			}
			g.drawImage(sprite, this.x, this.y, null);

//			g.setColor(Color.black);
//			g.fillRect(this.x, this.y, this.width, this.height);
		}
	}
}