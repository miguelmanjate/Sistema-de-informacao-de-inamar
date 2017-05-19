package mz.com.ciuem.controller;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;

import mz.com.ciuem.inamar.dao.ServicoDAO;
import mz.com.ciuem.model.Servico;

public class ServicoController extends GenericForwardComposer<Component> {
	private Textbox descricao;
	private Radio radioSim;
	private Radio radioNao;
	private Button btnCancelar;
	private Button btnGravar;
	private Button btnPesquisar;
	private Listbox lbxServico;
	private Listbox btnListar;

	public void doAfterCompose(Component com) throws Exception {
		super.doAfterCompose(com);
	}

	public void onClick$btnGravar(Event e) {
		lbxServico.getItems().clear();
		Servico servico = new Servico();

		servico.setDescricao(descricao.getValue());

		if (radioSim.isChecked()) {
			servico.setAtivo(true);
		} else if (radioNao.isChecked()) {
			servico.setAtivo(false);
		}
		if (servico.isValid()) {
			if(radioSim.isChecked() || radioNao.isChecked()){
				ServicoDAO.adicionar(servico);
				listar(servico);
				limpar();
				Clients.showNotification("Servico adicionado com sucesso!", "info", btnGravar, "center", 2000);
			}else{
				Clients.showNotification("Error O Defina o estado de Ativacao do servico!!", "error", btnGravar, "center", 2000);
			}
		} else {
			Clients.showNotification("Error O introdusa a descricao !", "error", btnGravar, "center", 2000);
		}
	}
   public void onClick$btnPesquisar(Event e){
	   lbxServico.getItems().clear();
	   if(!descricao.getValue().isEmpty()){
		   List<Servico> servicos = ServicoDAO.getByDescricao(descricao.getValue());
		   
		   for(Servico c : servicos){
			   listar(c);
		   }
	   }
   }
	public void onClick$btnCancelar(Event e) {
		lbxServico.getItems().clear();
		limpar();
	}

	private void listar(Servico s) {

		Listitem item = new Listitem();

		Listcell cell1 = new Listcell(s.getDescricao());
		Listcell cell2 = new Listcell();

		if (s.isAtivo()) {
			cell2.setLabel("Ativo");
		} else {
			cell2.setLabel("Desativo");
		}
		item.appendChild(cell1);
		item.appendChild(cell2);

		item.setValue(s);
		lbxServico.appendChild(item);

	}

	public void onClick$btnListar(Event e) {
		lbxServico.getItems().clear();

		List<Servico> servicos = ServicoDAO.listar();
		for (Servico servico : servicos) {
			listar(servico);
		}

	}

	public void limpar() {
		descricao.setRawValue(null);
		radioSim.setChecked(false);
		radioNao.setChecked(false);
	}
}
