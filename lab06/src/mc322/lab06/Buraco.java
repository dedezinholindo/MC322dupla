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
}