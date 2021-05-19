package mc322.lab06;

public class Caverna {

    private static int MAX_LINHAS_CAVERNA = 4;
    private static int MAX_COLUNAS_CAVERNA = 4;

    private Sala[][] salas;

    /**
     * Inicializa uma caverna.
     */
    Caverna() {
        this.salas = new Sala[MAX_LINHAS_CAVERNA][MAX_COLUNAS_CAVERNA];
        for (int i = 0; i < MAX_LINHAS_CAVERNA; i++) {
            for (int j = 0; j < MAX_COLUNAS_CAVERNA; j++) {
                this.salas[i][j] = new Sala();
            }
        }
    }

    /**
     * coordenadas: coordenadas de uma sala.
     * Retorna o componente primário da sala.
     */
    public Componente getComponentePrimario(int coordenadas[]) {
        return this.salas[coordenadas[0]][coordenadas[1]].getComponentePrimario();
    }

    /**
     * Imprime a caverna na tela com o eixo de coordenadas.
     */
    public void apresentarCaverna(){
        for (int i = 0; i < MAX_LINHAS_CAVERNA; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < MAX_COLUNAS_CAVERNA; j++) {
                System.out.print(this.salas[i][j].tipoComponentePriotario() + " ");
            }
            System.out.println();
        }
        for (int j = 0; j < MAX_COLUNAS_CAVERNA; j++) {
            System.out.println(" " + (j + 1));
        }
    }

    /**
     * componente: componente a adicionar à caverna.
     * Retorna true, caso adicione o componente à caverna, e false, caso o
     * contrário.
     */
    public boolean adicionarComponente(Componente componente) {
        int coordenadas[] = componente.getCoordenadas();
        return this.salas[coordenadas[0]][coordenadas[1]].adicionarComponente(componente);
    }

    /**
     * componente: componente.
     * Retira o componente da caverna.
     */
    public void retirarComponente(Componente componente) {
        int coordenadas[] = componente.getCoordenadas();
        this.salas[coordenadas[0]][coordenadas[1]].retirarComponente(componente);
    }
}