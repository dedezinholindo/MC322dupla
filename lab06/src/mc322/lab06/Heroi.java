package mc322.lab06;

public class Heroi extends Componente {
    
    private String nome;
    private int flechasDisponiveis;
    private boolean flechaEquipada;
    private int ourosColetados;
    private int vidas;
    private Caverna caverna;

    /**
     * coordenadas: coordenadas do heroi.
     * Inicializa um heroi.
     */
    Heroi(int[] coordenadas, int flechasDisponiveis, String nome, int vidas, Caverna caverna) {
        super('P', coordenadas);
        this.nome = nome;
        this.flechasDisponiveis = flechasDisponiveis;
        this.flechaEquipada = false;
        this.ourosColetados = 0;
        this.vidas = vidas;
        this.caverna = caverna;
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

    public Caverna getCaverna() {
        return caverna;
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

    public void setCaverna(Caverna caverna) {
        this.caverna = caverna;
    }

    //assume que o movimento eh permitido
    public void movimentar(int[] destino){
        this.coordenadas = destino;
    }

    public void equiparFlecha(){
        this.flechasDisponiveis -= 1;
        this.flechaEquipada = true;
    }

    public void coletarOuro(){
        this.ourosColetados += 1;
    }
    public void apresentarHeroi(){
        System.out.println("|| Player: " + this.getNome());
        //lembrar de apresentar pontuacao!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        System.out.println("|| Lifes: " + this.getVidas());
        System.out.println("|| Arrows: " + this.getFlechasDisponiveis());
        if (this.getFlechasDisponiveis() != 0){
            System.out.println("||   Equiped: " + (this.isFlechaEquipada()? "yes" : "no"));
        }
        System.out.println("|| Colected Golds: " + this.getOurosColetados());
    }
}