package mz.com.ciuem.controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Menuitem;

public class IndexController extends GenericForwardComposer<Component> {

	private Div conteudo;
	private Menuitem areas;
	private Menuitem gestaoDePeticao;
	private Menuitem servico;
	private Menuitem maritimos;
	private Menuitem empresas;
	private Menuitem embarcacoes;
	private Menuitem vistorias;
	private Menuitem pagamentos;
	private Menuitem estatistica;
	private Menuitem infraEstrutura;
	private Menuitem implementacao;
	private Menuitem expaco;
	private Menuitem pol;
	private Menuitem orgao;
	private Menuitem requisito;

	public void onClick$embarcacoes(Event e) {

		conteudo.getChildren().clear();

		embarcacoes.setDisabled(true);

		gestaoDePeticao.setDisabled(false);
		maritimos.setDisabled(false);
		empresas.setDisabled(false);
		vistorias.setDisabled(false);
		pagamentos.setDisabled(false);
		estatistica.setDisabled(false);
		infraEstrutura.setDisabled(false);
		implementacao.setDisabled(false);
		expaco.setDisabled(false);
		pol.setDisabled(false);
		areas.setDisabled(false);
		servico.setDisabled(false);
		orgao.setDisabled(false);
		requisito.setDisabled(false);
		Executions.createComponents("embarcacao.zul", conteudo, null);

	}

	public void onClick$areas(Event e) {

		conteudo.getChildren().clear();
		areas.setDisabled(true);

		embarcacoes.setDisabled(false);
		gestaoDePeticao.setDisabled(false);
		maritimos.setDisabled(false);
		empresas.setDisabled(false);
		vistorias.setDisabled(false);
		pagamentos.setDisabled(false);
		estatistica.setDisabled(false);
		infraEstrutura.setDisabled(false);
		implementacao.setDisabled(false);
		expaco.setDisabled(false);
		pol.setDisabled(false);
		servico.setDisabled(false);
		orgao.setDisabled(false);
		requisito.setDisabled(false);
		Executions.createComponents("areas.zul", conteudo, null);
	}

	public void onClick$gestaoDePeticao(Event e) {
		conteudo.getChildren().clear();
        
		gestaoDePeticao.setDisabled(true);
		embarcacoes.setDisabled(false);
		
		maritimos.setDisabled(false);
		empresas.setDisabled(false);
		vistorias.setDisabled(false);
		pagamentos.setDisabled(false);
		estatistica.setDisabled(false);
		infraEstrutura.setDisabled(false);
		implementacao.setDisabled(false);
		expaco.setDisabled(false);
		pol.setDisabled(false);
		areas.setDisabled(false);
		servico.setDisabled(false);
		orgao.setDisabled(false);
		requisito.setDisabled(false);
		Executions.createComponents("peticao.zul", conteudo, null);

	}
	
	public void onClick$servico(Event e){
		conteudo.getChildren().clear();
		servico.setDisabled(true);
		
		gestaoDePeticao.setDisabled(false);
		embarcacoes.setDisabled(false);
		maritimos.setDisabled(false);
		empresas.setDisabled(false);
		vistorias.setDisabled(false);
		pagamentos.setDisabled(false);
		estatistica.setDisabled(false);
		infraEstrutura.setDisabled(false);
		implementacao.setDisabled(false);
		expaco.setDisabled(false);
		pol.setDisabled(false);
		areas.setDisabled(false);
		orgao.setDisabled(false);
		requisito.setDisabled(false);
		Executions.createComponents("servicos.zul", conteudo, null);
	}
	
	public void onClick$orgao(Event e){
		conteudo.getChildren().clear();
		
		orgao.setDisabled(true);
		servico.setDisabled(false);
		gestaoDePeticao.setDisabled(false);
		embarcacoes.setDisabled(false);
		maritimos.setDisabled(false);
		empresas.setDisabled(false);
		vistorias.setDisabled(false);
		pagamentos.setDisabled(false);
		estatistica.setDisabled(false);
		infraEstrutura.setDisabled(false);
		implementacao.setDisabled(false);
		expaco.setDisabled(false);
		pol.setDisabled(false);
		areas.setDisabled(false);
		requisito.setDisabled(false);
		Executions.createComponents("orgao.zul", conteudo, null);
	}
	public void onClick$requisito(Event e){
		conteudo.getChildren().clear();
		
		requisito.setDisabled(true);
		servico.setDisabled(false);
		gestaoDePeticao.setDisabled(false);
		embarcacoes.setDisabled(false);
		maritimos.setDisabled(false);
		empresas.setDisabled(false);
		vistorias.setDisabled(false);
		pagamentos.setDisabled(false);
		estatistica.setDisabled(false);
		infraEstrutura.setDisabled(false);
		implementacao.setDisabled(false);
		expaco.setDisabled(false);
		pol.setDisabled(false);
		areas.setDisabled(false);
		orgao.setDisabled(false);
		Executions.createComponents("requisito.zul", conteudo, null);
		
	}
}
