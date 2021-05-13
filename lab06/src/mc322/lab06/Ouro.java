package mc322.lab06;

public class Ouro extends Componente {
    
    /**
     * coordenadas: coordenadas do ouro.
     * Inicializa um ouro.
     */
    Ouro(int[] coordenadas) {
        this.tipo = 'O';
        this.coordenadas = coordenadas;
        this.caverna = null;
    }

    /**
     * ouro: ouro na forma ["i:j", "t"], em que i é a linha, j, a
     * coluna e t o tipo do ouro, 'O'.
     * Inicializa um ouro.
     */
    Ouro(String[] ouro) {
        this(Posicao.coordenadasParaInt(ouro[0]));
    }
}