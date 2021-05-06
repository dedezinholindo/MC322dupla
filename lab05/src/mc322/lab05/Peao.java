package mc322.lab05;

public class Peao extends Peca {
    
    /**
     * tipo: tipo do peão.
     * linha: linha do peão.
     * coluna: coluna do peão.
     * Inicializa um peão.
     */
    Peao(char tipo, int linha, int coluna) {
        super(tipo, linha, coluna);
    }

    /**
     * trajeto: vetor com as peças do trajeto, em que a primeira é a peça
     * imediatamente após a source e a última é a peça no target.
     * Retorna a posição da peça capturada caso haja captura, um vetor com
     * apenas -1 caso não haja captura e null caso seja um movimento inválido.
     */
    public int[] movimentoValido(Peca[] trajeto) {
        if (trajeto[trajeto.length - 1] == null) { //target fora do tabuleiro.
            return null;
        }
        if (trajeto.length == 0 || trajeto.length > 2) { //movimento para o source ou movimento excessivo.
            return null;
        }
        if (this.linha == trajeto[trajeto.length - 1].getLinha() || this.coluna == trajeto[trajeto.length - 1].getColuna()) { //movimento horizontal ou vertical.
            return null;
        }
        if (trajeto[trajeto.length - 1].getTipo() != '-') { //target ocupado.
            return null;
        }
        if (trajeto.length == 2 && trajeto[0].getTipo() == '-') { //pulando uma casa vazia.
            return null;
        }
        if (trajeto.length == 1) {
            if (this.tipo == 'b' && this.linha > trajeto[trajeto.length - 1].getLinha()) { //movimento simples para trás por peão branco.
                return null;
            } else if (this.tipo == 'p' && this.linha < trajeto[trajeto.length - 1].getLinha()) { //movimento simples para trás por peão preto.
                return null;
            }
        }
        int pecaCapturada[];
        if (trajeto.length == 1) { //movimento simples.
            pecaCapturada = new int[] {-1};
            return pecaCapturada;
        }
        if (!Posicao.avaliarMesmoTipo(trajeto[trajeto.length - 2], this)){ //movimento com captura(sem capturar peca do mesmo tipo)
            pecaCapturada = new int[] {trajeto[0].getLinha(), trajeto[0].getColuna()};
            return pecaCapturada;
        }
        return null;
    }

}
