package mc322.lab06;

public class MontadorCaverna {

    public Caverna montarCaverna(String path) {
        String componentes[][] = lerComponentes(path);


    }
    //verifica a quantidade de todos os componentes de uma vez (mais eficiente)
    public void verificarComponentes(){
        int wumpus, ouro, buraco, heroi;
        //percorrer todas as salas e seu vetor de elementos e ir acrescentando e ver se a qtd bate
    }

    /**
     * path: path para um arquivo com os componentes de uma caverna a montar.
     * Retorna um vetor com os componentes.
     */
    public Componentes[] lerComponentes(String path) {
        CSVHandling csv = new CSVHandling();
        String infoComponentes[][];
        Componentes componentes[];
        csv.setDataSource(path);
        infoComponentes = csv.requestCommands();
        componentes = new Componente[infoComponentes.length];
        for (int i = 0; i < componentes.length; i++) {
            switch (infoComponentes[i][1]) {
            case '_':
                componentes[i] = Componente(infoComponentes[i]);
                break;
            case 'W':
                componentes[i] = Wumpus(infoComponentes[i]);
                break;
            case 'B':
                componentes[i] = Buraco(infoComponentes[i]);
                break;
            case 'O':
                componentes[i] = Ouro(infoComponentes[i]);
                break;
            case 'P':
                componentes[i] = Heroi(infoComponentes[i]);
                break;
            default:
                return null;
            }
        }
        return componentes;
    }

    public void criarBrisas(){}

    public void criarFedor(){}
}
