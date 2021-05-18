package mc322.lab06;

public class Sala {
    private boolean revelada; // indica se a sala está revelada.
    private int numComponentesNaoPrimarios; // número de componentes não primários presentes na sala.
    private static int MAX_NUM_COMPONENTES_NAO_PRIMARIOS = 4; // máximo de 3 brisas e 1 fedor.
    private Componente componentesNaoPrimarios[]; // componentes não primários presentes na sala.
    private Componente componentePrimario; //contém um componente primário e não herói caso presente, ou null, caso o contrário.
    private Heroi heroi; //contém o herói caso esteja presente, ou null, caso o contrário.

    /**
     * Inicializa um sala.
     */
    Sala() {
        this.revelada = false;
        this.numComponentesNaoPrimarios = 0;
        this.componentesNaoPrimarios = new Componente[MAX_NUM_COMPONENTES_NAO_PRIMARIOS];
        this.componentePrimario = null;
        this.heroi = null;
    }

    /**
     * Retorna o estado de revelação da sala.
     */
    public boolean isRevelada() {
        return this.revelada;
    }

    /**
     * Retorna o número de componentes não primários da sala.
     */
    public int getNumComponentes() {
        return this.numComponentesNaoPrimarios;
    }

    /**
     * Retorna os componentes não primários da sala.
     */
    public Componente[] getComponentes() {
        return this.componentesNaoPrimarios;
    }

    /**
     * Retorna o componente primário da sala.
     */
    public Componente getComponentePrimario() {
        return this.componentePrimario;
    }

    /**
     * Retorna o herói da sala.
     */
    public Heroi getHeroi() {
        return this.heroi;
    }

    /**
     * Revela a sala.
     */
    public void revelar() {
        this.revelada = true;
    }

    /**
     * componenteNaoPrimario: componente não primário a adicionar à sala.
     * Adiciona o componente não primário à sala.
     */
    public void adicionarComponenteNaoPrimario(Componente componenteNaoPrimario) {
        this.componentesNaoPrimarios[this.numComponentesNaoPrimarios] = componenteNaoPrimario;
        this.numComponentesNaoPrimarios++;
    }

    /**
     * componentePrimario: componente primário a adicionar à sala.
     * Retorna true se adiciona o componente primário à sala, e false, caso o
     * contrário.
     */
    public boolean setComponentePrimario(Componente componentePrimario) {
        if (this.componentePrimario == null) {
            this.componentePrimario = componentePrimario;
            return true;
        }
        return false;
    }

    /**
     * heroi: herói a adicionar à sala.
     * Adiciona o herói à sala.
     */
    public void setHeroi(Heroi heroi) {
        this.heroi = heroi;
    }

    /**
     * componente: componente a adicionar à sala (qualquer tipo).
     * Retorna true, caso consiga adicionar o componente à sala, e false, caso
     * contrário.
     */
    public boolean adicionarComponente(Componente componente) {
        if (componente.getTipo() == 'P') {
            setHeroi(componente);
            return true;
        } else if (!componente.isPrimario()) {
            adicionarComponenteNaoPrimario(componente);
            return true;
        } else {
            return setComponentePrimario(componente);
        }
    }

    /**
     * tipo: tipo do componente não primário a retirar da sala.
     * Retira um componente não primário da sala.
     */
    public void retirarComponenteNaoPrimario(char tipo) {
        for (int i = 0; i < this.numComponentesNaoPrimarios; i++) {
            if (this.componentesNaoPrimarios[i].getTipo() == tipo) {
                this.componentesNaoPrimarios[i] = null;
                this.componentesNaoPrimarios[i] = this.componentesNaoPrimarios[this.numComponentesNaoPrimarios];
                this.componentesNaoPrimarios[this.numComponentesNaoPrimarios] = null;
                this.numComponentesNaoPrimarios--;
            }
        }
    }

    /**
     * Retira o componente primário da sala.
     */
    public void retirarComponentePrimario() {
        this.componentePrimario = null;
    }

    /**
     * Retira o herói da sala.
     */
    public void retirarHeroi() {
        this.heroi = null;
    }
}