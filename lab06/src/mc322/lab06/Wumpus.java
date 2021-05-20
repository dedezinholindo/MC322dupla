package mc322.lab06;

public class Wumpus extends Componente {

    /**
     * coordenadas: coordenadas do Wumpus.
     * Inicializa um Wumpus.
     */
    Wumpus(int[] coordenadas) {
        super('W', coordenadas);
        int coordenadasComponentesAssociados[][] = coordenadasComponentesAssociados();
        this.componentesAssociados = new Componente[4];
        for (int i = 0; i < 4; i++) {
            if (Posicao.valida(coordenadasComponentesAssociados[i])) {
                this.componentesAssociados[this.numComponentesAssociados] = new Fedor(coordenadasComponentesAssociados[i]);
                this.numComponentesAssociados++;
            }
        }
    }

    /**
     * wumpus: Wumpus na forma ["i:j", "t"], em que i é a linha, j, a
     * coluna e t, o tipo do Wumpus, 'W'.
     * Inicializa um Wumpus.
     */
    Wumpus(String[] wumpus) {
        this(Posicao.coordenadasParaInt(wumpus[0]));
    }
}