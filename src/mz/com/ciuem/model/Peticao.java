package mz.com.ciuem.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Peticao {
	
	@Id
	@GeneratedValue
	private long id;
	private String codigo;
	private String descricao;
	private boolean intrumentoLegal;
	
	@ManyToOne
	@JoinColumn(name ="area_id")
	private Area area;
	
	@OneToMany( fetch=FetchType.LAZY, mappedBy = "peticao" )
	private List<TipoDePeticaoServico> tipoDePeticaoServico;
	
	@OneToMany( fetch=FetchType.LAZY, mappedBy= "peticao" )
	private List<RequisitoDeTipoPeticao> requisitoDeTipoPeticao;
	
	public List<RequisitoDeTipoPeticao> getRequisitoDeTipoPeticao() {
		return requisitoDeTipoPeticao;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setTipoDePeticaoServico(List<TipoDePeticaoServico> tipoDePeticaoServico) {
		this.tipoDePeticaoServico = tipoDePeticaoServico;
	}
	public List<TipoDePeticaoServico> getTipoDePeticaoServico() {
		return tipoDePeticaoServico;
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
	public boolean isIntrumentoLegal() {
		return intrumentoLegal;
	}
	public void setIntrumentoLegal(boolean intrumentoLegal) {
		this.intrumentoLegal = intrumentoLegal;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	
	@Override
	public String toString() {
		return  codigo+" DESCRICAO : "+descricao+" INSTRUMENTO: "+intrumentoLegal+" AREA: "+area;
	}
    public boolean isValid(){
    	return !codigo.isEmpty() && !descricao.isEmpty() && area != null;
    }
}
