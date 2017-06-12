package mz.com.ciuem.controller;

import java.util.List;

import javax.persistence.NoResultException;

import org.apache.catalina.ha.backend.Sender;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import mz.com.ciuem.inamar.dao.UsuarioDAO;
import mz.com.ciuem.model.Usuario;

public class UsuarioLoginController extends GenericForwardComposer<Component> {

	private Textbox txtLogin;
	private Textbox txtSenha;
	private Button btnLogin;
	
	private Window usuarioLogin;

	public void doAfterComposer(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}

	public void onClick$btnLogin(Event e) {
		Usuario usuario  = null;
	
		try {
			usuario = UsuarioDAO.getByLoginSennha(txtLogin.getValue(), txtSenha.getValue());
		} catch (Exception e2) {
			
		}
		if (usuario != null) {
		Executions.getCurrent().getDesktop().getSession().setAttribute("usuarioLogado", usuario);	
		Executions.createComponents("/WEB-INF/forms/index.zul", null, null);
		//Executions.sendRedirect("/index.zul");	
		usuarioLogin.detach();
		} else {
			Clients.showNotification("O Login ou senha incorreta!!", "error", btnLogin, "center", 2000);
			limpar();
		}
		
	}
	public void limpar(){
		txtLogin.setRawValue(null);
		txtSenha.setRawValue(null);
	}
}
