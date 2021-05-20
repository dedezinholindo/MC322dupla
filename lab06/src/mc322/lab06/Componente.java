  package mc322.lab06;

public abstract class Componente {

    protected char tipo; /* 'P' para herói, 'W' para Wumpus, 'B' para buraco,
                            'O' para ouro, 'f' para fedor e 'b'  para brisa. */
    protected int coordenadas[]; /* linha e coluna. */
    protected int numComponentesAssociados;
    protected Componente componentesAssociados[];
    protected Caverna caverna;
    
    /**
     * tipo: tipo do componente.
     * coordenadas: coordenadas do componente.
     * Inicializa um componente.
     */
    Componente(char tipo, int[] coordenadas) {
        this.tipo = tipo;
        this.coordenadas = coordenadas;
        this.numComponentesAssociados = 0;
        this.componentesAssociados = null;
        this.caverna = null;
    }

    /**
     * componente: componente na forma ["i:j", "t"], em que i é a linha, j, a
     * coluna e t, o tipo do componente, sendo '_' o indicador do tipo vazio.
     * Inicializa um componente.
     */
    Componente(String[] componente) {
        this(componente[1].charAt(0), Posicao.coordenadasParaInt(componente[0]));
    }

    /**
     * Retorna o tipo do componente.
     */
    public char getTipo() {
        return this.tipo;
    }

    /**
     * Retorna as coordenadas do componente.
     */
    public int[] getCoordenadas() {
        return this.coordenadas.clone();
    }

    /**
     * Retorna o número de componentes associados.
     */
    public int getNumComponentesAssociados() {
        return this.numComponentesAssociados;
    }

    /**
     * Retorna os componentes associados.
     */
    public Componente[] getComponentesAssociados() {
        return (this.componentesAssociados == null) ? null : this.componentesAssociados.clone();
    }

    /**
     * caverna: caverna.
     * Retorna true, caso adicione o componente e os seus componentes
     * associados à caverna e associe-a ao componente, e false, caso o
     * contrário.
     */
    public boolean setCaverna(Caverna caverna) {
        if (caverna.adicionarComponente(this)) {
            this.caverna = caverna;
            return true;
        }
        return false;
    }

    /**
     * Retorna a caverna do componente.
     */
    public Caverna getCaverna() {
        return this.caverna;
    }

    /**
     * Retorna true, caso o componente seja primário, e false, caso contrário.
     */
    public boolean isPrimario() {
        return (this.tipo == 'W' || this.tipo == 'B' || this.tipo == 'O');
    }

    /**
     * Retorna as coordenadas dos componentes associados.
     */
    protected int[][] coordenadasComponentesAssociados(){
        int coordenadasComponentesAssociados[][] = new int[4][2];
        coordenadasComponentesAssociados[0] = new int[] {this.coordenadas[0] + 1, this.coordenadas[1]};
        coordenadasComponentesAssociados[1] = new int[] {this.coordenadas[0], this.coordenadas[1] + 1};
        coordenadasComponentesAssociados[2] = new int[] {this.coordenadas[0] - 1, this.coordenadas[1]};
        coordenadasComponentesAssociados[3] = new int[] {this.coordenadas[0], this.coordenadas[1] - 1};
        return coordenadasComponentesAssociados;
    }
}