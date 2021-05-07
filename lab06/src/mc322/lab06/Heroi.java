package mc322.lab06;

public class Heroi extends Componente {
    private int flechasDisponiveis; //deixar em aberto caso expanda o jogo e queira mais flechas
    private boolean flechaEquipada; //se equipar na proxima sala ele perde uma flecha automaticamente
    private int ourosColetados;
    private int vidas; //qtd de vidas que ele possui (perde se cair no buraco ou Wumpus matar)

    public int getFlechasDisponiveis() {
        return flechasDisponiveis;
    }

    public boolean isFlechaEquipada() {
        return flechaEquipada;
    }

    public int getOurosColetados() {
        return ourosColetados;
    }

    public int getVidas() {
        return vidas;
    }

    public void setFlechasDisponiveis(int flechasDisponiveis) {
        this.flechasDisponiveis = flechasDisponiveis;
    }

    public void setFlechaEquipada(boolean flechaEquipada) {
        this.flechaEquipada = flechaEquipada;
    }

    public void setOurosColetados(int ourosColetados) {
        this.ourosColetados = ourosColetados;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }
}