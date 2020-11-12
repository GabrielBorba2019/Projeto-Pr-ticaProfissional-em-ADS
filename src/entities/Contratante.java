package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.pag.CartaoCredito;

public class Contratante extends Usuario {
	
	private Double notaMedia;
	private CartaoCredito cartaoCredito;
	private List<PesquisaSatisfacao> pesquisas = new ArrayList();

	public Contratante(String nome, String cpf, String tipoPessoa, String endereco, String telefone, String email,Date dataNascimento, Double notaMedia) {
		super(nome, cpf, tipoPessoa, endereco, telefone, email, dataNascimento);
		this.notaMedia = notaMedia;
	}
	
	
}
