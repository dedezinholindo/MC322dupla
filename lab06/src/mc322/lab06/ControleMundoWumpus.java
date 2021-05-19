package mc322.lab06;

public class ControleMundoWumpus {

    private static int INCREMENTO_SAIDA_COM_OURO = 1000;
    private static int INCREMENTO_MORTE = -1000;
    private static int INCREMENTO_MOVIMENTO = -15;
    private static int INCREMENTO_USO_FLECHA = -100;
    private static int INCREMENTO_VITORIA_WUMPUS = 500;

    private int pontuacao;
    private Heroi heroi;
    private String nomeUsuario;
    private boolean jogoFinalizado;

    /**
     * heroi: herói do jogo.
     * nomeUsuario: nome do usuário.
     * Inicializa um controle para o jogo Mundo de Wumpus.
     */
    ControleMundoWumpus(Heroi heroi, String nomeUsuario) {
        this.pontuacao = 0;
        this.heroi = heroi;
        this.nomeUsuario = nomeUsuario;
        this.jogoFinalizado = false;
    }

    /**
     * Retorna a pontuação atual do jogo.
     */
    public int getPontuacao() {
        return pontuacao;
    }

    /**
     * Retorna o nome do usuário.
     */
    public String getNomeUsuario() {
        return this.nomeUsuario;
    }

    /**
     * Retorna o estado de finalização do jogo.
     */
    public boolean isJogoFinalizado() {
        return this.jogoFinalizado;
    }

    /**
     * incremento: incremento para a pontuação, pode ser positivo ou negativo.
     * Incrementa a pontuação do jogo.
     */
    public void incrementarPontuacao(int incremento) {
        this.pontuacao += incremento;
    }

    /**
     * Finaliza o jogo.
     */
    public void finalizarJogo(boolean morte) {
        if (morte) {
            apresentarJogo();
            System.out.println("Você morreu!");
        } else if (this.heroi.getOurosColetados() > 0) {
            incrementarPontuacao(INCREMENTO_SAIDA_COM_OURO);
            apresentarJogo();
            System.out.println("Você venceu!");
        } else {
            apresentarJogo();
            System.out.println("Fim de jogo.");
        }
        this.jogoFinalizado = true;
    }

    /**
     * Movimenta o herói para cima.
     */
    public boolean movimentarCima() {
        int destino[] = heroi.getCoordenadas();
        destino[0]--;
        if (Posicao.valida(destino)) {
            this.heroi.movimentar(destino);
            return true;
        }
        return false;
    }

    /**
     * Movimenta o herói para baixo.
     */
    public boolean movimentarBaixo() {
        int destino[] = heroi.getCoordenadas();
        destino[0]++;
        if (Posicao.valida(destino)) {
            this.heroi.movimentar(destino);
            return true;
        }
        return false;
    }

    /**
     * Movimenta o herói para a direita.
     */
    public boolean movimentarDireita() {
        int destino[] = heroi.getCoordenadas();
        destino[1]++;
        if (Posicao.valida(destino)) {
            this.heroi.movimentar(destino);
            return true;
        }
        return false;
    }

    /**
     * Movimenta o herói para a esquerda.
     */
    public boolean movimentarEsquerda() {
        int destino[] = heroi.getCoordenadas();
        destino[1]--;
        if (Posicao.valida(destino)) {
            this.heroi.movimentar(destino);
            return true;
        }
        return false;
    }

    /**
     * comando: comando de ação.
     * Executa o comando, caso possível.
     */
    public void executarComando(char comando) {
        if (isJogoFinalizado()) {
            apresentarJogo();
            System.out.println("O jogo já foi finalizado!");
            return;
        }
        switch (comando) {
            case 'w':
                if (movimentarCima()) {
                    incrementarPontuacao(INCREMENTO_MOVIMENTO);
                } else {
                    System.out.println("Não há sala acima!");
                }
                break;
            case 's':
                if (movimentarBaixo()) {
                    incrementarPontuacao(INCREMENTO_MOVIMENTO);
                } else {
                    System.out.println("Não há sala abaixo!");
                }
                break;
            case 'd':
                if (movimentarDireita()) {
                    incrementarPontuacao(INCREMENTO_MOVIMENTO);
                } else {
                    System.out.println("Não há sala à direita!");
                }
                break;
            case 'a':
                if (movimentarEsquerda()) {
                    incrementarPontuacao(INCREMENTO_MOVIMENTO);
                } else {
                    System.out.println("Não há sala à esquerda!");
                }
                break;
            case 'k':
                if (!this.heroi.equiparFlecha()) {
                    System.out.println("Não há flechas disponíveis!");
                }
                break;
            case 'c':
                if (!this.heroi.coletarOuro()) {
                    System.out.println("Não há ouro nesta sala!");
                }
                break;
            case 'q':
                finalizarJogo(false);
                break;
            default:
                System.out.println("Comando inválido!");
        }
        int estado = this.heroi.atualizarEstado();
        if (estado == 1) {
                System.out.println("Você derrotou o Wumpus!");
                incrementarPontuacao(INCREMENTO_VITORIA_WUMPUS);
        } else if (estado == 2) {
                System.out.println("O Wumpus lhe derrotou!");
                incrementarPontuacao(INCREMENTO_MORTE);
                finalizarJogo(true);
        } else if (estado == 3) {
            System.out.println("Você caiu em um buraco!");
            incrementarPontuacao(INCREMENTO_MORTE);
            finalizarJogo(true);
        } else if (estado == 4) {
            System.out.println("Há ouro nessa sala!");
        }
        if (this.heroi.isFlechaEquipada()) { // dispara uma flecha sempre que equipada.
            this.heroi.dispararFlecha(); 
            incrementarPontuacao(INCREMENTO_USO_FLECHA);
        }
    }

    /**
     * Apresenta o jogo: caverna, nome do usuário e pontuação.
     */
    public void apresentarJogo() {
        Caverna caverna = this.heroi.getCaverna();
        caverna.apresentarCaverna();
        System.out.println("");
        System.out.println("Player: " + this.nomeUsuario);
        System.out.println("Score: " + this.pontuacao);
    }
}