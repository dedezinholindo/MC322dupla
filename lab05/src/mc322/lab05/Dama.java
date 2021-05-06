package mc322.lab05;

public class Dama extends Peca {

    /**
     * tipo: tipo da dama.
     * linha: linha da dama.
     * coluna: coluna da dama.
     * Inicializa uma dama.
     */
    Dama(char tipo, int linha, int coluna) {
        super(tipo, linha, coluna);
    }

    /**
     * trajeto: vetor com as peças do trajeto, em que a primeira é a peça
     * imediatamente após a source e a última é a peça no target.
     * Retorna a quantidade de peças não vazias no trajeto.
     */
    private int pecasIntermediarias(Peca[] trajeto) {
        int n = 0;
        for (int ponto = 0; ponto < trajeto.length - 1; ponto++) {
            if (trajeto[ponto].getTipo() != '-') {
                n++;
            }
        }
        return n;
    }

    /**
     * trajeto: vetor com as peças do trajeto, em que a primeira é a peça
     * imediatamente após a source e a última é a peça no target.
     * Retorna a posição da peça capturada caso haja captura, um vetor vazio
     * caso não haja captura e null caso seja um movimento inválido.
     */
    public int[] movimentoValido(Peca[] trajeto) {
        if (trajeto.length == 0) { //movimento para o source.
            return null;
        }
        if (trajeto[trajeto.length - 1] == null) { //target fora do tabuleiro.
            return null;
        }
        if (this.linha == trajeto[trajeto.length - 1].getLinha() || this.coluna == trajeto[trajeto.length - 1].getColuna()) { //movimento horizontal ou vertical.
            return null;
        }
        if (trajeto[trajeto.length - 1].getTipo() != '-') { //target ocupado.
            return null;
        }
        int n = pecasIntermediarias(trajeto);
        if (n > 1) { //pulando mais de uma peça.
            return null;
        }
        if (n == 0) { //movimento simples.
            int posPecaCapturada[] = {};
            return posPecaCapturada;
        }
        if (trajeto[trajeto.length - 2].getTipo() == '-') { //peça capturada antes da posição imediatamente antes do target.
            return null;
        }
        if (Character.toLowerCase(this.tipo) != Character.toLowerCase(trajeto[trajeto.length - 2].getTipo())) { //movimento com captura.
            int posPecaCapturada[] = {trajeto[trajeto.length - 2].getLinha(), trajeto[trajeto.length - 2].getColuna()};
            return posPecaCapturada;
        }
        return null; //captura de peça da mesma cor.
    }
}