package mc322.lab06;

public class Componente {
    char tipo; /* '-' para vazio, 'P' para herói, 'W' para Wumpus,
                    'B' para buraco, 'O' para ouro, 'f' para fedor e 'b'  para brisa. */
    int[] coordenadas; /* tamanho 2. */

    /**
     * tipo: tipo do componente.
     * coordenadas: coordenadas do componente.
     * Inicializa um componente.
     */
    Componente (char tipo, int[] coordenadas) {
        this.tipo = tipo;
        this.coordenadas = coordenadas.clone;
    }

    /**
     * componente: componente na forma ["i:j", "t"], em que i é a linha, j, a
     * coluna e t o tipo do componente, sendo '_' o tipo vazio.
     * Inicializa um componente.
     */
    Componente (String[] componente) {
        this((componente[1] == '_') ? '-' : componente[1],
                Posicao.coordenadasParaInt(componente[0]));
    }
}