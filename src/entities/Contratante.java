package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.pag.CartaoCredito;

public class Contratante extends Usuario {
	
	private Double notaMedia;
	private CartaoCredito cartaoCredito;
	private List<PesquisaSatisfacao> pesquisas = new ArrayList();
        private final long id;

	public Contratante(long id,String nome, String cpf, String tipoPessoa, String endereco, String telefone, String email,Date dataNascimento, Double notaMedia) {
		super(nome, cpf, tipoPessoa, endereco, telefone, email, dataNascimento);
		this.notaMedia = notaMedia;
                this.id = id;
	}

    public long getId() {
        return id;
    }

    public String getcpf() {
        return cpf; 
    }
    
    public Date getDataNascimento() {
        return dataNascimento;
    }

    public double getNotaMedia() {
        return notaMedia;
    }

    public void setId(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
	
}
