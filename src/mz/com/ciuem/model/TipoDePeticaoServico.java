package mz.com.ciuem.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TipoDePeticaoServico {
	
	@Id
	@GeneratedValue
	private long id;
	private String descricao;
	private boolean ativo;
	private double valor;
	
	@ManyToOne
	@JoinColumn(name ="peticao_id")
	private Peticao peticao;
	
	@ManyToOne
	@JoinColumn(name ="servico_id")
	private Servico servico;
	
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
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Peticao getPeticao() {
		return peticao;
	}
	public void setPeticao(Peticao peticao) {
		this.peticao = peticao;
	}
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	public boolean isValid(){
		return !descricao.isEmpty() && valor != 0 && peticao != null && servico != null;
	}

}
