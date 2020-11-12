package entities.pag;

import java.util.Date;

public class CartaoCredito {
	
	private String numeroCartao;
	private String nomeTitular;
	private Date dataVencimento;
	
	public CartaoCredito(String numeroCartao, String nomeTitular, Date dataVencimento) {
		this.numeroCartao = numeroCartao;
		this.nomeTitular = nomeTitular;
		this.dataVencimento = dataVencimento;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getNomeTitular() {
		return nomeTitular;
	}

	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	
	public boolean validaCartao(String numeroCartao, String nomeTitular, Date dataVencimento) {
		return true;
	}
	
}
