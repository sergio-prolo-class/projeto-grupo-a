import java.util.Scanner;
import java.util.Random;

public class Tabuleiro {

    static final char AGUA = '.';  // Estamos definindo um constante para preencher a matriz
    static final int[] TAMANHO_NAVIO = {5,4,3,2,1}; // definimos um array para armazenar o valor de tamanho de cada navio
    static final char[] NAVIO = {'P','E','C','S','A'}; // definimos o nome de cada navio respectivo com o tamanho.
    static final int TAMANHO = 10;
    

    private static char[][] geraTabuleiro(){
        char[][] tabuleiro = new char [TAMANHO][TAMANHO]; // Criamos a matriz e definimos o tamnho dela como 10 x 10

        for (int i = 0; i < TAMANHO; i++) {
            for ( int j =0; j < TAMANHO; j++){
                tabuleiro [i][j] = AGUA;                  //definimos tudo com agua, deixando vazio
            }    
        }
        return tabuleiro;                                 //retornamos o valor para o metodo estatico como sendo a matriz com agua
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
                posicionado = true;
            }
        }
    }

    private static boolean verificaPosicao(char[][] tabuleiro, int linha, int coluna, int tamanho, boolean horizontal) {

        if (horizontal) {
            if (coluna + tamanho > TAMANHO) return false;

            for (int j = coluna; j < coluna + tamanho; j++) {
                if (tabuleiro[linha][j] != AGUA) return false;
            }

        } else {
            if (linha + tamanho > TAMANHO) return false;

            for (int i = linha; i < linha + tamanho; i++) {
                if (tabuleiro[i][coluna] != AGUA) return false;
            }
        }

        return true;
    }


    public static void main (String[] args){
        Random random = new Random();
        char[][] tabuleiro = geraTabuleiro();

        posicionaNavio(tabuleiro,'P', 5, random);
        posicionaNavio(tabuleiro,'E', 4, random);
        posicionaNavio(tabuleiro,'C', 3, random);
        posicionaNavio(tabuleiro,'S', 2, random);
        posicionaNavio(tabuleiro,'A', 1, random);

        for (int i = 0; i < 10; i++) {          // Percorre as linhas
            for (int j = 0; j < 10; j++) {      // Percorre as colunas
                System.out.print(tabuleiro[i][j] + " "); // Imprime cada posição
            }
            System.out.println();               // Quebra a linha após cada linha da matriz
        }

    }
}