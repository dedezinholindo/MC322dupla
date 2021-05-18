package mc322.lab06;

public class Posicao {

    /**
     * coordenada: coordenada em forma de char, partindo de '1'.
     * Retorna a coordenada em forma de int partindo de 0.
     */
    public static int charParaInt(char coordenada) {
        return (int)coordenada - (int)('1');
    }

    /**
     * coordenada: coordenada em forma de int, partindo de 0.
     * Retorna a coordenada em forma de char, partindo de '1'.
     */
    public static char intParaChar(int coordenada) {
        return (char)(coordenada + (int)('1'));
    }

    /**
     * coordenadas: coordenadas na forma "i:j", em que i é a linha e j, a
     * coluna.
     * Retorna um vetor com a linha e a coluna das coordenadas,
     * respectivamente.
     */
    public static int[] coordenadasParaInt(String coordenadas) {
        return new int[] {charParaInt(coordenadas.charAt(0)), charParaInt(coordenadas.charAt(2))};
    }

    /**
     * coordenadas: coordenadas.
     * Retorna true se as coordenadas são dentro da caverna, e false, caso
     * contrário.
     */
    public static boolean valida(int[] coordenadas) {
        return ((coordenadas[0] >= 0 && coordenadas[0] < 4) && (coordenadas[1] >= 0 && coordenadas[1] < 4));
    }

    public boolean comapararCoordenadas(int[] a, int[] b){
        return (a[0] == b[0] && a[1] == b[1]);
    }

    //retornara quem tem preferencia de impressao em determinadda sala
    public static char preferenciaDeImpressao(Sala s){
        char retorno = null;
        for (int i = 0; i < (s.getComponentes()).length; i++){
            char c = ((s.getComponentes())[i]).getTipo();
            if (c == 'O' | c == 'W' | c == 'B' ){
                return c;
            }
            if (c == 'P'){
                retorno = c;
            } else if (c == 'f' && (retorno == null)){
                retorno = c;
            } else if (retorno == null && (c == 'b')){
                retorno = c;
            }
        }
        if (s.getComponentes()).length == 0 && (s.isVisitada()){
            return '#';
        } else if (s.getComponentes()).length == 0 && !(s.isVisitada()){
            return '-';
        }
        return retorno;
    }

}