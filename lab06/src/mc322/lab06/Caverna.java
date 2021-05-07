package mc322.lab06;

public class Caverna {
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
}