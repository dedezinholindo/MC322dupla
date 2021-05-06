package mc322.lab05;

public class AppDama {
    
    /**
     * pathComandos: path para um arquivo CSV com uma série de comandos.
     * pathEstadoFinal: path para um arquivo CSV onde escrever o estado final
     * do tabuleiro.
     * Retorna um vetor de strings com todos estados do tabuleiro de Jogo de
     * Damas durante um jogo com os dados comandos, imprime na tela esses
     * estados com suas descrições e exporta o estado final para um arquivo.
     * Caso o último comando seja inválido, exporta "erro" e nada mais para o
     * arquivo.
     */
    public static String[] executaJogo(String pathComandos, String pathEstadoFinal) {
        Tabuleiro tabuleiro = new Tabuleiro();
        CSVHandling csv = new CSVHandling();
        String comandos[];
        String estados[];
        boolean comandoValido = true;
        csv.setDataSource(pathComandos);
        comandos = csv.requestCommands();
        estados = new String[comandos.length + 1];
        System.out.println("Tabuleiro inicial:");
        tabuleiro.imprimirTabuleiro();
        estados[0] = tabuleiro.paraString();
        for (int i = 0; i < comandos.length; i++) {
            System.out.println();
            System.out.println("Source: " + comandos[i].substring(0, 2));
            System.out.println("Target: " + comandos[i].substring(3, 5));
            comandoValido = tabuleiro.solicitaMovimento(comandos[i]);
            tabuleiro.imprimirTabuleiro();
            estados[i + 1] = tabuleiro.paraString();
        }
        tabuleiro.exportarArquivo(pathEstadoFinal, comandoValido);
        return estados;
    }

    public static void main(String args[]){
        executaJogo(args[0], args[1]);
    }
}