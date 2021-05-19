package mc322.lab06;

public class MontadorCaverna {

    private static int MIN_QUANT_BURACO = 2;
    private static int MAX_QUANT_BURACO = 3;
    private static int MIN_QUANT_WUMPUS = 1;
    private static int MAX_QUANT_WUMPUS = 1;
    private static int MIN_QUANT_OURO = 1;
    private static int MAX_QUANT_OURO = 1;
    private static int[] COORD_INI_HEROI = {1, 1};

    /**
     * path: path para um arquivo com os componentes de uma caverna a montar.
     * Retorna uma caverna com os componentes do arquivo, ou null, caso seja uma
     * caverna inválida.
     */
    public Caverna montarCaverna(String path) {
        Caverna caverna = new Caverna();
        Componente componentes[] = lerComponentes(path);
        if (componentes == null) {
            return null;
        }
        for (int i = 0; i < componentes.length; i++) {
            if (!componentes[i].setCaverna(caverna)) {
                return null;
            }
        }
        return caverna;
    }

    /**
     * path: path para um arquivo com os componentes de uma caverna a montar.
     * Retorna um vetor com os componentes do arquivo, ou null, caso seja uma
     * caverna inválida.
     */
    private Componente[] lerComponentes(String path) {
        CSVHandling csv = new CSVHandling();
        String infoComponentes[][];
        Componente componentes[];
        int quantBuraco = 0;
        int quantWumpus = 0;
        int quantOuro = 0;
        int quantHeroi = 0;
        csv.setDataSource(path);
        infoComponentes = csv.requestCommands();
        componentes = new Componente[infoComponentes.length];
        for (int i = 0; i < componentes.length; i++) {
            switch (infoComponentes[i][1]) {
            case '_':
                componentes[i] = Componente(infoComponentes[i]);
                break;
            case 'B':
                componentes[i] = Buraco(infoComponentes[i]);
                quantBuraco++;
                break;
            case 'W':
                componentes[i] = Wumpus(infoComponentes[i]);
                quantWumpus++;
                break;
            case 'O':
                componentes[i] = Ouro(infoComponentes[i]);
                quantOuro++;
                break;
            case 'P':
                componentes[i] = Heroi(infoComponentes[i]);
                if (!Posicao.compararCoordenadas(COORD_INI_HEROI, componentes[i].getCoordenadas)) {
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
        return componentes;
    }
}
