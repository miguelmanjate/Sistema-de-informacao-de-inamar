package mz.com.ciuem.controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

import mz.com.ciuem.model.Embarcacao;

public class EmbarcacaoController extends GenericForwardComposer<Component> {
	
	private Button btnGravar;
	private Button Cancelar;
	
	private Textbox nome;
	private Textbox nomePai;
	private Textbox nomeMae;
	private Datebox dataNascimento;
	private Textbox bi;
	private Datebox dataEmissao;
	private Textbox bairro;
	private Textbox quarterao;
	private Textbox casa;
	private Textbox telefone;
	private Combobox servico;
	private Listbox lbxEmbarcacao;
	
	public void doAfterCompose(Component com) throws Exception{
		super.doAfterCompose(com);
	}
	
	public void onClick$btnGravar(Event e){
		
		Embarcacao embarcacao = new Embarcacao();
		
		embarcacao.setNome(nome.getValue());
		embarcacao.setNomePai( nomePai.getValue());
		embarcacao.setNomeMae(nomeMae.getValue());
		
		Instant i = dataNascimento.getValue().toInstant();
		LocalDateTime ldt = LocalDateTime.ofInstant(i, ZoneId.systemDefault());
		LocalDate ld = ldt.toLocalDate();
		
		embarcacao.setDataNascimento(ld);
		embarcacao.setBi(bi.getValue());
		
		Instant i1 = dataEmissao.getValue().toInstant();
		LocalDateTime ldt1 = LocalDateTime.ofInstant(i, ZoneId.systemDefault());
		LocalDate ld1 = ldt.toLocalDate();
		
		embarcacao.setDataEmissao(ld1);
		embarcacao.setBairro(bairro.getValue());
		
		int quarteraoInt = Integer.parseInt(quarterao.getValue());
		embarcacao.setQuarterao(quarteraoInt);
		int casaInt = Integer.parseInt(casa.getValue());
		
		embarcacao.setCasa(casaInt);
		
		int telefoneInt = Integer.parseInt(telefone.getValue());
		embarcacao.setTelefone(telefoneInt);
		limpar();
		System.out.println(embarcacao.getNome()+" "+embarcacao.getNomePai()+" "
				+ ""+embarcacao.getNomeMae()+"  "+embarcacao.getDataNascimento()+" "+embarcacao.getDataEmissao());
		
	}
	
	public void limpar(){
		nome.setRawValue(null);
		nomePai.setRawValue(null);
		nomeMae.setRawValue(null);
		dataNascimento.setRawValue(null);
		bi.setRawValue(null);
		dataEmissao.setRawValue(null);
		bairro.setRawValue(null);
		quarterao.setRawValue(null);
		casa.setRawValue(null);
		telefone.setRawValue(null);
	}

}
