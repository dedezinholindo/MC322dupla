package mc322.lab06;

public class ControleMundoWumpus {
    private int pontuacao;
    //realiza o movimento do heroi
    //1-verificar tecla digitada
    //2-verificar se o jogador nao esta indo para fora dos limites
    
    /**
     * Retorna a pontuação atual do jogo.
     */
    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    /**
     * incremento: incremento para a pontuação, pode ser positivo ou negativo.
     * Incrementa a pontuação do jogo.
     */
    public void incrementarPontuacao(int incremento) {
        this.pontuacao += incremento;
    }
}