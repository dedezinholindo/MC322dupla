package mc322.lab06;

public class Heroi extends Componente {
    private int flechasDisponiveis; //deixar em aberto caso expanda o jogo e queira mais flechas
    private int ourosColetados;

    public int getFlechasDisponiveis() {
        return flechasDisponiveis;
    }

    public int getOurosColetados() {
        return ourosColetados;
    }

    public void setFlechasDisponiveis(int flechasDisponiveis) {
        this.flechasDisponiveis = flechasDisponiveis;
    }

    public void setOurosColetados(int ourosColetados) {
        this.ourosColetados = ourosColetados;
    }
}