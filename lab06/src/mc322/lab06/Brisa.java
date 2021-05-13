package mc322.lab06;

public class Brisa extends Componente {
    
    /**
     * coordenadas: coordenadas da brisa.
     * Inicializa uma brisa.
     */
    Brisa(int[] coordenadas) {
        this.tipo = 'b';
        this.coordenadas = coordenadas;
        this.caverna = null;
    }

    /**
     * brisa: brisa na forma ["i:j", "t"], em que i é a linha, j, a
     * coluna e t o tipo da brisa, 'b'.
     * Inicializa uma brisa.
     */
    Brisa(String[] brisa) {
        this(Posicao.coordenadasParaInt(brisa[0]));
    }
}