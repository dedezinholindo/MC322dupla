package mc322.lab06;

public class Wumpus extends Componente {
    
    /**
     * coordenadas: coordenadas do wumpus.
     * Inicializa um wumpus.
     */
    Wumpus(int[] coordenadas) {
        super('W', coordenadas);
    }

    /**
     * wumpus: wumpus na forma ["i:j", "t"], em que i é a linha, j, a
     * coluna e t o tipo do wumpus, 'W'.
     * Inicializa um wumpus.
     */
    Wumpus(String[] wumpus) {
        this(Posicao.coordenadasParaInt(wumpus[0]));
    }
}