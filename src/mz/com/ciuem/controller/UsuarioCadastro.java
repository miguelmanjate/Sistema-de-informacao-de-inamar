package mz.com.ciuem.controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;

import mz.com.ciuem.inamar.dao.UsuarioDAO;
import mz.com.ciuem.model.Usuario;

public class UsuarioCadastro extends GenericForwardComposer<Component> {
	private Div conteudo;
	private Textbox txtNome;
	private Textbox txtEmail;
	private Textbox txtSenha;
	private Radio rdAdmin;
	private Radio rdOpera;
	private Radio rdReque;
	private Button btnGravar;
	private Button btnLimpar;
	private Button btnVoltar;

	public void doAfterComposer(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}

	public void onClick$btnGravar(Event e) {
		Usuario usuario = new Usuario();

		usuario.setLogin(txtNome.getValue());
		usuario.setEmail(txtEmail.getValue());

		if (rdAdmin.isChecked()){
			usuario.setPerfil("Administrador");
		}
		else if (rdOpera.isChecked()){
			usuario.setPerfil("Operador");
		}
		else if (rdReque.isChecked()){
			usuario.setPerfil("Requerente");
		}
		usuario.setSenha(txtSenha.getValue());
		if(usuario.isValid()){
		UsuarioDAO.adicionar(usuario);
		limpar();
		Clients.showNotification("Usuario cadastrado com sucesso!!!", "info", btnGravar, "center", 2000);
		}else{
			Clients.showNotification("Erro prienche todos os campos", "error", btnGravar, "center", 2000);
		}
	}
	public void onClick$btnLimpar(Event e){
		limpar();
	}
	public void onClick$btnVoltar(Event e){
		conteudo.getChildren().clear();
		
		Executions.createComponents("usuarioMenu.zul", conteudo, null);
	}
	public void limpar(){
		txtNome.setRawValue(null);
		txtEmail.setRawValue(null);
		txtSenha.setRawValue(null);
		rdAdmin.setChecked(false);
		rdOpera.setChecked(false);
		rdReque.setChecked(false);
	}

}
