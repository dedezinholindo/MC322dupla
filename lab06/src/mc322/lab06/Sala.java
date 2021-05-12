package mc322.lab06;

public class Sala {
    private boolean visitada; //booleano pra se a sala foi visitada
    private int numComponentes; //numeros de componentes existentes na sala
    private Componente [] componentes;


    //fazer verificacao de dois objetos proibidos na mesma sala aqui (ele diz que a sala que deve avisar a caverna)
    Sala(){

    }

    public boolean isVisitada() {
        return visitada;
    }

    public int getNumComponentes() {
        return numComponentes;
    }

    public Componente[] getComponentes() {
        return componentes;
    }

    public void setVisitada(boolean visitada) {
        this.visitada = visitada;
    }

    public void setNumComponentes(int numComponentes) {
        this.numComponentes = numComponentes;
    }

    public void setComponentes(Componente[] componentes) {
        this.componentes = componentes;
    }
}