# Arquivos Java do Jogo

Para acionar o jogo no terminal, chame o programa *AppMundoWumpus* informando um path para um arquivo CSV de entrada com o estado inicial da caverna. Então, quando pedido, digite o nome de usuário desejado no terminal. O jogo sera apresentado e você deve digitar algum dos comandos permitidos:
* w -> Herói movimenta para a sala acima;
* s -> Herói movimenta para a sala abaixo;
* d -> Herói movimenta para a sala a direita;
* a -> Herói movimenta para a sala a esquerda;
* k -> Herói equipa a flecha;
* c -> Herói captura o ouro;
* q -> O usuário sai do jogo.

[Pasta com os arquivos Java.](https://github.com/gadelhap/MC322/tree/master/lab06/src/mc322/lab06)

# Destaques de Arquitetura

## Delegação de Funções aos Respectivos Objetos e Expansão

```Java
public class Heroi extends Componente {
    ...
    private int flechasDisponiveis;
    private boolean flechaEquipada;
    private int ourosColetados;
    ...
    public boolean equiparFlecha() {
    ...
    public boolean coletarOuro() {
    ...
    public boolean batalharWumpus() {
    ...
    public void movimentar(...) {
    ...
```

O trecho acima demonstra a delegação máxima de informações e funcionalidades relacionadas a um objeto a ele mesmo. Neste exemplo, note que o componente Heroi realiza diversas funções relacionadas e contém informações úteis sobre si mesmo.

Além disso, nesse trecho podemos perceber que o jogo está aberto à expansão, podendo aumentar facilmente o número de flechas disponíveis e o número de ouros coletáveis caso o desenvolvedor queira.

```Java
public abstract class Componente {

    protected char tipo;
    protected int coordenadas[];
    protected int numComponentesAssociados;
    protected Componente componentesAssociados[];
    protected Caverna caverna;
    ...
```

Nesse trecho, é demonstrado que, para expandir adicionando outros componentes, também seria um processo fácil, uma vez que todos os componentes são herdeiros da Classe componentes e só teria que ser acrescentado um novo *char* para seu tipo de componente novo e implementada sua devida classe herdeira de Componente.

## Encapsulamento

```Java
public class Caverna {
    ...
    private Sala[][] salas;
    ...
```

O trecho acima demonstra o encapsulamento do código. Note que a matriz de salas é privada e, como pode ser visto no arquivo Java, não há método que dê acesso direto à matriz ou a qualquer sala. Assim, o encapsulamento não é subvertido.

## Polimorfismo

```Java
Componente componente;
...
componente = new Buraco(infoComponentes[i]);
...
componente = new Wumpus(infoComponentes[i]);
...
componente = new Ouro(infoComponentes[i]);
...
```

O trecho acima demonstra o polimorfismo sendo explorado a fim de forma que a adição de novas classes herdeiras de Componente possam ser adicionadas com facilidade.

## Expansão

```Java
public abstract class Componente {

    protected char tipo;
    protected int coordenadas[];
    protected int numComponentesAssociados;
    protected Componente componentesAssociados[];
    protected Caverna caverna;
    ...
```

Para expandir adicionando outros componentes, também seria um processo bem fácil, uma vez que todos os componentes são filhos da Classe componentes e só teria que acrescentar um novo *char* para seu tipo de componente novo e implementar sua devida classe herdando a classe Componente.