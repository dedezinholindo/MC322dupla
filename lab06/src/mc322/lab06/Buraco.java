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
}