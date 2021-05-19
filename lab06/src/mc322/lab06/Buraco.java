package mc322.lab06;

public class Buraco extends Componente {
    
    /**
     * coordenadas: coordenadas do buraco.
     * Inicializa um buraco.
     */
    Buraco(int[] coordenadas) {
        super('B', coordenadas);
    }

    /**
     * buraco: buraco na forma ["i:j", "t"], em que i é a linha, j, a
     * coluna e t o tipo do buraco, 'B'.
     * Inicializa um buraco.
     */
    Buraco(String[] buraco) {
        this(Posicao.coordenadasParaInt(buraco[0]));
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
                    componenteAssociado = new Componente('b', coordenadasComponentesAssociados[i]);
                    if (!componenteAssociado.setCaverna(caverna)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
}