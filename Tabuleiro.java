import java.util.Scanner;
import java.util.Random;

public class Tabuleiro {

    static final Random random= new Random();
    static final char AGUA = '.';  // Estamos definindo um constante para preencher a matriz
    static final int[] TAMANHO_NAVIO = {5,4,3,2,1}; // definimos um array para armazenar o valor de tamanho de cada navio
    static final char[] NAVIO = {'P','E','C','S','A'}; // definimos o nome de cada navio respectivo com o tamanho.
    static final int TAMANHO = 10;


    private static char[][] geraTabuleiro(){
        char[][] MATRIZ = new char [TAMANHO][TAMANHO]; // Criamos a matriz e definimos o tamnho dela como 10 x 10
        for (int i = 0; i < TAMANHO; i++) {
            for ( int b =0;b<TAMANHO; b++){
                MATRIZ [i][b] = AGUA;                  //definimos tudo com agua
            }    
        }
        return MATRIZ;                                 //retornamos o valor para o metodo estatico como sendo a matriz com agua

    }

    public static void main (String[] args){
        Scanner sc = new Scanner(System.in); 

        

    
   

    sc.close();
    }
}