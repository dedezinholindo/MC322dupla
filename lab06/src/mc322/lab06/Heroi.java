package mc322.lab06;

import java.util.Random;

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
     * Retorna true, caso equipe uma flecha disponível, e false, caso o contrário.
     */
    public boolean equiparFlecha() {
        if (this.flechasDisponiveis > 0) {
            this.flechasDisponiveis--;
            this.flechaEquipada = true;
            return true;
        }
        return false;
    }

    /**
     * Desequipa a flecha.
     */
    public void dispararFlecha() {
        this.flechaEquipada = false;
    }

    /**
     * Retorna true, caso o herói colete o ouro, e false, caso não haja ouro.
     */
    public boolean coletarOuro() {
        Componente ouro = this.caverna.getComponentePrimario(this.coordenadas);
        if (ouro == null) {
            return false;
        }
        if (ouro.getTipo() == 'O') {
            this.caverna.retirarComponente(ouro);
            this.ourosColetados++;
            return true;
        }
        return false;
    }

    /**
     * Retorna true, caso o herói vença o Wumpus, e false, caso o contrário.
     */
    public boolean batalharWumpus() {
        if (this.flechaEquipada) {
            Random gerador = new Random();
            return gerador.nextBoolean(); // probabilidade de 50% de vencer o Wumpus.
        } else {
            return false;
        }
    }

    /**
     * Retorna o código do estado atualizado do herói de acordo com a sala em
     * que está: 0, caso ocorra nada, 1, caso derrote um Wumpus; 2, caso seja
     * derrotado por um Wumpus; 3, caso caia em um buraco; e 4, caso encontre
     * ouro.
     */
    public int atualizarEstado() {
        Componente componentePrimario = this.caverna.getComponentePrimario(this.coordenadas);
        if (componentePrimario == null) {
            return 0;
        } else if (componentePrimario.getTipo() == 'W') {
            return (batalharWumpus()) ? 1 : 2;
        } else if (componentePrimario.getTipo() == 'B') {
            return 3;
        } else { // ouro.
            return 4;
        }
    }

    /**
     * coordenadasDestino: coordenadas do destino do herói.
     * Movimenta o herói para o destino.
     */
    public void movimentar(int[] coordenadasDestino){
        this.caverna.retirarComponente(this);
        this.coordenadas = coordenadasDestino;
        this.caverna.adicionarComponente(this);
    }

    public void informacoesHeroi(){
        System.out.println("Flechas: " + this.flechasDisponiveis);
        System.out.println("Ouros coletados: " + this.ourosColetados);
    }
}