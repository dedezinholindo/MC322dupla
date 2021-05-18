package mc322.lab06;

public class MontadorCaverna {
    private Caverna caverna;


    public Caverna montarCaverna(String path) {
        String componentes[][] = lerComponentes(path);



    }

    private void mensagemErro(int valor, int valorIdeal, String componete){
        if (valor != valorIdeal){
            System.out.println("Valor do " + componete + " difere do valor ideal.");
        } //encerrar programa?
    }

    //verifica a quantidade de todos os componentes de uma vez (mais eficiente)
    //baseado numa quantidade ideal
    public void verificarComponentes(Caverna cav, int idWumpus, int idOuro, int idBuraco, int idHeroi){
        int wumpus = 0, ouro = 0, buraco = 0, heroi = 0;
        Sala[][] s = cav.getSalas();
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                ArrayList<Componente> c = s[i][j].getComponentes();
                for (int k = 0; k < s[i][j].getNumComponentes(); k++){
                    switch (c.getTipo()){
                        case 'W':
                            wumpus += 1;
                            break;
                        case 'P':
                            heroi += 1;
                            break;
                        case 'O':
                            ouro += 1;
                            break;
                        case 'B':
                            buraco += 1;
                    }
                }
            }
        }
        mensagemErro(heroi, idHeroi, "Heroi");
        mensagemErro(wumpus, idWumpus, "Wumpus");
        mensagemErro(buraco, idBuraco, "Buraco");
        mensagemErro(ouro, idOuro, "Ouro");
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

    //problema: criar as brisas sem ter acesso direto a ela
    public Caverna criarBrisas(Caverna cav){
        Caverna c = cav;
        Sala[][] s = c.getSalas();
        int [] coordenadas;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                for (int k = 0; k < s.getNumComponentes(); k++){
                    if (s[i][j].componentes[k].getTipo() == 'B'){
                        int [] coordenadas = {i, j + 1};
                        if (Posicao.valida(coordenadas)){
                            Componete b = new Brisa(coordenadas);

                        }
                    }
                }
            }
        }
    }

    public Caverna getCaverna() {
        return caverna;
    }

    public void criarFedor(){}
}
