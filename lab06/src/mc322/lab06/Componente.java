package mc322.lab06;

public class Componente {
    protected char tipo; /* '-' para vazio ou # para vazio e ja percorrido, 'P' para herói, 'W' para Wumpus,
                    'B' para buraco, 'O' para ouro, 'f' para fedor e 'b'  para brisa. */
    private int[] coordenadas; /* tamanho 2. */
    static Caverna caverna; //ele diz que o componente tem referencia oara a caverna mas nao o contrario
    //util para exibir todo o caminho explorado pelo heroi (vai completando a medida que avanca)

    /**
     * tipo: tipo do componente.
     * coordenadas: coordenadas do componente.
     * Inicializa um componente.
     */
    Componente (char tipo, int[] coordenadas) {
        this.tipo = tipo;
        this.coordenadas = coordenadas;
    }

    /**
     * componente: componente na forma ["i:j", "t"], em que i é a linha, j, a
     * coluna e t o tipo do componente, sendo '_' o tipo vazio.
     * Inicializa um componente.
     */
    Componente (String[] componente) {
        //precisa transformar a string em character, por isso o charAt
        this((componente[1].charAt(0) == '_') ? '-' : componente[1].charAt(0));

        this((componente[1].charAt(0) == '_') ? '-' : componente[1].charAt(0),
               Posicao.coordenadasParaInt(componente[0]));
    }
}