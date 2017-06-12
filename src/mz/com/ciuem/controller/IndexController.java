package mz.com.ciuem.controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menuseparator;

import mz.com.ciuem.model.Usuario;

public class IndexController extends GenericForwardComposer<Component> {

	private Div conteudo;
	private Menu settings;
	private Menuitem areas;
	private Menuitem gestaoDePeticao;
	private Menuitem servico;
	private Menuitem orgao;
	private Menuitem requisito;
	private Menuitem idUsuarios;
	private Label lblUsuarioLogado;
	private Button btnSair;
	private Menuseparator orgaoArea;
	private Menuseparator areaPeticao;
	private Menuseparator peticaoServico;
	private Menuseparator servicoRequisito;
	
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);
		Usuario usuario = (Usuario) Executions.getCurrent().getDesktop().getSession().getAttribute("usuarioLogado");
		
		if(usuario != null){
			lblUsuarioLogado.setValue(usuario.getLogin());
			
			perfil(usuario.getPerfil());
		}
	}
	
	public void perfil(String perfil){
		
		if(perfil.trim().equalsIgnoreCase("Requerente")){
			orgao.setVisible(false);
			orgaoArea.setVisible(false);
			areas.setVisible(false);
			areaPeticao.setVisible(false);
			gestaoDePeticao.setVisible(false);
			areaPeticao.setVisible(false);
			servico.setVisible(false);
			servicoRequisito.setVisible(false);
			settings.setVisible(false);
	
		}else if(perfil.trim().equalsIgnoreCase("Operador")){
			idUsuarios.setVisible(false);
		}
		
		
	}
	

	public void onClick$embarcacoes(Event e) {

		conteudo.getChildren().clear();

		gestaoDePeticao.setDisabled(false);
		areas.setDisabled(false);
		servico.setDisabled(false);
		orgao.setDisabled(false);
		requisito.setDisabled(false);
		idUsuarios.setDisabled(false);
		Executions.createComponents("embarcacao.zul", conteudo, null);

	}

	public void onClick$areas(Event e) {

		conteudo.getChildren().clear();
		areas.setDisabled(true);

		gestaoDePeticao.setDisabled(false);
		servico.setDisabled(false);
		orgao.setDisabled(false);
		requisito.setDisabled(false);
		idUsuarios.setDisabled(false);
		Executions.createComponents("/WEB-INF/forms/areas.zul", conteudo, null);
	}

	public void onClick$gestaoDePeticao(Event e) {
		conteudo.getChildren().clear();
        
		gestaoDePeticao.setDisabled(true);
		
		areas.setDisabled(false);
		servico.setDisabled(false);
		orgao.setDisabled(false);
		requisito.setDisabled(false);
		idUsuarios.setDisabled(false);
		Executions.createComponents("/WEB-INF/forms/peticao.zul", conteudo, null);

	}
	
	public void onClick$servico(Event e){
		conteudo.getChildren().clear();
		servico.setDisabled(true);
		
		gestaoDePeticao.setDisabled(false);
		areas.setDisabled(false);
		orgao.setDisabled(false);
		requisito.setDisabled(false);
		idUsuarios.setDisabled(false);
		Executions.createComponents("/WEB-INF/forms/servicos.zul", conteudo, null);
	}
	
	public void onClick$orgao(Event e){
		conteudo.getChildren().clear();
		
		orgao.setDisabled(true);
		servico.setDisabled(false);
		gestaoDePeticao.setDisabled(false);
		areas.setDisabled(false);
		requisito.setDisabled(false);
		idUsuarios.setDisabled(false);
		Executions.createComponents("/WEB-INF/forms/orgao.zul", conteudo, null);
	}
	public void onClick$requisito(Event e){
		conteudo.getChildren().clear();
		
		requisito.setDisabled(true);
		servico.setDisabled(false);
		gestaoDePeticao.setDisabled(false);
		areas.setDisabled(false);
		orgao.setDisabled(false);
		idUsuarios.setDisabled(false);
		Executions.createComponents("/WEB-INF/forms/requisito.zul", conteudo, null);
		
	}
	public void onClick$idUsuarios(Event e){
		
        conteudo.getChildren().clear();
		
        idUsuarios.setDisabled(true);
		requisito.setDisabled(false);
		servico.setDisabled(false);
		gestaoDePeticao.setDisabled(false);
		areas.setDisabled(false);
		orgao.setDisabled(false);
		
		Executions.createComponents("/WEB-INF/forms/usuarioMenu.zul", conteudo, null);
		
	}
	public void onClick$btnSair(Event e){
		Executions.sendRedirect("usuarioLogin.zul");
		
		Executions.getCurrent().getDesktop().getSession().invalidate();
	}
}
