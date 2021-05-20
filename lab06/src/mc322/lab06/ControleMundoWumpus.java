package mc322.lab06;

public class ControleMundoWumpus {

    private static int INCREMENTO_SAIDA_COM_OURO = 1000;
    private static int INCREMENTO_MORTE = -1000;
    private static int INCREMENTO_MOVIMENTO = -15;
    private static int INCREMENTO_USO_FLECHA = -100;
    private static int INCREMENTO_VITORIA_WUMPUS = 500;
    private static int[] COORD_INI_HEROI = {0, 0};

    private boolean jogoFinalizado;
    private int pontuacao;
    private Heroi heroi;
    private String nomeUsuario;

    /**
     * heroi: herói do jogo.
     * nomeUsuario: nome do usuário.
     * Inicializa um controle para o jogo Mundo de Wumpus.
     */
    ControleMundoWumpus(Heroi heroi, String nomeUsuario) {
        this.jogoFinalizado = false;
        this.pontuacao = 0;
        this.heroi = heroi;
        this.nomeUsuario = nomeUsuario;
    }

    /**
     * Retorna o estado de finalização do jogo.
     */
    public boolean isJogoFinalizado() {
        return this.jogoFinalizado;
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
     * Finaliza o jogo.
     */
    private void finalizarJogo(boolean morte) {
        if (morte) {
            System.out.println("Você morreu!");
        } else if (this.heroi.getOurosColetados() > 0 && Posicao.compararCoordenadas(heroi.getCoordenadas(), COORD_INI_HEROI)) { // sai pela sala inicial.
            incrementarPontuacao(INCREMENTO_SAIDA_COM_OURO);
            System.out.println("Você venceu!:)))");
        } else {
            System.out.println("Volte sempre!");
        }
        this.jogoFinalizado = true;
    }
    /**
     * incremento: incremento para a pontuação, pode ser positivo ou negativo.
     * Incrementa a pontuação do jogo.
     */
    private void incrementarPontuacao(int incremento) {
        this.pontuacao += incremento;
    }

    /**
     * Movimenta o herói para cima.
     */
    private boolean movimentarCima() {
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
    private boolean movimentarBaixo() {
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
    private boolean movimentarDireita() {
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
    private boolean movimentarEsquerda() {
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
    public void executarComando(String comando) {
        if (this.jogoFinalizado) {
            return;
        }
        boolean flechaEquipadaAgora = false;
        switch (comando) {
            case "w":
                if (movimentarCima()) {
                    incrementarPontuacao(INCREMENTO_MOVIMENTO);
                } else {
                    System.out.println("Não há sala acima!");
                }
                break;
            case "s":
                if (movimentarBaixo()) {
                    incrementarPontuacao(INCREMENTO_MOVIMENTO);
                } else {
                    System.out.println("Não há sala abaixo!");
                }
                break;
            case "d":
                if (movimentarDireita()) {
                    incrementarPontuacao(INCREMENTO_MOVIMENTO);
                } else {
                    System.out.println("Não há sala à direita!");
                }
                break;
            case "a":
                if (movimentarEsquerda()) {
                    incrementarPontuacao(INCREMENTO_MOVIMENTO);
                } else {
                    System.out.println("Não há sala à esquerda!");
                }
                break;
            case "k":
                if (!this.heroi.equiparFlecha()) {
                    System.out.println("Não há flechas disponíveis!");
                } else {
                    flechaEquipadaAgora = true;
                    System.out.println("Equipou uma flecha.");
                }
                break;
            case "c":
                if (!this.heroi.coletarOuro()) {
                    System.out.println("Não há ouro nesta sala!");
                }
                break;
            case "q":
                finalizarJogo(false);
                break;
            default:
                System.out.println("Comando inválido!");
                break;
        }
        int estado = this.heroi.atualizarEstado();
        if (estado == 1) {
                System.out.println("Você derrotou o Wumpus XD!!!");
                incrementarPontuacao(INCREMENTO_VITORIA_WUMPUS);
        } else if (estado == 2) {
                System.out.println("O Wumpus lhe derrotou! ;(");
                incrementarPontuacao(INCREMENTO_MORTE);
                finalizarJogo(true);
        } else if (estado == 3) {
            System.out.println("Você caiu em um buraco! :o");
            incrementarPontuacao(INCREMENTO_MORTE);
            finalizarJogo(true);
        } else if (estado == 4) {
            System.out.println("Há ouro nessa sala! $)");
        }
        if (!flechaEquipadaAgora) {
            if (this.heroi.isFlechaEquipada()) { // dispara uma flecha sempre no comando seguinte ao equipamento.
                this.heroi.dispararFlecha(); 
                incrementarPontuacao(INCREMENTO_USO_FLECHA);
            }
        }
    }

    /**
     * Apresenta o jogo: caverna, nome do usuário, pontuação e informações do herói.
     */
    public void apresentarJogo() {
        Caverna caverna = this.heroi.getCaverna();
        caverna.apresentarCaverna();
        System.out.println("");
        System.out.println("Player: " + this.nomeUsuario);
        System.out.println("Score: " + this.pontuacao);
        System.out.println("Flechas Disponíveis: " + this.heroi.getFlechasDisponiveis());
        System.out.println("Ouros coletados: " + this.heroi.getOurosColetados());
    }
}