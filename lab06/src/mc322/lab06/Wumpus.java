package mc322.lab06;

public class Wumpus extends Componente {

    private int numComponentesAssociados;
    private Componente componentesAssociados[];

    /**
     * coordenadas: coordenadas do wumpus.
     * Inicializa um wumpus.
     */
    Wumpus(int[] coordenadas) {
        super('W', coordenadas);
        int coordenadasComponentesAssociados[][] = Posicao.criarComponentesAssociados(this.componentesAssociados);
        for (int i = 0; i < 4; i++) {
            if (Posicao.valida(coordenadasComponentesAssociados[i])) {
                this.componentesAssociados[i] = new Fedor('f', coordenadasComponentesAssociados[i]);
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
     * associados à caverna e associe-a ao componente, e false, caso o  ????????????????????????????????????????/
     * contrário.
     */
    public boolean setCaverna(Caverna caverna) {
        if (caverna.adicionarComponente(this)) {
            for (int i = 0; i < this.numComponentesAssociados; i++) {
                    this.componenteAssociados[i].setCaverna(caverna);
            }
            return true;
        }
        return false;
    }

    /**
     * Retira o componente e seus componentes associados da caverna.
     */
    public void retirar() {
        this.caverna.retirarComponente(this);
        for (int i = 0; i < this.numComponentesAssociados; i++) {
            this.caverna.retirarComponente(this.componentesAssociados[i]);
        }
    }
}