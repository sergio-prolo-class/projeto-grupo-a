import java.util.Scanner;
import java.util.Random;

public class Tabuleiro {
    // CONSTANTES
    static final char AGUA = '.';  // Estamos definindo um constante para preencher a matriz
    static final int[] TAMANHO_NAVIO = {5,4,3,3,2}; // definimos um array para armazenar o valor de tamanho de cada navio
    static final char[] NAVIO = {'P','E','C','S','N'}; // definimos o nome de cada navio respectivo com o tamanho.
    static final int TAMANHO = 10;
    
    // Primeira parte: gerar tabuleiro aleatório
    private static boolean verificaPosicao(char[][] tabuleiro, int linha, int coluna, int tamanho, boolean horizontal) {

        if (horizontal) { // faz a verificação se é horizontal
            if (coluna + tamanho > TAMANHO) { // se a soma do tamanho do navio com a coluna, for maior que o tamanho do tabuleiro (10), ele retorna falso, indicando que não cabe
                return false;
            }

            for (int j = coluna; j < coluna + tamanho; j++) { // loop para verificar se o quadradinho está vazio
                if (tabuleiro[linha][j] != AGUA) return false;
            }

        } else { // faz a verificação se é vertical

            if (linha + tamanho > TAMANHO) { // se a soma do tamanho do navio com a linha, for maior que o tamanho do tabuleiro (10), ele retorna falso, indicando que não cabe
                return false;
            }

            for (int i = linha; i < linha + tamanho; i++) { // loop para verificar se o quadradinho está vazio
                if (tabuleiro[i][coluna] != AGUA) {
                    return false; // retorna falso se o que encontrar for diferente de água
                }
            }
        }

        return true;
    }

    private static void posicionaNavio(char[][] tabuleiro, char simbolo, int tamanho, Random random) {
        boolean posicionado = false; // verifica se o navio foi posicionado, inicializa em 'false' pra indicar que ele não ta posicionado

        while(!posicionado) { // loop de posicionamento
            boolean horizontal = random.nextBoolean(); // booleano pra definir se será horizontalmente ou verticalmente, sendo true pra horizontal e false pra vertical
            int linha = random.nextInt(TAMANHO); // 
            int coluna = random.nextInt(TAMANHO);

            if (verificaPosicao(tabuleiro, linha, coluna, tamanho, horizontal)) {
                if (horizontal) { // se foi denifido horizontal, ele entra aqui
                    for (int j = coluna; j < coluna + tamanho; j++) {
                        tabuleiro[linha][j] = simbolo;
                    }
                } else { // se não, ele faz o loop para ser na vertical
                    for (int i = linha; i < linha + tamanho; i++) {
                        tabuleiro[i][coluna] = simbolo;
                    }
                }
                posicionado = true; // defini a variavel como true, depois que for posicionado para sair do loop
            }
        }
    }

    private static void imprimirTabuleiro(char[][] tabuleiro) { // recebe a matriz pra imprimir
        for (int i = 0; i < TAMANHO; i++) { // percorre as linhas 
            for (int j = 0; j < TAMANHO; j++) {  // percorre as colunas
                System.out.print(tabuleiro[i][j]); // imprime a linha X coluna 
                if (j < TAMANHO - 1) {
                    System.out.print(" "); // imprime um espaço depois do j, enquanto ele for menor que o TAMANHO
                }
            }
            System.out.println();
        }
    }

    private static void gerarTabuleiro(){
        char[][] tabuleiro = new char [TAMANHO][TAMANHO]; // criamos a matriz e definimos o tamnho dela como 10 x 10

        for (int i = 0; i < TAMANHO; i++) {

            for ( int j =0; j < TAMANHO; j++){
                tabuleiro [i][j] = AGUA;                  //definimos tudo com agua, deixando vazio
            }    
        }

        Random random = new Random(); // cria um objeto random

        for (int i = 0; i < NAVIO.length; i++) {
            posicionaNavio(tabuleiro, NAVIO[i], TAMANHO_NAVIO[i], random); //chamamos o metodo para posicionar o navio, passando a matriz, o simbolo do navio, o tamanho e o random
        } 
        
        imprimirTabuleiro(tabuleiro); // chama o método que imprime o tabuleiro
    }

    // Segunda parte: verificar tabuleiro lido da entrada --------------------------------------------------------------------------------------------

    public static void validarSimbolo(char[][] tabuleiro) { // recebe o tabuleiro montado no método validarTabuleiro (que já verificou se o tamanho é válido), para verificar os símbolos

        for (int i = 0; i < TAMANHO; i++) {

            for (int j = 0; j < TAMANHO; j++) {

                char simbolo = tabuleiro[i][j]; // percorre todo o tabuleiro pra verificar se tem algum elemento inválido
                
                if (simbolo != 'P' && simbolo != 'E' && simbolo != 'C' && 
                    simbolo != 'S' && simbolo != 'N' && simbolo != AGUA) { // se algum dos caracteres não for o que é permitido, ele vai dar a mensagem e encerrar o programa
                    
                    System.out.println("Tabuleiro INVÁLIDO: navio desconhecido, representado pela letra " + simbolo);
                    System.exit(0); // pra o programa encerrar sem verificar os outros métodos
                }
            }
        }

        //System.out.println("Tabuleiro com símbolos válidos");
    }

    // Conta um navio contínuo a partir de (linha,coluna) e garante que é só H ou só V
    private static int contarNavioContínuo(char[][] tabuleiro, int linha, int coluna, char tipo, boolean[][] visitado) {
        int tamanho = 0;

        // Verifica vizinhos imediatos
        boolean temDireita  = (coluna + 1 < TAMANHO && tabuleiro[linha][coluna + 1] == tipo);
        boolean temEsquerda = (coluna - 1 >= 0      && tabuleiro[linha][coluna - 1] == tipo);
        boolean temBaixo    = (linha  + 1 < TAMANHO && tabuleiro[linha + 1][coluna] == tipo);
        boolean temCima     = (linha  - 1 >= 0      && tabuleiro[linha - 1][coluna] == tipo);

        // Se tem vizinho na horizontal E na vertical, é navio em L/diagonal
        if ((temDireita || temEsquerda) && (temBaixo || temCima)) {
            System.out.println("Tabuleiro INVÁLIDO: navio " + tipo + " não está totalmente na horizontal ou vertical");
            System.exit(0);
        }

        // Caso 1: navio horizontal
        if (temDireita || temEsquerda) {
            int col = coluna;

            // anda para a direita
            while (col < TAMANHO && tabuleiro[linha][col] == tipo) {
                visitado[linha][col] = true;
                tamanho++;
                col++;
            }

            // anda para a esquerda
            col = coluna - 1;
            while (col >= 0 && tabuleiro[linha][col] == tipo) {
                visitado[linha][col] = true;
                tamanho++;
                col--;
            }

            return tamanho;
        }

        // Caso 2: navio vertical
        if (temBaixo || temCima) {
            int lin = linha;

            // anda para baixo
            while (lin < TAMANHO && tabuleiro[lin][coluna] == tipo) {
                visitado[lin][coluna] = true;
                tamanho++;
                lin++;
            }

            // anda para cima
            lin = linha - 1;
            while (lin >= 0 && tabuleiro[lin][coluna] == tipo) {
                visitado[lin][coluna] = true;
                tamanho++;
                lin--;
            }

            return tamanho;
        }

        // Caso 3: célula isolada (navio de tamanho 1)
        visitado[linha][coluna] = true;
        return 1;
    }


    private static int pegaIndiceNavio(char tipo) {
        for (int k = 0; k < NAVIO.length; k++) {
            if (NAVIO[k] == tipo) return k;
        }
        return -1;
    }

    private static void validarTamanhoNavios(char[][] tabuleiro) {
        boolean[][] visitado = new boolean[TAMANHO][TAMANHO];

        int[] qtdNavios    = new int[NAVIO.length];     // quantos navios de cada tipo existem
        int[] somaTamanhos = new int[NAVIO.length];     // soma dos tamanhos (opcional, para conferência)

        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                if (tabuleiro[i][j] != AGUA && !visitado[i][j]) {

                    char tipo = tabuleiro[i][j];
                    int indice = pegaIndiceNavio(tipo);
                    if (indice == -1) continue; // segurança

                    int tamanho = contarNavioContínuo(tabuleiro, i, j, tipo, visitado);

                    // conta este navio
                    qtdNavios[indice]++;

                    // tamanho deste navio deve ser exatamente o esperado
                    if (tamanho != TAMANHO_NAVIO[indice]) {
                        System.out.printf("Tabuleiro INVÁLIDO: navio %c com tamanho incorreto (%d em vez de %d)%n",
                                        tipo, tamanho, TAMANHO_NAVIO[indice]);
                        System.exit(0);
                    }

                    somaTamanhos[indice] += tamanho;
                }
            }
        }

        // agora garante que existe exatamente 1 navio de cada tipo
        for (int k = 0; k < NAVIO.length; k++) {
            if (qtdNavios[k] != 1) {
                if (qtdNavios[k] == 0) {
                    System.out.printf("Tabuleiro INVÁLIDO: navio %c não encontrado%n", NAVIO[k]);
                } else {
                    System.out.printf("Tabuleiro INVÁLIDO: múltiplos navios do tipo '%c'%n", NAVIO[k]);
                }
                System.exit(0);
            }
        }

        //System.out.println("Quantidade correta de navios (1 de cada tipo, todos com tamanho correto)");
    }




    private static void validarTabuleiro(Scanner sc) {
        char [][] tabuleiroRecebido = new char [TAMANHO][TAMANHO];

        for (int i=0; i < TAMANHO; i++){
            if (!sc.hasNextLine()) { // verifica se tem menos linhas que o TAMANHO
                System.out.println("Tabuleiro INVÁLIDO: dimensões incorretas");
                System.exit(0);
            }
            
            String linha = sc.nextLine(); // pega a próxima linha

            String[] partes = linha.split(" ");

            if (partes.length != TAMANHO){
                System.out.println("Tabuleiro INVÁLIDO: dimensões incorretas");
                System.exit(0);
            }

            for (int j=0; j < TAMANHO; j++){
                tabuleiroRecebido[i][j] = partes[j].charAt(0);
            }
        }
        if (sc.hasNextLine()) {
            System.out.println("Tabuleiro INVÁLIDO: dimensões incorretas");
            System.exit(0);
        }
        //System.out.println("Dimensões válidas (10x10)");

        validarSimbolo(tabuleiroRecebido);
        validarTamanhoNavios(tabuleiroRecebido);

    }

    // Método main, aonde inicializa e começa o programa
    public static void main (String[] args){

        Scanner sc = new Scanner(System.in);
        
        if (args.length == 0) { // se não tiver o argumento na inicialização, ele dá as instruções
            System.out.println("Uso: java BatalhaNaval <modo>");
            System.out.println("  G - Gerar tabuleiro aleatório");
            System.out.println("  V - Validar tabuleiro lido da entrada");
            return;
        }

        char modo = args[0].toUpperCase().charAt(0); // guarda o argumento na variavael modo, do tipo char
        
        if (modo == 'G') { // se for G, gera o tabuleiro
            gerarTabuleiro();
        } else if (modo == 'V') { // se for V, verifica se o tabuleiro é válido
            validarTabuleiro(sc);
            System.out.println("Tabuleiro VÁLIDO ");
        } else {
            System.out.println("Modo inválido: " + modo); // aqui indica que o modo não é válido, caso não seja nenhuma daquelas letras
            return;
        }

        sc.close();

    }
}