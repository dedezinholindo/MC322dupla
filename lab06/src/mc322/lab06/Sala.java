package mc322.lab06;

public class Sala {
    private boolean visitada; //booleano pra se a sala foi visitada
    private int numComponentes; //numeros de componentes existentes na sala
    private ArrayList<Componente> componentes;
    private boolean contemComponentePrimario; /* indica se a sala contém componente primário. */


    //fazer verificacao de dois objetos proibidos na mesma sala aqui (ele diz que a sala que deve avisar a caverna)
    Sala() {
        this.visitada = false;
        this.numComponentes = 0;
        this.componentes = new Componente[4];
    }

    public boolean isVisitada() {
        return visitada;
    }

    public int getNumComponentes() {
        return numComponentes;
    }

    public ArrayList<Componente> getComponentes() {
        return componentes;
    }

    public void setVisitada(boolean visitada) {
        this.visitada = visitada;
    }

    public void setNumComponentes(int numComponentes) {
        this.numComponentes = numComponentes;
    }

    public void setComponentes(ArrayList<Componente> componentes) {
        this.componentes = componentes;
    }

    /**
     * componente: componente a adicionar à sala.
     * Retorna true, caso consiga adicionar o componente à sala, e false, caso
     * contrário.
     */
    public boolean adicionarComponente(Componente componente) {
        if (this.numComponentes == 4 ||
                (this.contemComponentePrimario && componente.isPrimario())) {
            return false;
        }
        this.componentes.add(componente);
        this.numComponentes++;
        if (componente.isPrimario()) {
            this.contemComponentePrimario = true;
        }
        return true;
    }
}