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
     * Retorna a posição da peça capturada caso haja captura, um vetor vazio
     * caso não haja captura e null caso seja um movimento inválido.
     */
    public int[] movimentoValido(Peca[] trajeto) {
        if (trajeto.length == 0) { //movimento para o source.
            return null;
        }
        if ((trajeto.length == 2 && trajeto[0].getTipo() == '-') ||trajeto.length > 2) { //movimento excessivo.
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
        if (trajeto.length == 1) {
            if (this.tipo == 'b' && this.linha > trajeto[trajeto.length - 1].getLinha()) { //movimento simples para trás por peão branco.
                return null;
            } else if (this.tipo == 'p' && this.linha < trajeto[trajeto.length - 1].getLinha()) { //movimento simples para trás por peão preto.
                return null;
            }
        }
        if (trajeto.length == 1) { //movimento simples.
            int posPecaCapturada[] = {};
            return posPecaCapturada;
        }
        if (this.tipo != Character.toLowerCase(trajeto[0].getTipo())) { //movimento com captura.
            int posPecaCapturada[] = {trajeto[0].getLinha(), trajeto[0].getColuna()};
            return posPecaCapturada;
        }
        return null; //captura de peça da mesma cor.
    }

}