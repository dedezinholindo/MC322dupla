package mc322.lab06;

import java.util.Scanner;

public class AppMundoWumpus {
    
    public static void main(String args[]) {
        Caverna caverna = MontadorCaverna.montarCaverna(args[0]);
        if (caverna == null) {
            System.out.println("Arquivo inválido!")
        }
        Scanner teclado = new Scanner(System.in);
        String comando = teclado.nextLine();
        while () { // enquanto o comando nao é 'q' ou o usuario nao perder ou ganhar o jogo faça isso.
            comando = teclado.nextLine();
        }
}