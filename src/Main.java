import java.util.*;
public class Main {
	static Scanner leia = new Scanner(System.in);
	
	static String[] marcas = {"Dell", "Lenovo", "HP", "Positivo", "Asus", "Apple", "IBM"};
	static String[] processadores = {"Intel Core Ultra 3", "Intel Core Ultra 5", "Intel Core Ultra 7", "Intel Core Ultra 9", "AMD Ryzen 5", "AMD Ryzen 7", "AMD Ryzen 9"};
	static int[] tamanhos = {10, 12, 15, 20, 25, 28};
	static int[] calendario = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	
	public static void main(String[] args) {	
		Computador computador = new Computador();
    	byte opcao = -1;
    	 
    	do {
			do {
    			System.out.println("\n ***************  CADASTRO DE COMPUTADOR  ***************** ");
    			System.out.println(" [1] INCLUIR COMPUTADOR ");
    			System.out.println(" [2] ALTERAR / VENDER COMPUTADOR ");
    			System.out.println(" [3] EXCLUIR COMPUTADOR ");
				System.out.println(" [4] RELATORIOS");
    			System.out.println(" [0] SAIR");
    			System.out.print("\nDigite a opcao desejada: ");
    			opcao = leia.nextByte();
    			if (opcao < 0 || opcao > 4) {
    				System.out.println("opcao Invalida, digite novamente.\n");
    			}
    		}while (opcao < 0 || opcao > 4);
			
			switch (opcao) {
				case 0:
					System.out.println("\n ************  PROGRAMA ENCERRADO  ************** \n");
					break;
				case 1: 
					computador.incluir(); 
					break;
				case 2:
					computador.alterar();
					break;
				case 3: 
					computador.excluir();
					break;
				case 4:
					computador.relatorio();
					break;
			}
    	} while ( opcao != 0 );
    	//leia.close();
	}

}
