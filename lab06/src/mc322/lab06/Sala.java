package mc322.lab06;

public class Sala {
    private int numComponentes; //numeros de componentes existentes na sala
    private Componente [] componentes;


    //fazer verificacao de dois objetos proibidos na mesma sala aqui (ele diz que a sala que deve avisar a caverna)
    public int getNumComponentes() {
        return numComponentes;
    }

    public Componente[] getComponentes() {
        return componentes;
    }

    public void setNumComponentes(int numComponentes) {
        this.numComponentes = numComponentes;
    }

    public void setComponentes(Componente[] componentes) {
        this.componentes = componentes;
    }
}