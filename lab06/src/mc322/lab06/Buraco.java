package mc322.lab06;

public class Buraco extends Componente {

    private int numComponentesAssociados;
    private Componente componentesAssociados[];

    /**
     * coordenadas: coordenadas do buraco.
     * Inicializa um buraco.
     */
    Buraco(int[] coordenadas) {
        super('B', coordenadas);
        int coordenadasComponentesAssociados[][] = new int[4][2];
        coordenadasComponentesAssociados[0] = new int[] {this.coordenadas[0] + 1, this.coordenadas[1]};
        coordenadasComponentesAssociados[1] = new int[] {this.coordenadas[0], this.coordenadas[1] + 1};
        coordenadasComponentesAssociados[2] = new int[] {this.coordenadas[0] - 1, this.coordenadas[1]};
        coordenadasComponentesAssociados[3] = new int[] {this.coordenadas[0], this.coordenadas[1] - 1};
        for (int i = 0; i < 4; i++) {
            if (Posicao.valida(coordenadasComponentesAssociados[i])) {
                this.componentesAssociados[i] = new Brisa('b', coordenadasComponentesAssociados[i]);
                this.numComponentesAssociados++;
            }
        }
    }

    /**
     * buraco: buraco na forma ["i:j", "t"], em que i é a linha, j, a
     * coluna e t, o tipo do buraco, 'B'.
     * Inicializa um buraco.
     */
    Buraco(String[] buraco) {
        this(Posicao.coordenadasParaInt(buraco[0]));
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