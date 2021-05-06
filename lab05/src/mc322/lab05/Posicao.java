package mc322.lab05;

public class Posicao {

    /**
     * coluna: coluna em forma de char, partindo de 'a'.
     * Retorna a coluna em forma de int, partindo de 0.
     */
    public static int colunaCharParaInteiro(char coluna) {
        return (int)coluna - (int)('a');
    }

    /**
     * coluna: coluna em forma de int, partindo de 0.
     * Retorna a coluna em forma de char, partindo de 'a'.
     */
    public static char colunaInteiroParaChar(int coluna) {
        return (char)(coluna + (int)('a'));
    }
    
    /**
     * linha: linha em forma de char, partindo de '1'.
     * Retorna a linha em forma de int partindo de 0.
     */
    public static int linhaCharParaInteiro(char linha) {
        return (int)linha - (int)('1');
    }

    /**
     * linha: linha em forma de int, partindo de 0.
     * Retorna a linha em forma de char, partindo de '1'.
     */
    public static char linhaInteiroParaChar(int linha) {
        return (char)(linha + (int)('1'));
    }

    /**
     * coluna: coluna.
     * linha: linha.
     * Retorna true se a posição é dentro do tabuleiro, e false, caso contrário.
     */
    public static boolean valida(int linha, int coluna) {
        if ((linha < 0 || linha > 7) || (coluna < 0 || coluna > 7)) {
            return false;
        } else {
            return true;
        }
    }
}