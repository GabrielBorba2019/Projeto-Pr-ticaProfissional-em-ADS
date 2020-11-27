package entities;

import entities.enuns.StatusServico;
import entities.enuns.TipoServico;
import java.util.Date;

public class Servico {
	
	private Date dataInicio;
	private Date dataTermino;
	private StatusServico statusServico;// implementar enum Status servi�o;
//	private Orcamento orcamento; //implementar a classe orcamento
	private TipoServico tipoServico;// implementar a classe tipo servi�o
//	private Contrato contrato; //implementar a classe contrato;
	private Contratado contrado;
	
	public Servico(Date dataInicio, Date dataTermino, Contratado contrado) {
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.contrado = contrado;
	}
	
	
	

}
