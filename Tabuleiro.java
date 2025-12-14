import java.util.Scanner;
import java.util.Random;

public class Tabuleiro {
    Scanner sc = new Scanner(); 
    static final Random random= new Random();
    static final char agua = '.';  // Estamos definindo um constante para preencher a matriz
    static final int[] Tamanho_Navio = {5,4,3,2,1}; // definimos um array para armazenar o valor de tamanho de cada navio
    static final char[] Navio = {'P','E','C','S','A'}; // definimos o nome de cada navio respectivo com o tamanho.
    static final int Tamanho = 10;

    private static char[][] Tabuleiro (){
        char[][] MATRIZ = new char [Tamanho][Tamanho]; // Criamos a matriz e definimos o tamnho dela como 10 x 10
        for (int i = 0; i < Tamanho; i++) {
            for ( int b =0;b<Tamanho; b++){
                MATRIZ [i][b] = agua;                  //definimos tudo com agua
            }    
        }
        return MATRIZ;                                 //retornamos o valor para o metodo estatico como sendo a matriz com agua

    }
}