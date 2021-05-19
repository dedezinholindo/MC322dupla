package mc322.lab06;

import java.util.Scanner;

public class AppMundoWumpus {

    private static int[] COORD_INI_HEROI = {1, 1};

    public static void main(String args[]) {
        Heroi heroi = MontadorCaverna.montarCaverna(args[0]);
        if (heroi == null) {
            System.out.println("Arquivo inválido!");
            return;
        }
        Scanner teclado = new Scanner(System.in);
        String nomeUsuario;
        ControleMundoWumpus controle;
        String comando;
        System.out.print("Nome do player: ");
        nomeUsuario = teclado.nextLine();
        controle = new ControleMundoWumpus(Heroi, nomeUsuario);
        controle.apresentarJogo(); // estado inicial.
        comando = teclado.nextLine();
        while (!controle.isJogoFinalizado()) {
            controle.executarComando(comando);
            controle.apresentarJogo();
            comando = teclado.nextLine();
        }
    }
}