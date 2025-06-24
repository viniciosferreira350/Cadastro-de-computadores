import java.io.*;

public class Computador {

	char ativo;
	String codComp;
	String marca;
	String modelo;
	String processador;
	int quantMemoria;
	int tamanhoTela;
	int quantEstoque;
	float preco;
	int quantTotalVendida;
	int quantUltimaVenda;
	String dtUltimaVenda;

	public long pesquisarComputador(String _codComp) {
		// metodo para localizar um registro no arquivo em disco
		long posicaoCursorArquivo = 0;
		try {
			RandomAccessFile arqComputador = new RandomAccessFile("COMPUTADOR.DAT", "rw");
			while (true) {
				posicaoCursorArquivo = arqComputador.getFilePointer(); // posicao do inicio do registro no arquivo
				ativo = arqComputador.readChar();
				codComp = arqComputador.readUTF();
				modelo = arqComputador.readUTF();
				processador = arqComputador.readUTF();
				quantMemoria = arqComputador.readInt();
				tamanhoTela = arqComputador.readInt();
				quantEstoque = arqComputador.readInt();
				preco = arqComputador.readFloat();
				quantTotalVendida = arqComputador.readInt();
				quantUltimaVenda = arqComputador.readInt();
				dtUltimaVenda = arqComputador.readUTF();

				if (_codComp.equals(codComp) && ativo == 'S') {
					arqComputador.close();
					return posicaoCursorArquivo;
				}
			}
		} catch (EOFException e) {
			return -1; // registro nao foi encontrado
		} catch (IOException e) {
			System.out.println("Erro na abertura do arquivo  -  programa sera finalizado");
			System.exit(0);
			return -1;
		}
	}

	public void salvarComputador() {
		// metodo para incluir um novo registro no final do arquivo em disco
		try {
			RandomAccessFile arqComputador = new RandomAccessFile("COMPUTADOR.DAT", "rw");
			arqComputador.seek(arqComputador.length()); // posiciona o ponteiro no final do arquivo (EOF)
			arqComputador.writeChar(ativo);
			arqComputador.writeUTF(codComp);
			arqComputador.writeUTF(marca);
			arqComputador.writeUTF(modelo);
			arqComputador.writeUTF(processador);
			arqComputador.writeInt(quantMemoria);
			arqComputador.writeInt(tamanhoTela);
			arqComputador.writeInt(quantEstoque);
			arqComputador.writeFloat(preco);
			arqComputador.writeInt(quantTotalVendida);
			arqComputador.writeInt(quantUltimaVenda);
			arqComputador.writeUTF(dtUltimaVenda);
			arqComputador.close();
			System.out.println("Dados gravados com sucesso !\n");
		} catch (IOException e) {
			System.out.println("Erro na abertura do arquivo  -  programa sera finalizado");
			System.exit(0);
		}
	}

	public void desativarComputador(long _posicao) {
		// metodo para alterar o valor do campo ATIVO para N, tornando assim o registro
		// excluido
		try {
			RandomAccessFile arqComputador = new RandomAccessFile("COMPUTADOR.DAT", "rw");
			arqComputador.seek(_posicao);
			arqComputador.writeChar('N'); // desativar o registro antigo
			arqComputador.close();
		} catch (IOException e) {
			System.out.println("Erro na abertura do arquivo  -  programa sera finalizado");
			System.exit(0);
		}
	}

	// *********************** INCLUSAO *****************************
	public void incluir() {
		String codigoChave;
		char confirmacao;
		long posicaoRegistro;

		do {
			do {
				Main.leia.nextLine();
				System.out.println("\n ***************  INCLUSAO DE COMPUTADORES  ***************** ");
				System.out.print("Digite o Codigo do Computador( FIM para encerrar): ");
				codigoChave = Main.leia.nextLine();
				if (codigoChave.equals("FIM")) {
					break;
				}
				posicaoRegistro = pesquisarComputador(codigoChave);

				if (posicaoRegistro >= 0) {
					System.out.println("Matricula ja cadastrada, digite outro valor\n");
				}
			} while (posicaoRegistro >= 0);

			if (codigoChave.equals("FIM")) {
				break;
			}

			ativo = 'S';
			codComp = codigoChave;
			System.out.print("Digite o nome da marca.........................: ");
			marca = Main.leia.nextLine();
			System.out.print("Digite o nome do modelo.........................: ");
			modelo = Main.leia.nextLine();
			System.out.print("Digite o nome do processador.........................: ");
			processador = Main.leia.nextLine();
			System.out.print("Digite a quantidade de memoria.......: ");
			quantMemoria = Main.leia.nextInt();
			System.out.print("Digite o tamanho da tela.......: ");
			tamanhoTela = Main.leia.nextInt();
			System.out.print("Digite a quantidade em estoque.......: ");
			quantEstoque = Main.leia.nextInt();
			System.out.print("Digite o preco..................: ");
			preco = Main.leia.nextFloat();
			quantTotalVendida = 0;
			quantUltimaVenda = 0;
			dtUltimaVenda = "";

			do {
				System.out.print("\nConfirma a gravacao dos dados (S/N) ? ");
				confirmacao = Main.leia.next().charAt(0);
				if (confirmacao == 'S') {
					salvarComputador();
				}
			} while (confirmacao != 'S' && confirmacao != 'N');

		} while (!codComp.equals("FIM"));
	}

	// ************************ ALTERACAO *****************************
	public void alterar() {
		String codigoChave;
		char confirmacao;
		long posicaoRegistro = 0;
		byte opcao;

		do {
			do {
				Main.leia.nextLine();
				System.out.println("\n ***************  ALTERACAO DE ALUNOS  ***************** ");
				System.out.print("Digite a Matricula do Aluno que deseja alterar( FIM para encerrar ): ");
				codigoChave = Main.leia.nextLine();
				if (codigoChave.equals("FIM")) {
					break;
				}

				posicaoRegistro = pesquisarComputador(codigoChave);
				if (posicaoRegistro == -1) {
					System.out.println("Matricula nao cadastrada no arquivo, digite outro valor\n");
				}
			}while (posicaoRegistro == -1);

			if (codigoChave.equals("FIM")) {
				break;
			}

			ativo = 'S';

			do {
				System.out.println("[ 1 ] Nome da marca............: " + marca);
				System.out.println("[ 2 ] Nome do modelo............: " + modelo);
				System.out.println("[ 3 ] Nome do processador............: " + processador);
				System.out.println("[ 4 ] Quantidade de memoria............: " + quantMemoria);
				System.out.println("[ 5 ] Tamanho da tela............: " + tamanhoTela);
				System.out.println("[ 6 ] Quantidade em estoque............: " + quantEstoque);
				System.out.println("[ 7 ] Preco............: " + preco);
				System.out.println("[ 8 ] Quantidade da ultima venda........: " + quantUltimaVenda);
				System.out.println("[ 9 ] Data da ultima venda..........: " + dtUltimaVenda);

				do{
					System.out.println("Digite o numero do campo que deseja alterar (0 para finalizar as alterações): ");
					opcao = Main.leia.nextByte();
				}while (opcao < 0 || opcao > 4);

				switch (opcao) {
				case 1:
					Main.leia.nextLine();
					System.out.print  ("Digite o novo nome da marca..................: ");
					do{
						marca = Main.leia.nextLine();
						if (consistirMarca(marca)) break;
						else System.out.println("Digite uma marca valida...");
					}while(true);
					break;
				case 2: 
					Main.leia.nextLine();
					System.out.print  ("Digite o novo nome do modelo..................: ");
					modelo = Main.leia.nextLine();
					break;
					
				case 3:
					Main.leia.nextLine();
					System.out.print  ("Digite o novo nome do processador..................: ");
					do{
						processador = Main.leia.nextLine();
						if (consistirProcessador(processador)) break;
						else System.out.println("Digite um processador valido...");
					}while(true);
					break;
				case 4:
					Main.leia.nextLine();
					System.out.println("Digite a nova quantidade de memoria..............: ");
					do{
						quantMemoria = Main.leia.nextInt();
						if (quantMemoria >= 1 && preco <= 32) break;
						else System.out.println("Digite um valor valido para a memoria (entre 1 e 32)...");
					}while(true);
					break;
				case 5:
					Main.leia.nextLine();
					System.out.println("Digite o novo tamanho da tela..................: ");
					do{
						tamanhoTela = Main.leia.nextInt();
						if (consistirTamanhoTela(tamanhoTela)) break;
						else System.out.println("Digite um valor valido para o tamanho da tela...");
					}while(true);
					break;
				case 6:
					Main.leia.nextLine();
					System.out.println("Digite a nova quantidade disponivel no estoque............: ");
					do{
						quantEstoque = Main.leia.nextInt();
						if (quantEstoque > 0) break;
						else System.out.println("Digite um valor maior que zero para a quantidade disponivel no estoque...");
					}while(true);
					break;
				case 7:
					Main.leia.nextLine();
					System.out.println("Digite o novo preco....................: ");
					do{
						preco = Main.leia.nextFloat();
						if (preco >= 1000 && preco <= 20000) break;
						else System.out.println("Digite um valor valido para o preco (entre 1000 e 20000)...");
					}while(true);
					break;
				case 8:
					Main.leia.nextLine();
					System.out.println("Digite a nova quantidade da ultima venda...............: ");
					do{
						quantUltimaVenda = Main.leia.nextInt();
						if(quantUltimaVenda > 0 && quantUltimaVenda <= quantEstoque) break;
						else System.out.println("Digite um valor valido para a quantidade da ultima venda...");
					}while(true);
					break;
				case 9:
					Main.leia.nextLine();
					System.out.println("Digite a nova data da ultima venda.....................: ");
					do{
						dtUltimaVenda = Main.leia.nextLine();
						if(verificarData(codigoChave)) break;
						else System.out.println("Digite um valor valido para a data...");
					}while(true);
					break;				
				System.out.println();
			}while (opcao != 0);  		

			do {
				System.out.print("\nConfirma a alteracao dos dados (S/N) ? ");
				confirmacao = Main.leia.next().charAt(0);
				if (confirmacao == 'S') {
					desativarComputador(posicaoRegistro);
					salvarComputador();
					System.out.println("Dados gravados com sucesso !\n");
				}
			}while (confirmacao != 'S' && confirmacao != 'N');

		}while ( ! codComp.equals("FIM"));
	}

	// ************************ EXCLUSAO *****************************
	public void excluir() {
		String codigoChave;
		char confirmacao;
		long posicaoRegistro = 0;

		do {
			do {
				Main.leia.nextLine();
				System.out.println(" ***************  EXCLUSAO DE COMPUTADORES  ***************** ");
				System.out.print("Digite o codigo do computador que deseja excluir ( FIM para encerrar ): ");
				codigoChave = Main.leia.nextLine();
				if (codigoChave.equals("FIM")) {
					break;
				}

				posicaoRegistro = pesquisarComputador(codigoChave);
				if (posicaoRegistro == -1) {
					System.out.println("Matricula nao cadastrada no arquivo, digite outro valor\n");
				}
			} while (posicaoRegistro == -1);

			if (codigoChave.equals("FIM")) {
				System.out.println("\n ************  PROGRAMA ENCERRADO  ************** \n");
				break;
			}

			System.out.println("Nome da marca......................: " + marca);
			System.out.println("Nome do modelo.....................: " + modelo);
			System.out.println("Nome do processador................: " + processador);
			System.out.println("Quantidade de memoria..............: " + quantMemoria);
			System.out.println("Tamanho da tela....................: " + tamanhoTela);
			System.out.println("Quantidade em estoque..............: " + quantEstoque);
			System.out.println("Preco..............................: " + preco);
			System.out.println("Quantidade total vendida...........: " + quantTotalVendida);
			System.out.println("Quantidade da ultima venda.........: " + quantUltimaVenda);
			System.out.println("Data da ultima venda...............: " + dtUltimaVenda);

			do {
				System.out.print("\nConfirma a exclusao deste computador (S/N)?");
				confirmacao = Main.leia.next().charAt(0);
				if (confirmacao == 'S') {
					desativarComputador(posicaoRegistro);
				}
			} while (confirmacao != 'S' && confirmacao != 'N');

		} while (!codComp.equals("FIM"));
	}

	// ************************ RELATORIO *****************************
	public void relatorio() {
		RandomAccessFile arqComputador;
		byte opcao;
		String codigoChave;
		char sexoAux;
		long posicaoRegistro;

		do {
			do {
				System.out.println(" ***************  RELATORIO DE COMPUTADORES  ***************** ");
				System.out.println(" [1] CONSULTAR APENAS 1 COMPUTADOR ");
				System.out.println(" [2] LISTA DE TODOS OS COMPUTADORES ");
				System.out.println(" [3] LISTA SOMENTE SEXO MASCULINO OU FEMININO");
				System.out.println(" [0] SAIR");
				System.out.print("\nDigite a opcao desejada: ");
				opcao = Main.leia.nextByte();
				if (opcao < 0 || opcao > 3) {
					System.out.println("opcao Invalida, digite novamente.\n");
				}
			} while (opcao < 0 || opcao > 3);

			switch (opcao) {
				case 0:
					System.out.println("\n ************  PROGRAMA ENCERRADO  ************** \n");
					break;

				case 1: // consulta de uma unica matricula
					Main.leia.nextLine(); // limpa buffer de memoria
					System.out.print("Digite a Matriocula do Aluno: ");
					codigoChave = Main.leia.nextLine();

					posicaoRegistro = pesquisarComputador(codigoChave);
					if (posicaoRegistro == -1) {
						System.out.println("Matricula nao cadastrada no arquivo \n");
					} else {
						imprimirCabecalho();
						imprimirAluno();
						System.out.println("\n FIM DE RELATORIO - ENTER para continuar...\n");
						Main.leia.nextLine();
					}

					break;

				case 2: // imprime todos os alunos
					try {
						arqComputador = new RandomAccessFile("COMPUTADOR.DAT", "rw");
						imprimirCabecalho();
						while (true) {
							ativo = arqComputador.readChar();
							matricula = arqComputador.readUTF();
							nomeAluno = arqComputador.readUTF();
							dtNasc = arqComputador.readUTF();
							mensalidade = arqComputador.readFloat();
							sexo = arqComputador.readChar();
							if (ativo == 'S') {
								imprimirAluno();
							}
						}
						// arqComputador.close();
					} catch (EOFException e) {
						System.out.println("\n FIM DE RELATORIO - ENTER para continuar...\n");
						Main.leia.nextLine();
						codigoChave = Main.leia.nextLine();
					} catch (IOException e) {
						System.out.println("Erro na abertura do arquivo - programa sera finalizado");
						System.exit(0);
					}
					break;

				case 3: // imprime alunos do sexo desejado
					do {
						System.out.print("Digite o Sexo desejado (M/F): ");
						sexoAux = Main.leia.next().charAt(0);
						if (sexoAux != 'F' && sexoAux != 'M') {
							System.out.println("Sexo Invalido, digite M ou F");
						}
					} while (sexoAux != 'F' && sexoAux != 'M');

					try {
						arqComputador = new RandomAccessFile("COMPUTADOR.DAT", "rw");
						imprimirCabecalho();
						while (true) {
							ativo = arqComputador.readChar();
							matricula = arqComputador.readUTF();
							nomeAluno = arqComputador.readUTF();
							dtNasc = arqComputador.readUTF();
							mensalidade = arqComputador.readFloat();
							sexo = arqComputador.readChar();

							if (sexoAux == sexo && ativo == 'S') {
								imprimirAluno();
							}
						}
					} catch (EOFException e) {
						System.out.println("\n FIM DE RELATORIO - ENTER para continuar...\n");
						Main.leia.nextLine();
						codigoChave = Main.leia.nextLine();
					} catch (IOException e) {
						System.out.println("Erro na abertura do arquivo - programa sera finalizado");
						System.exit(0);
					}

			}

		} while (opcao != 0);
	}

	public void imprimirCabecalho() {
		System.out.println("-MATRICULA-  -------- NOME ALUNO ----------  --DATA NASC--  -Mensalidade-  -sexo- ");
	}

	public void imprimirAluno() {
		System.out.println(formatarString(matricula, 11) + "  " +
				formatarString(nomeAluno, 30) + "  " +
				formatarString(dtNasc, 13) + "  " +
				formatarString(String.valueOf(mensalidade), 13) + "  " +
				formatarString(Character.toString(sexo), 6));
	}

	public static String formatarString(String texto, int tamanho) {
		// retorna uma string com o numero de caracteres passado como parametro em
		// TAMANHO
		if (texto.length() > tamanho) {
			texto = texto.substring(0, tamanho);
		} else {
			while (texto.length() < tamanho) {
				texto = texto + " ";
			}
		}
		return texto;
	}

	public static boolean consistirMarca(String str) {
		// verifica se a marca digitada consiste com as marcas contidas no banco de
		// dados (vetor global 'marcas' da classe Main)
		for (int i = 0; i < Main.marcas.length; i++) {
			if (str.equalsIgnoreCase(Main.marcas[i])) {
				return true;
			}
		}
		return false;
	}

	public static boolean consistirProcessador(String str) {
		// verifica se o processador digitado consiste com os processadores contidos no
		// banco de dados (vetor global 'processadores' da classe Main)
		for (int i = 0; i < Main.processadores.length; i++) {
			if (str.equalsIgnoreCase(Main.processadores[i])) {
				return true;
			}
		}
		return false;
	}

	public static boolean consistirTamanhoTela(int tam) {
		// verifica se o tamanho de tela digitado consiste com os valores contidos no
		// banco de dados (vetor global 'tamanhos' da classe Main)
		for (int i = 0; i < Main.tamanhos.length; i++) {
			if (tam == Main.tamanhos[i]) {
				return true;
			}
		}
		return false;
	}

	public static boolean verificarData(String data) {
		if (data.charAt(2) != '/' && data.charAt(5) != '/')
			return false;

		int dia = Integer.parseInt(data.substring(0, 2));
		int mes = Integer.parseInt(data.substring(3, 5));
		int ano = Integer.parseInt(data.substring(6));

		if (!(ano <= 2026)) {
			return false;
		}
		if (mes < 1 || mes > 12) {
			return false;
		}

		if (dia < 1)
			return false;

		if (mes == 2) {
			if ((ano % 4 == 0 && ano % 100 != 0) || ano % 400 == 0) {
				if (dia > 29)
					return false;
			} else if (dia > 28)
				return false;
		} else if (dia > Main.calendario[mes]) {
			return false;
		}
		return true;
	}
}
