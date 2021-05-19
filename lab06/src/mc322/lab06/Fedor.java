package mc322.lab06;

public class Fedor extends Componente {

    /**
     * coordenadas: coordenadas do fedor.
     * Inicializa um fedor.
     */
    Fedor(int[] coordenadas) {
        super('f', coordenadas);
    }

    /**
     * fedor: fedor na forma ["i:j", "t"], em que i é a linha, j, a
     * coluna e t, o tipo do fedor, 'f'.
     * Inicializa um fedor.
     */
    Fedor(String[] fedor) {
        this(Posicao.coordenadasParaInt(fedor[0]));
    }
}