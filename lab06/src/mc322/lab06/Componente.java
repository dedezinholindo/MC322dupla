  package mc322.lab06;

public abstract class Componente {
    protected char tipo; /* '#' para vazio, 'P' para herói, 'W' para Wumpus,
                            'B' para buraco, 'O' para ouro, 'f' para fedor e 'b'  para brisa. */
    protected int coordenadas[]; //indice 0 eh a linha e 1 a coluna
    protected Caverna caverna;
    
    /**
     * tipo: tipo do componente.
     * coordenadas: coordenadas do componente.
     * Inicializa um componente.
     */
    Componente(char tipo, int[] coordenadas) {
        this.tipo = tipo;
        this.coordenadas = coordenadas;
        this.caverna = null;
    }

    /**
     * componente: componente na forma ["i:j", "t"], em que i é a linha, j, a
     * coluna e t o tipo do componente, sendo '_' o tipo vazio.
     * Inicializa um componente.
     */
    Componente(String[] componente) {
        this((componente[1].charAt(0) == '_') ? '#' : componente[1].charAt(0),
               Posicao.coordenadasParaInt(componente[0]));
    }

    /**
     * Retorna o tipo do componente.
     */
    public char getTipo() {
        return tipo;
    }

    /**
     * Retorna as coordenadas do componente.
     */
    public int[] getCoordenadas() {
        return coordenadas;
    }

    /**
     * coordenadas: coordenadas para o componente.
     * Altera as coordenadas do componente.
     */
    public void setCoordenadas(int[] coordenadas) {
        this.coordenadas = coordenadas;
    }

    /**
     * caverna: caverna a conectar ao componente.
     * Retorna true, caso adicione o componente e os seus componentes
     * associados à caverna, e false, caso o contrário.
     */
    public boolean setCaverna(Caverna caverna) {
        if (caverna.adicionarComponente(this)) {
            this.caverna = caverna;
            return true;
        }
        return false;
    }

    /**
     * Retorna true, caso o componente seja primário, e false, caso contrário.
     */
    public boolean isPrimario() {
        return (this.tipo == 'W' || this.tipo == 'B' || this.tipo == 'O');
    }
}