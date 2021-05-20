package mc322.lab06;

public class Wumpus extends Componente {

    /**
     * coordenadas: coordenadas do wumpus.
     * Inicializa um wumpus.
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
     * wumpus: wumpus na forma ["i:j", "t"], em que i é a linha, j, a
     * coluna e t, o tipo do wumpus, 'W'.
     * Inicializa um wumpus.
     */
    Wumpus(String[] wumpus) {
        this(Posicao.coordenadasParaInt(wumpus[0]));
    }

    /**
     * caverna: caverna.
     * Retorna true, caso adicione o componente e os seus componentes
     * associados à caverna e associe-a ao componente, e false, caso o
     * contrário.
     */
    public boolean setCaverna(Caverna caverna) {
        if (caverna.adicionarComponente(this)) {
            for (int i = 0; i < this.numComponentesAssociados; i++) {
                    this.componentesAssociados[i].setCaverna(caverna);
            }
            return true;
        }
        return false;
    }
}