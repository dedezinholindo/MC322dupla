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

    /**
     * caverna: caverna a conectar ao componente.
     * Retorna true, caso adicione o componente e os seus componentes
     * associados à caverna, e false, caso o contrário.
     */
    public boolean setCaverna(Caverna caverna) {
        int coordenadasComponentesAssociados[][] = new int[4][2];
        coordenadasComponentesAssociados[0] = new int[] {this.coordenadas[0] + 1, this.coordenadas[1]};
        coordenadasComponentesAssociados[1] = new int[] {this.coordenadas[0], this.coordenadas[1] + 1};
        coordenadasComponentesAssociados[2] = new int[] {this.coordenadas[0] - 1, this.coordenadas[1]};
        coordenadasComponentesAssociados[3] = new int[] {this.coordenadas[0], this.coordenadas[1] - 1};
        if (caverna.adicionarComponente(this)) {
            Componente componenteAssociado;
            for (int i = 0; i < 4; i++) {
                if (Posicao.valida(coordenadasComponentesAssociados[i])) {
                    componenteAssociado = new Fedor('f', coordenadasComponentesAssociados[i]);
                    componenteAssociado.setCaverna(caverna);
                }
            }
            return true;
        }
        return false;
    }
}