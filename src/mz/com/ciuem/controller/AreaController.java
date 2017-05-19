package mz.com.ciuem.controller;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Div;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

import mz.com.ciuem.inamar.dao.AreaDAO;
import mz.com.ciuem.inamar.dao.OrgaoDAO;
import mz.com.ciuem.model.Area;
import mz.com.ciuem.model.Orgao;

public class AreaController extends GenericForwardComposer<Component> {

	private Button btnGravar;
	private Button btnCancelar;
	private Button btnListar;
	private Button btnPesquisar;
	private Button btnPedidosArea;
	private Textbox codigoArea;
	private Textbox descricaoArea;
	private Combobox cbxOrgao;
	private Listbox lbxArea;
	private Div conteudoArea;

	public void doAfterCompose(Component com) throws Exception {
		super.doAfterCompose(com);
		listarOrgao();
	}

	private void listarOrgao() {
		List<Orgao> orgoes = OrgaoDAO.listar();
		cbxOrgao.getChildren().clear();
		for (Orgao orgao : orgoes) {

			Comboitem item = new Comboitem();
			item.setLabel(orgao.getDescricao());
			item.setValue(orgao);

			cbxOrgao.appendChild(item);
		}
	}

	public void onClick$btnGravar(Event e) {
		lbxArea.getItems().clear();
		Area area = new Area();

		area.setCodigo(codigoArea.getValue());
		area.setDescricao(descricaoArea.getValue());

		Orgao orgao = onSelect$cbxOrgao(e);
		area.setOrgao(orgao);

		if (area.isValid()) {
			AreaDAO.adicionar(area);
			Clients.showNotification("Area adicionada com sucesso!!!", "info", btnGravar, "center", 2000);
			listar(area);
			limpar();
		} else {
			Clients.showNotification("Erro ha dados da area em falta", "error", btnGravar, "center", 2000);
		}

	}

	private Orgao onSelect$cbxOrgao(Event e) {
		if (cbxOrgao.getSelectedItem() != null) {
			return (Orgao) cbxOrgao.getSelectedItem().getValue();
		} else {
			return null;
		}
	}

	public void onClick$btnListar(Event e) {
		lbxArea.getItems().clear();
         limpar();
		List<Area> areas = AreaDAO.listar();
		for (Area area : areas) {
			listar(area);
		}
	}

	public void onClick$btnCancelar(Event e) {
		lbxArea.getItems().clear();
		limpar();
	}

	public void onSelect$lbxArea(Event e) {
		Area area = (Area) lbxArea.getSelectedItem().getValue();
		lbxArea.getItems().clear();
		listar(area);
		Executions.getCurrent().getDesktop().getSession().setAttribute("pedidosArea", area);
	}

	public void onClick$btnPedidosArea(Event e) {

		conteudoArea.getChildren().clear();
		Executions.createComponents("formularioAreaPedidos.zul", conteudoArea, null);
	}

	public void onClick$btnPesquisar(Event e) {
		lbxArea.getItems().clear();
		conteudoArea.getChildren().clear();
		List<Area> areas = null;
    if(!codigoArea.getValue().isEmpty() || !descricaoArea.getValue().isEmpty()){
    	
		if (!codigoArea.getValue().isEmpty())
			areas = AreaDAO.getBayCodigo(codigoArea.getValue());
		else if(!descricaoArea.getValue().isEmpty())
			areas = AreaDAO.getBayDescricao(descricaoArea.getValue());

		for (Area a : areas) {
			listar(a);
		}
    }else{
    	Clients.showNotification("Introdusa uma informacao para a pesquisa ", "error", btnPesquisar, "center", 2000);
    }
	}

	private void listar(Area area) {

		Listitem item = new Listitem();

		Listcell cell1 = new Listcell(area.getCodigo());
		Listcell cell2 = new Listcell(area.getDescricao());
		Listcell cell3 = new Listcell(area.getOrgao().getDescricao());

		item.appendChild(cell1);
		item.appendChild(cell2);
		item.appendChild(cell3);

		item.setValue(area);
		lbxArea.appendChild(item);
	}

	public void limpar() {
		conteudoArea.getChildren().clear();
		codigoArea.setRawValue(null);
		descricaoArea.setRawValue(null);
		cbxOrgao.setRawValue(null);
	}
}
