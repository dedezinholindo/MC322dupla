package mc322.lab06;

public class Fedor extends Componente {
    
    /**
     * coordenadas: coordenadas do fedor.
     * Inicializa um fedor.
     */
    Fedor(int[] coordenadas) {
        this.tipo = 'f';
        this.coordenadas = coordenadas;
        this.caverna = null;
    }

    /**
     * fedor: fedor na forma ["i:j", "t"], em que i é a linha, j, a
     * coluna e t o tipo do fedor, 'f'.
     * Inicializa um fedor.
     */
    Fedor(String[] fedor) {
        this(Posicao.coordenadasParaInt(fedor[0]));
    }
}