  package mc322.lab06;

public abstract class Componente {
    protected char tipo; /* '#' para vazio, 'P' para herói, 'W' para Wumpus,
                            'B' para buraco, 'O' para ouro, 'f' para fedor e 'b'  para brisa. */
    protected int coordenadas[]; //indice 0 eh a linha e 1 a coluna

    /**
     * tipo: tipo do componente.
     * coordenadas: coordenadas do componente.
     * Inicializa um componente.
     */
    Componente(char tipo, int[] coordenadas) {
        this.tipo = tipo;
        this.coordenadas = coordenadas;
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

    public char getTipo() {
        return tipo;
    }

    public int[] getCoordenadas() {
        return coordenadas;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public void setCoordenadas(int[] coordenadas) {
        this.coordenadas = coordenadas;
    }

    public void setCaverna(Caverna caverna) {
        
    }

    /**
     * Retorna true, caso o componente seja primário, e false, caso contrário.
     */
    public boolean isPrimario() {
        return (this.tipo == 'W' || this.tipo == 'B' || this.tipo == 'O');
}