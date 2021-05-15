package mc322.lab06;

public class Heroi extends Componente {
    private String nome;
    //quando mostrar essas informacoes abaixo mostrar quantos tem atualemte e o total(Ex: 2/5)
    private int flechasDisponiveis; //deixar em aberto caso expanda o jogo e queira mais flechas
    private boolean flechaEquipada; //se equipar na proxima sala ele perde uma flecha automaticamente
    private int ourosColetados;
    private int vidas; //qtd de vidas que ele possui (perde se cair no buraco ou Wumpus matar)

    /**
     * coordenadas: coordenadas do heroi.
     * Inicializa um heroi.
     */
    Heroi(int[] coordenadas) {
        super('P', coordenadas);
    }

    /**
     * heroi: heroi na forma ["i:j", "t"], em que i é a linha, j, a
     * coluna e t o tipo do heroi, 'P'.
     * Inicializa um heroi.
     */
    Heroi(String[] heroi) {
        this(Posicao.coordenadasParaInt(heroi[0]));
    }

    //heroi avisa a caverna quais movimentos foram feitos

    public String getNome() {
        return nome;
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

    public void apresentarHeroi(){
        System.out.println("|| Player: " + this.getNome());
        System.out.println("|| Score: " + this.getScore());
        System.out.println("|| Lifes: " + this.getVidas());
        System.out.println("|| Arrows: " + this.getFlechasDisponiveis());
        if (this.getFlechasDisponiveis() != 0){
            System.out.println("||   Equiped: " + (this.isFlechaEquipada()? "yes" : "no"));
        }
        System.out.println("|| Colected Golds: " + this.getOurosColetados());
    }
}