package mz.com.ciuem.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RequisitoDeTipoPeticao {
	@Id
	@GeneratedValue
	private long id;
	private String descricao;
	private boolean estado;
	
	@ManyToOne
	@JoinColumn(name ="peticao_id")
	private Peticao peticao;
	
	@ManyToOne
	@JoinColumn(name = "requisito_id")
	private Requisito requisito;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Peticao getPeticao() {
		return peticao;
	}

	public void setPeticao(Peticao peticao) {
		this.peticao = peticao;
	}

	public Requisito getRequisito() {
		return requisito;
	}

	public void setRequisito(Requisito requisito) {
		this.requisito = requisito;
	}
	
	public boolean isValid(){
		return !descricao.isEmpty() && peticao != null && requisito != null;
	}

}
