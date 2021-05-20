package mc322.lab06;

import java.util.Scanner;

public class AppMundoWumpus {

    public static void main(String args[]) {
        Heroi heroi = MontadorCaverna.montarCaverna(args[0]);
        if (heroi == null) {
            System.out.println("Arquivo inválido!");
            return; // sai do programa.
        }
        Scanner teclado = new Scanner(System.in);
        String nomeUsuario;
        ControleMundoWumpus controle;
        String comando;
        System.out.print("Nome do player: ");
        nomeUsuario = teclado.nextLine();
        controle = new ControleMundoWumpus(heroi, nomeUsuario);
        controle.apresentarJogo(); // estado inicial.
        while (!controle.isJogoFinalizado()) {
            System.out.println("");
            comando = teclado.nextLine();
            System.out.println("");
            controle.executarComando(comando);
            controle.apresentarJogo();
        }
        teclado.close();
    }
}