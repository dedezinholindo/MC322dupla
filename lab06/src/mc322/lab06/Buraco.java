package mc322.lab06;

public class Buraco extends Componente {

    /**
     * coordenadas: coordenadas do buraco.
     * Inicializa um buraco.
     */
    Buraco(int[] coordenadas) {
        super('B', coordenadas);
        int coordenadasComponentesAssociados[][] = coordenadasComponentesAssociados();
        this.componentesAssociados = new Componente[4];
        for (int i = 0; i < 4; i++) {
            if (Posicao.valida(coordenadasComponentesAssociados[i])) {
                this.componentesAssociados[this.numComponentesAssociados] = new Brisa(coordenadasComponentesAssociados[i]);
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
                    this.componentesAssociados[i].setCaverna(caverna);
            }
            return true;
        }
        return false;
    }
}