package mc322.lab06;

public class ControleMundoWumpus {
    private int pontuacao;
    private Heroi heroi;
    //realiza o movimento do heroi
    //1-verificar tecla digitada
    //2-verificar se o jogador nao esta indo para fora dos limites

    //coordenadas iniciais do heroi (1,1)
    ControleMundoWumpus(int[] coordenadas, int flechasDisponiveis, String nome, int vidas){
        this.pontuacao = 0;
        this.heroi = new Heroi(coordenadas, flechasDisponiveis, nome, vidas);
    }
    /**
     * Retorna a pontuação atual do jogo.
     */
    public int getPontuacao() {
        return pontuacao;
    }

    public Heroi getHeroi() {
        return heroi;
    }
    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public void setHeroi(Heroi heroi){
        this.heroi = heroi;
    }

    //retorna pra sala a nova posicao do heroi
    public boolean movimentarDireita(){
        int [] posicao = heroi.getCoordenadas();
        posicao[0] += 1;
        if (Posicao.valida(posicao)){
            this.heroi.movimentar(posicao);
            return true
        }
        return false;
    }

    public boolean movimentarEsquerda(){
        int [] posicao = heroi.getCoordenadas();
        posicao[0] -= 1;
        if (Posicao.valida(posicao)){
            this.heroi.movimentar(posicao);
            return true
        }
        return false;
    }

    public boolean movimentarCima(){
        int [] posicao = heroi.getCoordenadas();
        posicao[1] -= 1;
        if (Posicao.valida(posicao)){
            this.heroi.movimentar(posicao);
            return true
        }
        return false;
    }

    public boolean movimentarBaixo(){
        int [] posicao = heroi.getCoordenadas();
        posicao[1] += 1;
        if (Posicao.valida(posicao)){
            this.heroi.movimentar(posicao);
            return true
        }
        return false;
    }

    //alertar se nao tiver mais disponiveis
    //soltar sempre na rodada seguinte
    public boolean equiparFlecha(); //fazer

    private int[] localizarWumpus(Caverna caverna){
        Sala[4][4] salas = caverna.getSalas();
        Componente wumpus;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                Componente c = sala[i][j].getComponentePrimario();
                if (c != null){
                    if (c.getTipo() == 'W'){
                        wumpus = c;
                        break; //pois so tem um wumpus
                    }
                }
            }
        }
        return wumpus.getCoordenadas();
    }

    private void eliminarWumpus(int[] localizacaoWumpus){
        Caverna cav = heroi.getCaverna();
        Sala[4][4] salas = caverna.getSalas();
        boolean var = salas[localizacaoWumpus[0]][localizacaoWumpus[1]].setComponentePrimario(null);
        cav.setSalas(salas);
        heroi.setCaverna(cav); //caverna sem o wumpus
    }

    //verificar se matou o Wumpus
    public void dispararFlecha(){
        if (heroi.isFlechaEquipada){
            heroi.setFlechaEquipada(false);
            System.out.println("Uma flecha foi disparada!");
            int[] localizacaoWumpus = localizarWumpus(heroi.getCaverna);
            if (Posicao.compararCoordenadas(heroi.getCoordenadas(), localizacaoWumpus)){

                System.out.println("PARABÉNS! Você exterminou o Wumpus!!!");
            }
            else {
                System.out.println("Flecha disparada no imenso e triste vazio ;(");
            }
        }
    }

    //fazer verificacao se na sala tem ou nao ouro (como fazer o link com a caverna?)
    public boolean pegarOuro(){
        this.heroi.coletarOuro();
    }

    public void sairJogo(){
        System.out.println("Usuário desistiu do jogo: ");
        //apresentar informacoes do heroi atuais
        System.exit(0);
    }

    public comandosTeclado(char c){
        boolean verificador;
        switch (c){
            case 'w':
                verificador = movimentarCima();
                if(!verificador){
                    System.out.println("Não foi possível movimentar para cima, você ja está no limite superior da caverna!");
                }
                break;
            case 's':
                verificador = movimentarBaixo();
                if(!verificador){
                    System.out.println("Não foi possível movimentar para baixo, você ja está no limite inferior da caverna!");
                }
                break;
            case 'd':
                verificador = movimentarDireita();
                if(!verificador){
                    System.out.println("Não foi possível movimentar para direita, você ja está no limite da caverna!");
                }
                break;
            case 'a':
                verificador = movimentarEsquerda();
                if(!verificador){
                    System.out.println("Não foi possível movimentar para esquerda, você ja está no limite da caverna!");
                }
                break;
            case 'k':
                verificador = equiparFlecha();
                if(!verificador){
                    System.out.println("Não há mais flechas disponíveis!");
                }
                break;
            case 'c':
                verificador = pegarOuro();
                if(!verificador){
                    System.out.println("Não há ouro nesta sala!");
                }
                break;
            case 'q':
                sairJogo();
                break;
        }
        dispararFlecha(); //dispara uma flecha sempre que esta esta equipada
    }
    /**
     * incremento: incremento para a pontuação, pode ser positivo ou negativo.
     * Incrementa a pontuação do jogo.
     */
    public void incrementarPontuacao(int incremento) {
        this.pontuacao += incremento;
    }

    public void apresentarJogo(){
        Caverna cav = this.heroi.getCaverna();
        cav.apresentarCaverna();
        this.heroi.apresentarHeroi();
        System.out.println("|| Score: " + this.pontuacao);
    }
}