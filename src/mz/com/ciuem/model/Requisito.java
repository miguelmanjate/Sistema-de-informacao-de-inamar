package mz.com.ciuem.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Requisito {
	@Id
	@GeneratedValue
	private long id;
	private String descricao;
	private boolean estado;
	
//	@OneToMany(fetch=FetchType.LAZY, mappedBy="requisito")
//	private List<RequisitoDeTipoPeticao> requisitoDeTipoPeticao;
//	
//	public List<RequisitoDeTipoPeticao> getRequisitoDeTipoPeticao() {
//		return requisitoDeTipoPeticao;
//	}
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
	
	public boolean isValid(){
		return !descricao.isEmpty() ;
	}
	
}
