package View;

import java.io.IOException;

import javax.swing.JOptionPane;

import controller.ArquivosController;
import controller.IArquivosController;

public class Principal {

	public static void main(String[] args) {

		// Códigos contidos no arquivo CSV - 100-101-102-103-104-105

	IArquivosController arquivosCont = new ArquivosController();
	String nomeArquivo = "C:\\Temp\\arquivo.csv";
	int codigo;
	String nome, email;
	int opcao = 0;
	boolean cadastro = false;

	try {

		while (opcao != 9) {

			opcao = Integer.parseInt(JOptionPane.showInputDialog(
					"1 - Verificar Diretório"+
					"\n2 - Verificar Cadastro"+
						"\n3 - Cadastrar Usuário\n9-Finalizar"));

			switch (opcao) {

			case 1:
		arquivosCont.verificaDirTemp();
			break;

			case 2:
		codigo = Integer.parseInt(JOptionPane.showInputDialog("Insira o código que deseja localizar: "));
					arquivosCont.imprimeCadastro(nomeArquivo, codigo);
					break;

			case 3:
		codigo = Integer.parseInt(JOptionPane.showInputDialog("Insira o código a cadastrar: "));
		nome = JOptionPane.showInputDialog("Insira o nome a cadastrar: ");
		email = JOptionPane.showInputDialog("Insira o e-mail a cadastrar: ");
							arquivosCont.insereCadastro(nomeArquivo, codigo, nome, email);
			break;

			case 9:
		JOptionPane.showMessageDialog(null, "Programa finalizado.");
			break;

			default:
		JOptionPane.showMessageDialog(null, "Opção inválida!");
			break;
			}

		}

	} 

	catch (IOException e) {
			e.printStackTrace();
	}

}

}