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
     * componente: componente.
     * Retorna true, caso adicione o componente e os seus componentes
     * associados à caverna, e false, caso o contrário.
     */
    public boolean adicionarComponente(Componente componente) {
        int coordenadas[] = componente.getCoordenadas();
        if (!this.salas[coordenadas[0]][coordenadas[1]].adicionarComponente(componente)) {
            return false;
        }
        int numComponentesAssociados = componente.getNumComponentesAssociados();
        if (numComponentesAssociados > 0) {
            Componente componentesAssociados[] = componente.getComponentesAssociados();
            for (int i = 0; i < numComponentesAssociados; i++) {
                if (!adicionarComponente(componentesAssociados[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * componente: componente.
     * Retira o componente e os seus componentes associados da caverna.
     */
    public void retirarComponente(Componente componente) {
        int coordenadas[] = componente.getCoordenadas();
        int numComponentesAssociados = componente.getNumComponentesAssociados();
        this.salas[coordenadas[0]][coordenadas[1]].retirarComponente(componente);
        if (numComponentesAssociados > 0) {
            Componente componentesAssociados[] = componente.getComponentesAssociados();
            for (int i = 0; i < numComponentesAssociados; i++) {
                retirarComponente(componentesAssociados[i]);
            }
        }
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
        System.out.print(" ");
        for (int j = 0; j < MAX_COLUNAS_CAVERNA; j++) {
            System.out.print(" " + (j + 1));
        }
        System.out.println("");
    }
}