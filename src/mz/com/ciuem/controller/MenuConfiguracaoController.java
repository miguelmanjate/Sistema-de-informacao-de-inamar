package mz.com.ciuem.controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Textbox;

import mz.com.ciuem.model.Peticao;

public class MenuConfiguracaoController extends GenericForwardComposer<Component>  {
	
	private Div conteudoMenuConfiguracao;
	private Button btnServicos;
	private Button btnRequisitos;
	private Textbox txbPedidoConf;
	private Peticao peticao;
	
	public void doAfterCompose(Component com) throws Exception{
		super.doAfterCompose(com);
		txbPedidoConf.setDisabled(true);
	}
 
	public void onClick$btnServicos(Event e){
		
		conteudoMenuConfiguracao.getChildren().clear();
		 peticao = (Peticao) Executions.getCurrent().getDesktop().getSession().getAttribute("peticao");
			txbPedidoConf.setValue(peticao.getDescricao());
		Executions.createComponents("/WEB-INF/forms/tipoDePeticaoServico.zul", conteudoMenuConfiguracao, null);
		btnRequisitos.setDisabled(true);
	}
	public void onClick$btnRequisitos(Event e){
		conteudoMenuConfiguracao.getChildren().clear();
		peticao = (Peticao) Executions.getCurrent().getDesktop().getSession().getAttribute("peticao");
		txbPedidoConf.setValue(peticao.getDescricao());
		Executions.createComponents("/WEB-INF/forms/RequisitosDeTipoPeticao.zul", conteudoMenuConfiguracao, null);
		btnServicos.setDisabled(true);
	}
}
