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
        comando = teclado.nextLine();
        while (true) { // sairá do programa com System.exit(0).
            System.out.println("");
            controle.executarComando(comando);
            controle.apresentarJogo();
            comando = teclado.nextLine();
        }
    }
}