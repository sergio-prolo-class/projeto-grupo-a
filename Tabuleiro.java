import java.util.Scanner;
import java.util.Random;

public class Tabuleiro {

    static final char AGUA = '.';  // Estamos definindo um constante para preencher a matriz
    static final int[] TAMANHO_NAVIO = {5,4,3,3,2}; // definimos um array para armazenar o valor de tamanho de cada navio
    static final char[] NAVIO = {'P','E','C','S','N'}; // definimos o nome de cada navio respectivo com o tamanho.
    static final int TAMANHO = 10;
    
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
                if (tabuleiro[i][coluna] != AGUA) return false;
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
                System.out.print(tabuleiro[i][j]);
                if (j < TAMANHO - 1) System.out.print(" ");
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

    

    public static void main (String[] args){

        Scanner sc = new Scanner(System.in); // Inicializa o Scanner, mas a principio não vamos utilizar
        
        if (args.length == 0) { // se não tiver o argumento na inicialização, ele dá as instruções
            System.out.println("Uso: java BatalhaNaval <modo>");
            System.out.println("  G - Gerar tabuleiro aleatório");
            System.out.println("  V - Validar tabuleiro lido da entrada");
            System.exit(1);
        }

        char modo = args[0].toUpperCase().charAt(0); // guarda o argumento na variavael modo, do tipo char
        
        if (modo == 'G') { // se for G, gera o tabuleiro
            gerarTabuleiro();
        } else if (modo == 'V') { // se for V, verifica se o tabuleiro é válido
            System.out.println("Modo verificar tabuleiro ainda não implementado");
        } else {
            System.out.println("Modo inválido: " + modo); // aqui indica que o modo não é válido, caso não seja nenhuma daquelas letras
            System.exit(1);
        }



    }
}