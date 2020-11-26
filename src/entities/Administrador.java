package entities;

import java.util.Date;

public class Administrador extends Usuario {

	public Administrador(String nome, String cpf, String tipoPessoa, String endereco, String telefone, String email, Date dataNascimento) {
		super(nome, cpf, tipoPessoa, endereco, telefone, email, dataNascimento);
	}

}
