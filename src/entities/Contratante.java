package entities;

import java.util.Date;

import entities.pag.CartaoCredito;

public class Contratante extends Usuario {
	
	private Double notaMedia;
	private CartaoCredito cartaoCredito;

	public Contratante(String nome, String cpf, String tipoPessoa, String endereco, String telefone, String email,Date dataNascimento, Double notaMedia) {
		super(nome, cpf, tipoPessoa, endereco, telefone, email, dataNascimento);
		this.notaMedia = notaMedia;
	}
	
	
}
