package mc322.lab06;

public class Posicao {

    private static int MAX_LINHAS_CAVERNA = 4;
    private static int MAX_COLUNAS_CAVERNA = 4;

    /**
     * coordenada: coordenada em forma de char, partindo de '1'.
     * Retorna a coordenada em forma de int partindo de 0.
     */
    public static int charParaInt(char coordenada) {
        return ((int)coordenada - (int)('1'));
    }

    /**
     * coordenada: coordenada em forma de int, partindo de 0.
     * Retorna a coordenada em forma de char, partindo de '1'.
     */
    public static char intParaChar(int coordenada) {
        return ((char)(coordenada + (int)('1')));
    }

    /**
     * coordenadas: coordenadas na forma "i:j", em que i é a linha e j, a
     * coluna.
     * Retorna um vetor com as coordenadas linha e coluna, respectivamente.
     */
    public static int[] coordenadasParaInt(String coordenadas) {
        return (new int[] {charParaInt(coordenadas.charAt(0)), charParaInt(coordenadas.charAt(2))});
    }

    /**
     * coordenadas: coordenadas.
     * Retorna true, caso as coordenadas são dentro da caverna, e false, caso
     * o contrário.
     */
    public static boolean valida(int[] coordenadas) {
        return (((coordenadas[0] >= 0) && (coordenadas[0] < MAX_LINHAS_CAVERNA)) &&
                    ((coordenadas[1] >= 0) && (coordenadas[1] < MAX_COLUNAS_CAVERNA)));
    }

    /**
     * coordenadasA: coordenadas.
     * coordenadasB: coordenadas.
     * Retorna true, caso ambas coordenadas sejam iguais, e false, caso o
     * contrário.
     */
    public static boolean compararCoordenadas(int[] coordenadasA, int[] coordenadasB) {
        return ((coordenadasA[0] == coordenadasB[0]) && (coordenadasA[1] == coordenadasB[1]));
    }
}