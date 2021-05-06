package mc322.lab05;

public class Tabuleiro {
    
    private Peca[][] pecas;

    /**
     * Inicializa um tabuleiro.
     */
    Tabuleiro() {
        this.pecas = new Peca[8][8];
        for (int i = 0; i < 3; i++) {
            if (i == 0 || i == 2) {
                for (int j = 0; j < 8; j += 2) {
                    this.pecas[i][j] = new Peao('b', i, j);
                    this.pecas[i][j + 1] = new Peca('-', i, j + 1);
                }
            } else {
                for (int j = 0; j < 8; j += 2) {
                    this.pecas[i][j] = new Peca('-', i, j);
                    this.pecas[i][j + 1] = new Peao('b', i, j + 1);
                }
            }
        }
        for (int i = 3; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                this.pecas[i][j] = new Peca('-', i, j);
            }
        }
        for (int i = 5; i < 8; i++) {
            if (i == 5 || i == 7) {
                for (int j = 0; j < 8; j += 2) {
                    this.pecas[i][j] = new Peca('-', i, j);
                    this.pecas[i][j + 1] = new Peao('p', i, j + 1);
                }
            } else {
                for (int j = 0; j < 8; j += 2) {
                    this.pecas[i][j] = new Peao('p', i, j);
                    this.pecas[i][j + 1] = new Peca('-', i, j + 1);
                }
            }
        }
    }

    /**
     * srcI: linha da source.
     * srcJ: coluna da source.
     * tgtI: linha do target.
     * tgtJ: coluna do target.
     * Retorna um vetor com as peças de um trajeto entre as posições source e
     * target no tabuleiro, sendo a peça imediatamente após a source o primeiro
     * item do vetor e a peça no target, o último.
     */
    private Peca[] determinarTrajeto(int srcI, int srcJ, int tgtI, int tgtJ) {
        Peca trajeto[];
        int tamanho = (tgtI != srcI) ? (Math.abs(tgtI - srcI)) : (Math.abs(tgtJ - srcJ)); /* calcula o tamanho de acordo com o tipo de trajeto: diagonal, vertical ou horizontal */
        trajeto = new Peca[tamanho];
        int incrementoI = (tgtI - srcI) / (tamanho);
        int incrementoJ = (tgtJ - srcJ) / (tamanho);
        int i = srcI + incrementoI;
        int j = srcJ + incrementoJ;
        for (int ponto = 0; ponto < tamanho; ponto++) {
            if (Posicao.valida(i, j)) {
                trajeto[ponto] = this.pecas[i][j];
            } else {
                trajeto[ponto] = null;
            }            
            i += incrementoI;
            j += incrementoJ;
        }
        return trajeto;
    }

    /**
     * peca: uma peça.
     * Transforma a peça em uma dama, caso esteja no lado oposto do tabuleiro.
     */
    private void transformarEmDama(Peca peca) {
        int linha = peca.getLinha();
        int coluna = peca.getColuna();
        char tipo = peca.getTipo();
        if (tipo == 'b' && linha == 7) {
            this.pecas[linha][coluna] = new Dama('B', linha, coluna);
        } else if (tipo == 'p' && linha == 0) {
            this.pecas[linha][coluna] = new Dama('P', linha, coluna);
        }
    }

    /**
     * comando: string no formato sJsI:tJtI, em que sJsI é a coluna e a linha
     * da posição inicial e tJtI, da posição final.
     * Retorna true e atualiza o tabuleiro de acordo com um comando caso este
     * seja válido, e retorna false caso haja um comando inválido.
     */
    public boolean solicitaMovimento(String comando) {
        int srcI = Posicao.linhaCharParaInteiro(comando.charAt(1));
        int srcJ = Posicao.colunaCharParaInteiro(comando.charAt(0));
        int tgtI = Posicao.linhaCharParaInteiro(comando.charAt(4));
        int tgtJ = Posicao.colunaCharParaInteiro(comando.charAt(3));
        Peca trajeto[] = determinarTrajeto(srcI, srcJ, tgtI, tgtJ);
        int pecaCapturada[] = this.pecas[srcI][srcJ].movimentoValido(trajeto);
        if (pecaCapturada != null) {
            char tipoSrc = this.pecas[srcI][srcJ].getTipo();
            this.pecas[srcI][srcJ] = new Peca('-', srcI, srcJ);
            if (pecaCapturada.length == 2) {
                this.pecas[pecaCapturada[0]][pecaCapturada[1]] = new Peca('-', pecaCapturada[0], pecaCapturada[1]);
                System.out.println("Uma peça foi capturada!");
            }
            if (tipoSrc == 'b' || tipoSrc == 'p') {
                this.pecas[tgtI][tgtJ] = new Peao(tipoSrc, tgtI, tgtJ);
                transformarEmDama(this.pecas[tgtI][tgtJ]);
            } else {
                this.pecas[tgtI][tgtJ] = new Dama(tipoSrc, tgtI, tgtJ);
            }
            return true;
        } else {
            System.out.println("Movimento inválido!");
            return false;
        }
    }

    /**
     * path: caminho para um arquivo CSV.
     * erro: true caso haja erro, false caso contrário.
     * Caso não haja erro, escreve no arquivo as casas do tabuleiro, uma a cada
     * linha, coluna por coluna. As casas são as posições ji seguidas da
     * respectiva peça: vazia '_', peça branca 'b' ou peça preta 'p'. Caso haja
     * erro, escreve no arquivo "erro".
     */
    public void exportarArquivo(String path, boolean erro) {
        CSVHandling csv = new CSVHandling();
        csv.setDataExport(path);
        String tabuleiro[];
        if (!erro) {
            tabuleiro = new String[8 * 8];
            char coluna, linha;
            for (int j = 0; j < 8; j++) {
                coluna = Posicao.colunaInteiroParaChar(j);
                for (int i = 0; i < 8; i++) {
                    linha = Posicao.linhaInteiroParaChar(i);
                    tabuleiro[j * 8 + i] = "";
                    tabuleiro[j * 8 + i] += coluna + linha;
                    if (this.pecas[i][j].getTipo() == '-') {
                        tabuleiro[j * 8 + i] += '_';
                    } else {
                        tabuleiro[j * 8 + i] += Character.toLowerCase(this.pecas[i][j].getTipo());
                    }
                    tabuleiro[j * 8 + i] += '\n';
                }
            }
        } else {
            tabuleiro = new String[1];
            tabuleiro[0] = "erro";
        }
        csv.exportState(tabuleiro);
    }

    /**
     * Imprime o tabuleiro com o eixo de coordenadas na tela.
     */
    public void imprimirTabuleiro() {
        for (int i = 7; i >= 0; i--) {
            System.out.print(i + 1);
            for (int j = 0; j < 8; j++) {
                System.out.print(" " + pecas[i][j].getTipo());
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }
}