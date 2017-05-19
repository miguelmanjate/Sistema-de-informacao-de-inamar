package mz.com.ciuem.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Area {

	@Id
	@GeneratedValue
	private long id;
	private String codigo;
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name="orgao_id")
	private Orgao orgao;
	
	@OneToMany(fetch= FetchType.EAGER , mappedBy ="area")
	private List<Peticao> peticoes;
	
	public List<Peticao> getPeticoes() {
		return peticoes;
	}
	public Orgao getOrgao() {
		return orgao;
	}
	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setPeticoes(List<Peticao> peticoes) {
		this.peticoes = peticoes;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public boolean isValid(){
		return !codigo.isEmpty() && !descricao.isEmpty() && orgao != null;
	}
}
