package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class ArquivosController implements IArquivosController {

@Override
public void verificaDirTemp() throws IOException {
	File path = new File("C:\\TEMP");
	if (path.exists() && path.isDirectory()) {
		JOptionPane.showMessageDialog(null, " Diretório já existente!");
	} 
	else {
		JOptionPane.showMessageDialog(null, "novo Diretório Criado.");
			path.mkdir();
	}

}

@Override
public boolean verificaRegistro(String arquivo, int codigo) throws IOException {
	File arq = new File(arquivo);
	
	if (arq.exists() && arq.isFile()) {
		FileInputStream stream = new FileInputStream(arq);
		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader buffer = new BufferedReader(reader);

		String ln = buffer.readLine();

		while (ln != null) {
			if (ln.contains(Integer.toString(codigo))) {
				return true;
			}
			ln = buffer.readLine();
		}
		
		stream.close();
		reader.close();
		buffer.close();

	}
	return false;
}

@Override
public void imprimeCadastro(String arquivo, int codigo) throws IOException {

	File arq = new File(arquivo);
	String[] content;
	String mostraCadastro = "";
	int i;

	if (verificaCadastro(arquivo, codigo)) {
		FileInputStream stream = new FileInputStream(arq);
		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader buffer = new BufferedReader(reader);

		String ln = buffer.readLine();
			
		while (ln != null) {
			content = ln.split(";");
			for (i = 0; i < content.length; i++) {
				if (Integer.toString(codigo).equals(content[i])) {
					content = ln.split(";");
					for (i = 0; i < content.length; i++) {
						if (i == 0) {
							mostraCadastro += "Código: " + content[i] + "\n";
							} else if (i == 1) {
								mostraCadastro += "Nome: " + content[i] + "\n";
							} else {
								mostraCadastro += "E-mail: " + content[i] + "\n";
							}
						}
						JOptionPane.showMessageDialog(null, "---Dados do Cadastro--- \n" + mostraCadastro);
						break;
					}
				}

				ln = buffer.readLine();
			}
			buffer.close();
			reader.close();
			stream.close();

		} else {
			JOptionPane.showMessageDialog(null, "Cadastro não encontrado");
		}

	}

	@Override
	public void insereCadastro(String arquivo, int codigo, String nome, String email) throws IOException {
		StringBuffer buffer = new StringBuffer();
		String ln = "";
		File arq = new File(arquivo);
		boolean existe = false;

		if (!verificaCadastro(arquivo, codigo)) {
			existe = true;
			ln = Integer.toString(codigo) + ";" + nome + ";" + email;
			buffer.append(ln);

			FileWriter escritor = new FileWriter(arquivo, existe);
			PrintWriter imprimeArquivo = new PrintWriter(escritor);
			imprimeArquivo.write(ln);

			imprimeArquivo.flush();
			imprimeArquivo.close();
			escritor.close();

			JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
		} else {
			JOptionPane.showMessageDialog(null, "Código já cadastrado!");
		}

	}

	private boolean verificaCadastro(String arquivo, int codigo) throws IOException {

	File arq = new File(arquivo);
	String ln;
	String content[];
	int i = 0;

	if (arq.exists() && arq.isFile()) {
		FileInputStream stream = new FileInputStream(arq);
		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader buffer = new BufferedReader(reader);

		ln = buffer.readLine();

		while (ln != null) {
			content = ln.split(";");
			if (Integer.toString(codigo).equals(content[i])) {
				return true;
			}
		
			ln = buffer.readLine();
		}
		buffer.close();
		reader.close();
		stream.close();
	}

return false;

}

}
