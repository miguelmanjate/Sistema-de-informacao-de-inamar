package mz.com.ciuem.controller;

import java.util.List;

import org.zkoss.zhtml.Li;
import org.zkoss.zhtml.Text;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

import mz.com.ciuem.inamar.dao.OrgaoDAO;
import mz.com.ciuem.model.Area;
import mz.com.ciuem.model.Orgao;

public class OrgaoController extends GenericForwardComposer<Component> {

	private Textbox descricao;
	private Textbox local;
	private Textbox numeroConta;
	private Button btnCancel;
	private Button btnGravar;
	private Button btnListar;
	private Button btnListarAreasOrgao;
	private Button btnPesquisar;
	private Listbox lbxOrgao;
	private Listbox lbxAreasOrgao;
	private Div conteudoOrgao;
	private Orgao orgaoGlobal;

	public void doAfterCompose(Component com) throws Exception {
		super.doAfterCompose(com);
		// btnListarAreasOrgao.setVisible(false);
	}

	public void onClick$btnGravar(Event e) {

		lbxOrgao.getItems().clear();
		conteudoOrgao.getChildren().clear();
		Orgao orgao = new Orgao();

		orgao.setDescricao(descricao.getValue());
		orgao.setLocal(local.getValue());

		try {
			long numeroContaLong = Long.parseLong(numeroConta.getValue());
			orgao.setConta(numeroContaLong);

			if (orgao.isValid()) {
				OrgaoDAO.adicionar(orgao);
				listar(orgao);
				limpar();
				Clients.showNotification("Orgao adicionado com sucesso", "info", lbxOrgao, "center", 3000);
			} else {
				Clients.showNotification("Introdusa a descricao ou o Local", "error", lbxOrgao, "center", 3000);
			}
		} catch (NumberFormatException e2) {
			Clients.showNotification("Introdusa o numero da conta correta", "error", lbxOrgao, "center", 3000);
		}
	}

	public void onClick$btnCancel(Event e) {
		lbxOrgao.getItems().clear();
		conteudoOrgao.getChildren().clear();
		limpar();
	}

	public void onClick$btnListar(Event e) {
		lbxOrgao.getItems().clear();
		conteudoOrgao.getChildren().clear();
		List<Orgao> orgoes = OrgaoDAO.listar();
		for (Orgao orgao : orgoes) {
			listar(orgao);
		}

	}

	public void onClick$btnPesquisar(Event e) {
		conteudoOrgao.getChildren().clear();
		lbxOrgao.getItems().clear();
		List<Orgao> orgaes = null;

		long nr = 0;
		try {
			nr = Long.parseLong(numeroConta.getValue());
		} catch (Exception e2) {
	
		}
		if (!descricao.getValue().isEmpty() || !local.getValue().isEmpty() || !numeroConta.getValue().isEmpty()) {

			if (!descricao.getValue().isEmpty()) {
				orgaes = OrgaoDAO.getBayDescricao(descricao.getValue());
			} else if (!local.getValue().isEmpty()) {
				orgaes = OrgaoDAO.getBayLocal(local.getValue());
			} else if (!numeroConta.getValue().isEmpty()) {
				orgaes = OrgaoDAO.getBayConta(nr);
			}

			for (Orgao o : orgaes) {
				listar(o);
			}
		}else{
			Clients.showNotification("Introdusa uma informacao para a pesquisa ", "error", btnPesquisar, "center", 2000);
		}
	}

	public void listar(Orgao orgao) {

		Listitem item = new Listitem();

		Listcell cell1 = new Listcell(orgao.getDescricao());
		Listcell cell2 = new Listcell(orgao.getLocal());
		Listcell cell3 = new Listcell("" + orgao.getConta());

		item.appendChild(cell1);
		item.appendChild(cell2);
		item.appendChild(cell3);

		item.setValue(orgao);
		lbxOrgao.appendChild(item);
	}

	public Orgao onSelect$lbxOrgao(Event e) {

		Orgao o = (Orgao) lbxOrgao.getSelectedItem().getValue();
		lbxOrgao.getItems().clear();
		listar(o);
		btnListarAreasOrgao.setVisible(true);
		Executions.getCurrent().getDesktop().getSession().setAttribute("areasOrgao", o);
		return o;
	}

	public void onClick$btnListarAreasOrgao(Event e) {
		// btnListarAreasOrgao.setVisible(false);
		conteudoOrgao.getChildren().clear();
		Executions.createComponents("formularioOrgaoAreas.zul", conteudoOrgao, null);

	}

	public void limpar() {
		descricao.setRawValue(null);
		local.setRawValue(null);
		numeroConta.setRawValue(null);

	}
}
