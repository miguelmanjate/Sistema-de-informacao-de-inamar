package mz.com.ciuem.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
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
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;

import mz.com.ciuem.inamar.dao.AreaDAO;
import mz.com.ciuem.inamar.dao.PeticaoDAO;
import mz.com.ciuem.model.Area;
import mz.com.ciuem.model.Peticao;
import mz.com.ciuem.model.TipoDePeticaoServico;

public class PeticaoControler extends GenericForwardComposer<Component> {

	private Div conteudo, conteudoDePeticao;
	private Textbox codigo;
	private Textbox descricao;
	private Textbox txtConfiguracao;
	private Combobox cbxPeticao;
	private Listbox lbxPeticao;
	private Listbox lbxServicosPeticao;
	private Listbox lbxRequisitosPeticao;
	private Button btnGravar;
	private Button btnCancelar;
	private Button btnListar;
	private Button btnListarServico;
	private Button btnListarRequisito;
	private Button btnConfigurar;
	private Button btnPesquisar;
	private Radio radioSim;
	private Radio radioNao;

	public void doAfterCompose(Component com) throws Exception {
		super.doAfterCompose(com);
		listarAreas();
		txtConfiguracao.setDisabled(true);
	}

	private void listarAreas() {
		cbxPeticao.getChildren().clear();
		List<Area> areas = AreaDAO.listar();

		for (Area area : areas) {
			Comboitem item = new Comboitem();
			item.setLabel(area.getDescricao());
			item.setValue(area);

			cbxPeticao.appendChild(item);
		}
	}

	public void onClick$btnGravar(Event e) {

		lbxPeticao.getItems().clear();
		Peticao p = new Peticao();

		p.setCodigo(codigo.getValue());
		p.setDescricao(descricao.getValue());

		if (radioSim.isChecked()) {
			p.setIntrumentoLegal(true);
		} else if (radioNao.isChecked()) {
			p.setIntrumentoLegal(false);
		}

		Area area = onSelect$cbxPeticao(e);
		p.setArea(area);
		if (p.isValid()) {
			if (radioSim.isChecked() || radioNao.isChecked()) {
				PeticaoDAO.adicionar(p);
				Clients.showNotification("Peticao adicionada com sucesso ", "info", btnGravar, "center", 2000);
				listar(p);
				txtConfiguracao.setValue(p.getCodigo());
				limpar();
			} else {
				Clients.showNotification("Error defina uma opcao para o estrumento legal", "error", btnGravar, "center",
						2000);
			}
		} else {
			Clients.showNotification("Error dados em falta ", "error", btnGravar, "center", 2000);
		}
	}

	public void onClick$btnCancelar(Event e) {
		lbxPeticao.getItems().clear();
		conteudoDePeticao.getChildren().clear();
		limpar();

	}

	public void onClick$btnListar(Event e) {
		lbxPeticao.getItems().clear();
		conteudoDePeticao.getChildren().clear();
		List<Peticao> peticoes = PeticaoDAO.listar();
		for (Peticao p : peticoes) {
			listar(p);
		}

	}

	private void listar(Peticao peticao) {

		Listitem item = new Listitem();

		Listcell cell1 = new Listcell(peticao.getCodigo());
		Listcell cell2 = new Listcell(peticao.getDescricao());
		Listcell cell3 = new Listcell();
		if (peticao.isIntrumentoLegal())
			cell3.setLabel("Ativo");
		else
			cell3.setLabel("Desativo");
		Listcell cell4 = new Listcell(peticao.getArea().getDescricao());

		item.appendChild(cell1);
		item.appendChild(cell2);
		item.appendChild(cell3);
		item.appendChild(cell4);
		item.setValue(peticao);
		lbxPeticao.appendChild(item);
	}

	public void onSelect$lbxPeticao(Event e) {

		Peticao peticao = (Peticao) lbxPeticao.getSelectedItem().getValue();
		lbxPeticao.getItems().clear();

		listar(peticao);
		cbxPeticao.setValue(peticao.getArea().getDescricao());
		cbxPeticao.setDisabled(true);
		txtConfiguracao.setValue(peticao.getCodigo());
		Executions.getCurrent().getDesktop().getSession().setAttribute("servicosPedido", peticao);

	}

	public void onClick$btnListarServico(Event e) {
		conteudoDePeticao.getChildren().clear();
		Executions.createComponents("formularioPedidoServicos.zul", conteudoDePeticao, null);
	}
	public void onClick$btnListarRequisito(Event e){
		conteudoDePeticao.getChildren().clear();
		Executions.createComponents("formularioPeticaoRequisito.zul", conteudoDePeticao, null);
	}
	private Area onSelect$cbxPeticao(Event e) {
		if (cbxPeticao.getSelectedItem() != null)
			return (Area) cbxPeticao.getSelectedItem().getValue();

		return null;
	}

	public void onClick$btnConfigurar(Event e) {
		
		conteudoDePeticao.getChildren().clear();
		lbxPeticao.getItems().clear();

		Peticao p = null;
		try {
			p = getPeticaoCodigo();
		} catch (NoResultException e1) {

		}
		if (p != null) {
			listar(p);
			Executions.createComponents("menuConfiguracao.zul", conteudoDePeticao, null);
			Executions.getCurrent().getDesktop().getSession().setAttribute("peticao", p);
		} else {
			Clients.showNotification("Selecione uma peticao ", "error", btnGravar, "center", 2000);
		}
	}

	public void onClick$btnPesquisar(Event e) {
		lbxPeticao.getItems().clear();
		conteudoDePeticao.getChildren().clear();
		List<Peticao> peticoes = null;
		if (!codigo.getValue().isEmpty() || !descricao.getValue().isEmpty()) {
			if (!codigo.getValue().isEmpty())
				peticoes = PeticaoDAO.getBayCodigo(codigo.getValue());
			else
				peticoes = PeticaoDAO.getBayDescricao(descricao.getValue());
			
			for (Peticao p : peticoes) {
				listar(p);
			}
		} else {
			Clients.showNotification("Introdusa dados para a pesquisa", "error", btnPesquisar, "center", 2000);
		}
	}

	public Peticao getPeticaoCodigo() throws NoResultException {
		return PeticaoDAO.getByCodigo(txtConfiguracao.getValue());

	}

	public void limpar() {
		codigo.setRawValue(null);
		descricao.setRawValue(null);
		cbxPeticao.setRawValue(null);
		txtConfiguracao.setRawValue(null);
		radioSim.setChecked(false);
		radioNao.setChecked(false);
	}

}
