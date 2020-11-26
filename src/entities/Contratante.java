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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getcpf() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Date getDataNascimento() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public double getNotaMedia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
	
}
