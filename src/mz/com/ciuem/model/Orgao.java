package mz.com.ciuem.model;




import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Orgao{
	
	@Id
	@GeneratedValue
	private long id;
	private String descricao;
	private String local;
	private long conta;
	
	@OneToMany(mappedBy= "orgao", fetch = FetchType.EAGER)
	List<Area> areas;
	
	public List<Area> getAreas() {
		return areas;
	}
	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}
	
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
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public long getConta() {
		return conta;
	}
	public void setConta(long conta) {
		this.conta = conta;
	}
	
	public boolean isValid(){
		return !descricao.isEmpty() && !local.isEmpty() && conta != 0;
	}
	

}
