package mc322.lab06;

public class Caverna {
    private qtdOuros, qtdWumpus; //se heroi ourosColetados = qtdOuros ele pode sair
    private Sala[][] salas;

    Caverna(){
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
        for(int i = 1; i <= 4; i++){
            System.out.print(i + " ");
            for (int j = 1; j <= 4; j++){
                Componente c = Posicao.preferenciaDeImpressao(salas[i - 1][j - 1]);
                System.out.print(c.getTipo() + " ");
            }
            System.out.println();
        }
        System.out.println(" 1 2 3 4");
    }
    //apresentar o heroi na classe heroi
}