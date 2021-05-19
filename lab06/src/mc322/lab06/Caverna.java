package mc322.lab06;

public class Caverna {
    private int qtdOuros; //se heroi ourosColetados = qtdOuros ele pode sair
    private Sala[][] salas;

    Caverna() {
        this.salas = new Sala[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Sala[i][j] = new Sala();
            }
        }
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

    public boolean adicionarComponente(Componente componente) {
        int coordenadas[] = componente.getCoordenadas();
        return this.salas[coordenadas[0]][coordenadas[1]].adicionarComponente(componente);
    }
}