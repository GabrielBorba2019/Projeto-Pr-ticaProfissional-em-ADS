package entities;

import java.util.Date;

public class Servico {
	
	private Date dataInicio;
	private Date dataTermino;
	//private StatusServico statusServico; implementar enum Status serviço;
	//private Orcamento orcamento; implementar a classe orcamento
	//private TipoServico tipoServico; implementar a classe tipo serviço
	//private Contrato contrato; implementar a classe contrato;
	private Contratado contrado;
	
	public Servico(Date dataInicio, Date dataTermino, Contratado contrado) {
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.contrado = contrado;
	}
	
	
	

}
