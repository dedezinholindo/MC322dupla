package mc322.lab06;

public class Caverna {
    private qtdOuros, qtdWumpus; //se heroi ourosColetados = qtdOuros ele pode sair
    private Sala[][] salas;

    Caverna() {
        this.salas = new Sala[4][4];
        //criar salas aqui (ele diz que esse objeto eh responsavel por associar cada componente em sua sala)
    }

    public Sala[] getSalas() {
        return salas;
    }

    public void setSalas(Sala[] salas) {
        this.salas = salas;
    }

    public void apresentarCaverna(){
        for (int i = 0; i < 4; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 4; j++) {
                System.out.print(this.salas[i][j].tipoComponentePriotario() + " ");
            }
            System.out.println();
        }
        System.out.println(" 1 2 3 4");
    }
    //apresentar o heroi na classe heroi
}