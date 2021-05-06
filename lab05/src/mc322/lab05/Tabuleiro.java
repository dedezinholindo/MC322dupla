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
     * Retorna o tabuleiro em forma de string com os tipos das peças, linha por
     * linha pulando uma linha para cada linha.
     */
    public String paraString() {
        String tabuleiro = "";
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                tabuleiro += this.pecas[i][j].getTipo();
            }
            tabuleiro += '\n';
        }
        return tabuleiro;
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

    /**
     * path: caminho para um arquivo CSV de saída.
     * valido: true caso não haja erro, false caso contrário.
     * Caso não haja erro, escreve no arquivo as casas do tabuleiro, uma a cada
     * linha, coluna por coluna. As casas são as posições ji seguidas do tipo
     * da respectiva peça, mas peça vazia é denotada por '_'. Caso haja erro,
     * escreve "erro" no arquivo.
     */
    public void exportarArquivo(String path, boolean valido) {
        CSVHandling csv = new CSVHandling();
        csv.setDataExport(path);
        String tabuleiro[];
        if (valido) {
            tabuleiro = new String[8 * 8];
            char coluna, linha;
            for (int j = 0; j < 8; j++) {
                coluna = Posicao.colunaInteiroParaChar(j);
                for (int i = 0; i < 8; i++) {
                    linha = Posicao.linhaInteiroParaChar(i);
                    tabuleiro[j * 8 + i] = "";
                    tabuleiro[j * 8 + i] += coluna;
                    tabuleiro[j * 8 + i] += linha;
                    tabuleiro[j * 8 + i] += (this.pecas[i][j].getTipo() == '-') ? '_' : this.pecas[i][j].getTipo();
                }
            }
        } else {
            tabuleiro = new String[] {"erro"};
        }
        csv.exportState(tabuleiro);
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
        if (tamanho == 0) {
            return trajeto;
        }
        int incrementoI = (tgtI - srcI) / (tamanho);
        int incrementoJ = (tgtJ - srcJ) / (tamanho);
        int i = srcI + incrementoI;
        int j = srcJ + incrementoJ;
        for (int ponto = 0; ponto < tamanho; ponto++) {
            trajeto[ponto] = (Posicao.valida(i, j)) ? this.pecas[i][j] : null;         
            i += incrementoI;
            j += incrementoJ;
        }
        return trajeto;
    }

    /**
     * comando: string no formato sJsI:tJtI, em que sJsI é a coluna e a linha
     * da posição inicial e tJtI, da posição final.
     * Retorna true e atualiza o tabuleiro de acordo com um comando caso este
     * seja válido, e retorna false caso o comando seja inválido.
     */
    public boolean solicitaMovimento(String comando) {
        int srcI = Posicao.linhaCharParaInteiro(comando.charAt(1));
        int srcJ = Posicao.colunaCharParaInteiro(comando.charAt(0));
        int tgtI = Posicao.linhaCharParaInteiro(comando.charAt(4));
        int tgtJ = Posicao.colunaCharParaInteiro(comando.charAt(3));
        if (!Posicao.valida(srcI, srcJ)) { //source fora do tabuleiro.
            System.out.println("Movimento inválido!");
            return false;
        }
        Peca trajeto[] = determinarTrajeto(srcI, srcJ, tgtI, tgtJ);
        int posPecaCapturada[] = this.pecas[srcI][srcJ].movimentoValido(trajeto);
        if (posPecaCapturada != null) {
            char tipoSrc = this.pecas[srcI][srcJ].getTipo();
            this.pecas[srcI][srcJ] = new Peca('-', srcI, srcJ);
            if (posPecaCapturada.length != 0) {
                this.pecas[posPecaCapturada[0]][posPecaCapturada[1]] = new Peca('-', posPecaCapturada[0], posPecaCapturada[1]);
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
}