package mc322.lab06;

public class Sala {
    private int numComponentes; //numeros de componentes existentes na sala
    private int [] coordenadas;
    private Componente [] componentes;

    public int getNumComponentes() {
        return numComponentes;
    }

    public int[] getCoordenadas() {
        return coordenadas;
    }

    public Componente[] getComponentes() {
        return componentes;
    }

    public void setNumComponentes(int numComponentes) {
        this.numComponentes = numComponentes;
    }

    public void setCoordenadas(int[] coordenadas) {
        this.coordenadas = coordenadas;
    }

    public void setComponentes(Componente[] componentes) {
        this.componentes = componentes;
    }
}