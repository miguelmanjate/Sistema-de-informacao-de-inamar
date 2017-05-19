package mz.com.ciuem.model;



import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Servico{
	
	@Id
	@GeneratedValue
	private long id;
	private String descricao;
	private boolean ativo;
	
	@OneToMany(mappedBy = "servico" , fetch=FetchType.EAGER)
	private List<TipoDePeticaoServico> tipoDePeticaoServico;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<TipoDePeticaoServico> getTipoDePeticaoServico() {
		return tipoDePeticaoServico;
	}
	
	public void setTipoDePeticaoServico(List<TipoDePeticaoServico> tipoDePeticaoServico) {
		this.tipoDePeticaoServico = tipoDePeticaoServico;
	}


	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public boolean isValid(){
		return !descricao.isEmpty() ;
	}

}
