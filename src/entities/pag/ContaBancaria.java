package entities.pag;

public class ContaBancaria {
	private String agencia;
	private String conta;
	private String banco;
	
	public ContaBancaria() {
		
	}

	public ContaBancaria(String agencia, String conta, String banco) {
		this.agencia = agencia;
		this.conta = conta;
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

}
