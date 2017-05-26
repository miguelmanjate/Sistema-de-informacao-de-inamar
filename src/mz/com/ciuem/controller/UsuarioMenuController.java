package mz.com.ciuem.controller;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

import mz.com.ciuem.inamar.dao.UsuarioDAO;
import mz.com.ciuem.model.Usuario;

public class UsuarioMenuController extends GenericForwardComposer<Component> {
	private Div conteudo;
	private Button btnNovoUser;
	private Button btnListarUsuarios;
	private Button btnPesquisar;
	private Textbox nomeUsuario;
	private Listbox lbxUsuario;

	public void doAfterComposer(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}

	public void onClick$btnListarUsuarios(Event e) {
		lbxUsuario.getItems().clear();
		List<Usuario> usuarios = UsuarioDAO.listar();

		for (Usuario u : usuarios) {
			listar(u);
		}
	}

	public void onClick$btnPesquisar(Event e) {
		lbxUsuario.getItems().clear();
		List<Usuario> usuarios = UsuarioDAO.getByLogin(nomeUsuario.getValue());

		for (Usuario u : usuarios) {
			listar(u);
		}
	}

	public void onClick$btnNovoUser(Event e) {
		conteudo.getChildren().clear();
		Executions.createComponents("usuarioCadastro.zul", conteudo, null);
	}

	private void listar(Usuario usuario) {

		Listitem item = new Listitem();

		Listcell cell1 = new Listcell(usuario.getLogin());
		Listcell cell2 = new Listcell(usuario.getEmail());
		Listcell cell3 = new Listcell(usuario.getPerfil());
		Listcell cell4 = new Listcell();

		item.appendChild(cell1);
		item.appendChild(cell2);
		item.appendChild(cell3);
		item.appendChild(cell4);

		item.setValue(usuario);
		lbxUsuario.appendChild(item);
	}
}
