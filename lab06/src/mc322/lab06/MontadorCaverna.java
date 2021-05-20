package mc322.lab06;

public class MontadorCaverna {

    private static int MAX_LINHAS_CAVERNA = 4;
    private static int MAX_COLUNAS_CAVERNA = 4;
    private static int MIN_QUANT_BURACO = 2;
    private static int MAX_QUANT_BURACO = 3;
    private static int MIN_QUANT_WUMPUS = 1;
    private static int MAX_QUANT_WUMPUS = 1;
    private static int MIN_QUANT_OURO = 1;
    private static int MAX_QUANT_OURO = 1;
    private static int[] COORD_INI_HEROI = {0, 0};

    /**
     * path: path para um arquivo com os componentes de uma caverna a montar.
     * Retorna um herói em uma caverna especificada no arquivo, ou null, caso
     * seja uma caverna inválida.
     */
    public static Heroi montarCaverna(String path) {
        String infoComponentes[][] = lerComponentes(path);
        if ((infoComponentes == null) ||
                (infoComponentes.length != MAX_LINHAS_CAVERNA * MAX_COLUNAS_CAVERNA)) {
            return null;
        }
        Caverna caverna = new Caverna();
        Heroi heroi = null;
        Componente componente;
        int quantBuraco = 0;
        int quantWumpus = 0;
        int quantOuro = 0;
        int quantHeroi = 0;
        for (int i = 0; i < infoComponentes.length; i++) {
            switch (infoComponentes[i][1]) {
            case "_":
                break;
            case "B":
                componente = new Buraco(infoComponentes[i]);
                if (!componente.setCaverna(caverna)) {
                    return null;
                }
                quantBuraco++;
                break;
            case "W":
                componente = new Wumpus(infoComponentes[i]);
                if (!componente.setCaverna(caverna)) {
                    return null;
                }
                quantWumpus++;
                break;
            case "O":
                componente = new Ouro(infoComponentes[i]);
                if (!componente.setCaverna(caverna)) {
                    return null;
                }
                quantOuro++;
                break;
            case "P":
                heroi = new Heroi(infoComponentes[i]);
                if (!Posicao.compararCoordenadas(COORD_INI_HEROI, heroi.getCoordenadas())) {
                    return null;
                }
                if (!heroi.setCaverna(caverna)) {
                    return null;
                }
                quantHeroi++;
                break;
            default:
                return null;
            }
        }
        if ((quantBuraco < MIN_QUANT_BURACO) ||
                (quantBuraco > MAX_QUANT_BURACO) ||
                (quantWumpus < MIN_QUANT_WUMPUS) ||
                (quantWumpus > MAX_QUANT_WUMPUS) ||
                (quantOuro < MIN_QUANT_OURO) ||
                (quantOuro > MAX_QUANT_OURO) ||
                (quantHeroi != 1)) {
            return null;
        }
        return heroi;
    }

    /**
     * path: path para um arquivo com os componentes de uma caverna.
     * Retorna um vetor com a informação dos componentes do arquivo.
     */
    private static String[][] lerComponentes(String path) {
        CSVHandling csv = new CSVHandling();
        String infoComponentes[][];
        csv.setDataSource(path);
        infoComponentes = csv.requestCommands();
        return infoComponentes;
    }
}