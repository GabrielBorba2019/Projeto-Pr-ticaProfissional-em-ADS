package entities;

public class PesquisaSatisfacao {
	
	private Servico servico;
	private String nameContratada;
	private String comentario;
	private Double nota;
	
	public PesquisaSatisfacao(Servico servico, String nameContratada, String comentario, Double nota) {
		this.servico = servico;
		this.nameContratada = nameContratada;
		this.comentario = comentario;
		this.nota = nota;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public String getNameContratada() {
		return nameContratada;
	}

	public void setNameContratada(String nameContratada) {
		this.nameContratada = nameContratada;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}
	
	
		
	
}
