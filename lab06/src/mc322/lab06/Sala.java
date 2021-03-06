package mc322.lab06;

public class Sala {

    private static int MAX_NUM_COMPONENTES_NAO_PRIMARIOS = 4; // máximo de 3 brisas e 1 fedor.
    
    private boolean revelada;
    private Componente heroi; // contém o herói, caso presente, ou null, caso o contrário.
    private Componente componentePrimario; // contém um componente primário e não herói, caso presente, ou null, caso o contrário.
    private int numComponentesNaoPrimarios;
    private Componente componentesNaoPrimarios[];

    /**
     * Inicializa um sala.
     */
    Sala() {
        this.revelada = false;
        this.heroi = null;
        this.componentePrimario = null;
        this.numComponentesNaoPrimarios = 0;
        this.componentesNaoPrimarios = new Componente[MAX_NUM_COMPONENTES_NAO_PRIMARIOS];
    }

    /**
     * Retorna o estado de revelação da sala.
     */
    public boolean isRevelada() {
        return this.revelada;
    }

    /**
     * Retorna o herói da sala.
     */
    public Componente getHeroi() {
        return this.heroi;
    }

    /**
     * Retorna o componente primário e não herói da sala.
     */
    public Componente getComponentePrimario() {
        return this.componentePrimario;
    }

    /**
     * Retorna o número de componentes não primários da sala.
     */
    public int getNumComponentesNaoPrimarios() {
        return this.numComponentesNaoPrimarios;
    }

    /**
     * Retorna os componentes não primários da sala.
     */
    public Componente[] getComponentesNaoPrimarios() {
        return this.componentesNaoPrimarios.clone();
    }

    /**
     * Revela a sala.
     */
    private void revelar() {
        this.revelada = true;
    }

    /**
     * heroi: herói.
     * Adiciona o herói à sala.
     */
    private void adicionarHeroi(Componente heroi) {
        this.heroi = heroi;
    }

    /**
     * componentePrimario: componente primário e não herói.
     * Retorna true, caso adicione o componente primário à sala, e false, caso
     * o contrário.
     */
    private boolean adicionarComponentePrimario(Componente componentePrimario) {
        if (this.componentePrimario == null) {
            this.componentePrimario = componentePrimario;
            return true;
        }
        return false;
    }

    /**
     * componenteNaoPrimario: componente não primário.
     * Adiciona o componente não primário à sala.
     */
    private void adicionarComponenteNaoPrimario(Componente componenteNaoPrimario) {
        this.componentesNaoPrimarios[this.numComponentesNaoPrimarios] = componenteNaoPrimario;
        this.numComponentesNaoPrimarios++;
    }

    /**
     * componente: componente (qualquer tipo).
     * Retorna true, caso consiga adicionar o componente à sala, e false, caso
     * o contrário.
     */
    public boolean adicionarComponente(Componente componente) {
        if (componente.getTipo() == 'P') {
            adicionarHeroi(componente);
            revelar();
            return true;
        } else if (componente.isPrimario()) {
            return adicionarComponentePrimario(componente);
        } else {
            adicionarComponenteNaoPrimario(componente);
            return true;
        }
    }

    /**
     * Retira o herói da sala.
     */
    private void retirarHeroi() {
        this.heroi = null;
    }

    /**
     * Retira o componente primário e não herói da sala.
     */
    private void retirarComponentePrimario() {
        this.componentePrimario = null;
    }

    /**
     * tipo: tipo de componente não primário.
     * Retira um componente não primário do dado tipo da sala.
     */
    private void retirarComponenteNaoPrimario(char tipo) {
        for (int i = 0; i < this.numComponentesNaoPrimarios; i++) {
            if (this.componentesNaoPrimarios[i].getTipo() == tipo) {
                this.componentesNaoPrimarios[i] = null;
                this.componentesNaoPrimarios[i] = this.componentesNaoPrimarios[this.numComponentesNaoPrimarios - 1];
                this.componentesNaoPrimarios[this.numComponentesNaoPrimarios - 1] = null;
                this.numComponentesNaoPrimarios--;
            }
        }
    }

    /**
     * componente: componente (qualquer tipo).
     * Retira o componente da sala.
     */
    public void retirarComponente(Componente componente) {
        if (componente.getTipo() == 'P') {
            retirarHeroi();
        } else if (componente.isPrimario()) {
            retirarComponentePrimario();
        } else {
            retirarComponenteNaoPrimario(componente.getTipo());
        }
    }

    /**
     * Retorna o tipo do componente prioritário da sala, ou '-' caso não esteja
     * revelada.
     */
    public char tipoComponentePriotario() {
        if (!this.revelada) { // sala não revelada.
            return '-';
        }
        if (this.componentePrimario != null) { // há componente primário.
            return this.componentePrimario.getTipo();
        }
        if (this.heroi != null) { // há herói.
            return this.heroi.getTipo();
        }
        if (this.numComponentesNaoPrimarios > 0) { // há componente não primário.
            char tipoComponentePriotario = this.componentesNaoPrimarios[0].getTipo();
            if (tipoComponentePriotario != 'f') { // tipo não primário prioritário.
                for (int i = 1; i < this.numComponentesNaoPrimarios; i++) {
                    if (this.componentesNaoPrimarios[i].getTipo() == 'f') {
                        tipoComponentePriotario = 'f';
                    }
                }
            }
            return tipoComponentePriotario;
        }
        return '#'; // sala vazia.
    }
}