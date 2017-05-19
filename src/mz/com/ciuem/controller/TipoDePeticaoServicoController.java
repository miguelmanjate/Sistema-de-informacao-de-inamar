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
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;

import mz.com.ciuem.inamar.dao.ServicoDAO;
import mz.com.ciuem.inamar.dao.TipoDePeticaoServicoDAO;
import mz.com.ciuem.model.Peticao;
import mz.com.ciuem.model.Servico;
import mz.com.ciuem.model.TipoDePeticaoServico;

public class TipoDePeticaoServicoController extends GenericForwardComposer<Component> {

	private Textbox descricao;
	private Textbox valor;
	private Radio radioSim;
	private Radio radioNao;
	private Combobox cbxServico;
	private Button btnCancelar;
	private Button btnGravar;
	private Listbox lbxPeticaoServico;
	private Button btnListar;
	private Button btnPesquisar;

	public void doAfterCompose(Component com) throws Exception {
		super.doAfterCompose(com);
		preencherServicos();
	}

	public void preencherServicos() {
		List<Servico> servicos = ServicoDAO.listar();

		for (Servico s : servicos) {
			Comboitem item = new Comboitem();
			item.setLabel(s.getDescricao());
			item.setValue(s);
			cbxServico.appendChild(item);
		}

	}

	public Servico onSelect$cbxServico(Event e) {
		if (cbxServico.getSelectedItem() != null) {
			return (Servico) cbxServico.getSelectedItem().getValue();
		}
		return null;
	}

	public void onClick$btnGravar(Event e) {
		lbxPeticaoServico.getItems().clear();
		Servico s = onSelect$cbxServico(e);
		Peticao p = (Peticao) Executions.getCurrent().getDesktop().getSession().getAttribute("peticao");

		TipoDePeticaoServico tps = new TipoDePeticaoServico();
		tps.setDescricao(descricao.getValue());
		try {
			tps.setValor(Double.parseDouble(valor.getValue()));
		} catch (NumberFormatException e2) {
			Clients.showNotification("Introdusa um valor numerico ", "error", valor, "center", 2000);
		}
		if (radioSim.isChecked()) {
			tps.setAtivo(true);
		} else if (radioNao.isChecked()) {
			tps.setAtivo(false);
		}
		tps.setPeticao(p);
		tps.setServico(s);
		if (tps.isValid() && s != null && p != null) {
			if (radioSim.isChecked() || radioNao.isChecked()) {
				TipoDePeticaoServicoDAO.adicionar(tps);
				listar(tps);
				limpar();
				Clients.showNotification("Cofiguracao do pedido efetuada com sucesso!!!", "info", btnGravar, "center",
						2000);
			} else {
				Clients.showNotification("Erro, determina o estado de ativacao!!!", "error", btnGravar, "center", 2000);
			}
		} else {
			Clients.showNotification("Erro na configuracao do Pedido, dados em falta ", "error", btnGravar, "center",
					2000);
		}
	}

	public void onClick$btnListar(Event e) {
		lbxPeticaoServico.getItems().clear();
		List<TipoDePeticaoServico> tps = TipoDePeticaoServicoDAO.listar();
		for (TipoDePeticaoServico t : tps) {
			listar(t);
		}
	}

	public void onClick$btnCancelar(Event e) {
		lbxPeticaoServico.getItems().clear();
		limpar();
	}

	public void onClick$btnPesquisar(Event e) {
		lbxPeticaoServico.getItems().clear();
		List<TipoDePeticaoServico> tps = null;
		double valorDouble = 0;
        try {
		valorDouble = Double.parseDouble(valor.getValue());
        } catch (Exception e2) {
			// TODO: handle exception
		}
		if (!descricao.getValue().isEmpty() || !valor.getValue().isEmpty()) {
			if(!descricao.getValue().isEmpty()){
				tps = TipoDePeticaoServicoDAO.getBayDescricao(descricao.getValue());
			}
			else if(!valor.getValue().isEmpty()) {
					tps = TipoDePeticaoServicoDAO.getBayValor(valorDouble);
			}
			for (TipoDePeticaoServico tpc : tps) {
				listar(tpc);
			}
		} else {
			Clients.showNotification("Introdusa um dado para a pesquisa", "error", btnPesquisar, "center", 2000);
		}
       
	}

	private void listar(TipoDePeticaoServico t) {
		Listitem item = new Listitem();

		Listcell lcell1 = new Listcell(t.getDescricao());
		Listcell lcell2 = new Listcell("" + t.getValor());
		Listcell lcell3 = new Listcell();
		if (t.isAtivo())
			lcell3.setLabel("Ativo");
		else
			lcell3.setLabel("Desativo");

		Listcell lcell4 = new Listcell(t.getPeticao().getDescricao());
		Listcell lcell5 = new Listcell(t.getServico().getDescricao());

		item.appendChild(lcell1);
		item.appendChild(lcell2);
		item.appendChild(lcell3);
		item.appendChild(lcell4);
		item.appendChild(lcell5);

		item.setValue(t);
		lbxPeticaoServico.appendChild(item);
	}

	public void limpar() {
		descricao.setRawValue(null);
		;
		valor.setRawValue(null);
		;
		radioSim.setChecked(false);
		radioNao.setChecked(false);
		cbxServico.setRawValue(null);
	}
}
