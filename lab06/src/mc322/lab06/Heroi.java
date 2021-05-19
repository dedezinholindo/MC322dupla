package mc322.lab06;

public class Heroi extends Componente {

    private static int MAX_FLECHAS_INICIAIS = 1;

    private int flechasDisponiveis;
    private boolean flechaEquipada;
    private int ourosColetados;

    /**
     * coordenadas: coordenadas do herói.
     * Inicializa um herói.
     */
    Heroi(int[] coordenadas) {
        super('P', coordenadas);
        this.flechasDisponiveis = MAX_FLECHAS_INICIAIS;
        this.flechaEquipada = false;
        this.ourosColetados = 0;
        this.caverna = null;
    }

    /**
     * heroi: herói na forma ["i:j", "t"], em que i é a linha, j, a
     * coluna e t, o tipo do herói, 'P'.
     * Inicializa um herói.
     */
    Heroi(String[] heroi) {
        this(Posicao.coordenadasParaInt(heroi[0]));
    }

    /**
     * Retorna a quantidade de flechas disponíveis.
     */
    public int getFlechasDisponiveis() {
        return flechasDisponiveis;
    }

    /**
     * Retorna o estado de equipação de flecha.
     */
    public boolean isFlechaEquipada() {
        return flechaEquipada;
    }

    /**
     * Retorna a quantidade de ouros coletados.
     */
    public int getOurosColetados() {
        return ourosColetados;
    }

    /**
     * Equipa uma flecha.
     */
    public void equiparFlecha() {
        if (this.flechasDisponiveis > 0) {
            this.flechasDisponiveis--;
            this.flechaEquipada = true;
        }
    }

    /**
     * coordenadasDestino: coordenadas do destino do herói.
     * Retorna true, caso movimente o herói para o destino, e false, caso o
     * contrário.
     */
    public boolean movimentar(int[] coordenadasDestino){
        if (Posicao.valida(coordenadasDestino)) {
            this.caverna.retirarComponente(this);
            this.coordenadas = coordenadasDestino;
            this.caverna.adicionarComponente(this);
            return true;
        }
        return false;
    }

    public void coletarOuro(){
        this.ourosColetados += 1;
    }
}