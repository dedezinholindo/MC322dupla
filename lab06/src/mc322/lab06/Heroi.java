package mc322.lab06;

public class Heroi extends Componente {
    private String nome;
    private int score;
    //quando mostrar essas informacoes abaixo mostrar quantos tem atualemte e o total(Ex: 2/5)
    private int flechasDisponiveis; //deixar em aberto caso expanda o jogo e queira mais flechas
    private boolean flechaEquipada; //se equipar na proxima sala ele perde uma flecha automaticamente
    private int ourosColetados;
    private int vidas; //qtd de vidas que ele possui (perde se cair no buraco ou Wumpus matar)
    //heroi avisa a caverna quais movimentos foram feitos
    public String getNome() {
        return nome;
    }

    public int getScore() {
        return score;
    }

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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setScore(int score) {
        this.score = score;
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

    public int[] movimentar(int[] destino);//retorna as cordenadas finais para serem trocadas na caverna
    //controle fara isso
}