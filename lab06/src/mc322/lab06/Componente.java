package mc322.lab06;

public class Componente {
    protected char tipo; /* '-' para vazio ou # para vazio e ja percorrido, 'P' para herói, 'W' para Wumpus,
                    'B' para buraco, 'O' para ouro, 'f' para fedor e 'b'  para brisa. */

    //int[] coordenadas; /* tamanho 2. */
    //PEDRO: nao seria melhor direcionar as cordenadas para sala ou mantem os dois com cordenadas?
    //todos estao agrupados em um local so com a sala contendo a coordenada parece mais organizado
    /**
     * tipo: tipo do componente.
     * coordenadas: coordenadas do componente.
     * Inicializa um componente.
     */
    Componente (char tipo) {
        this.tipo = tipo;
        //this.coordenadas = coordenadas;
    }

    /**
     * componente: componente na forma ["i:j", "t"], em que i é a linha, j, a
     * coluna e t o tipo do componente, sendo '_' o tipo vazio.
     * Inicializa um componente.
     */
    Componente (String[] componente) {
        //precisa transformar a string em character, por isso o charAt
        this((componente[1].charAt(0) == '_') ? '-' : componente[1].charAt(0));

        //this((componente[1].charAt(0) == '_') ? '-' : componente[1].charAt(0),
        //        Posicao.coordenadasParaInt(componente[0]));
    }
}