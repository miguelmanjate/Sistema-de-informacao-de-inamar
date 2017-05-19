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

import mz.com.ciuem.inamar.dao.RequisitoDAO;
import mz.com.ciuem.inamar.dao.RequisitoDeTipoPeticaoDAO;
import mz.com.ciuem.model.Peticao;
import mz.com.ciuem.model.Requisito;
import mz.com.ciuem.model.RequisitoDeTipoPeticao;

public class RequisitoDeTipoPeticaoController extends GenericForwardComposer<Component> {

	private Textbox descricao;
	private Radio radioSim;
	private Radio radioNao;
	private Combobox cbxRequisito;
	private Button btnPesquisar;
	private Button btnCancelar;
	private Button btnGravar;
	private Button btnListar;
	private Listbox lbxRequisitoPeticao;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		priencherRequisito();
	}

	public void priencherRequisito() {
		
		List<Requisito> requisitos = RequisitoDAO.listar();

		for (Requisito r : requisitos) {
			Comboitem item = new Comboitem();
			item.setLabel(r.getDescricao());
			item.setValue(r);
			cbxRequisito.appendChild(item);
		}
	}

	public Requisito onSelecte$cbxRequisito(Event e) {
		if (cbxRequisito.getSelectedItem() != null)
			return (Requisito) cbxRequisito.getSelectedItem().getValue();
		else
			return null;
	}

	public void onClick$btnGravar(Event e) {

		lbxRequisitoPeticao.getItems().clear();
		RequisitoDeTipoPeticao rtp = new RequisitoDeTipoPeticao();

		rtp.setDescricao(descricao.getValue());

		Requisito r = onSelecte$cbxRequisito(e);
		Peticao p = (Peticao) Executions.getCurrent().getDesktop().getSession().getAttribute("peticao");

		if (radioSim.isChecked())
			rtp.setEstado(true);
		else if (radioNao.isChecked())
			rtp.setEstado(false);

		rtp.setPeticao(p);
		rtp.setRequisito(r);
        
		if(rtp.isValid()){
			if(radioSim.isChecked() || radioNao.isChecked()){
				RequisitoDeTipoPeticaoDAO.adicionar(rtp);		
				listar(rtp);
				limpar();
				Clients.showNotification("Configuracao do pedido efetuada com sucesso", "info", btnGravar, "center", 2000);
			}else{
				Clients.showNotification("Erro, determina o estado de ativacao!!!", "error", btnGravar, "center", 2000);
			}
		}else{
			Clients.showNotification("Erro na configuracao do Pedido, dados em falta ", "error", btnGravar, "center",
					2000);
		}
	}
	public void onClick$btnListar(Event e){
		lbxRequisitoPeticao.getItems().clear();
		
		List<RequisitoDeTipoPeticao> rtps = RequisitoDeTipoPeticaoDAO.listar();
		for(RequisitoDeTipoPeticao r : rtps ){
			listar(r);
		}
	}
	
	public void onClick$btnCancelar(Event e){
		lbxRequisitoPeticao.getItems().clear();
		limpar();
	}
	public void onClick$btnPesquisar(Event e){
		lbxRequisitoPeticao.getItems().clear();
		
		if(!descricao.getValue().isEmpty()){
		List<RequisitoDeTipoPeticao> rtps = RequisitoDeTipoPeticaoDAO.getBayDescricao(descricao.getValue());
		
		for(RequisitoDeTipoPeticao r : rtps){
			listar(r);
		}
		}else{
			Clients.showNotification("Erro introdusa uma informacao para pesquisa", "error", btnPesquisar, "center",
					2000);
		}
	}
	public void listar(RequisitoDeTipoPeticao rtp){
		
		Listitem item = new Listitem();

		Listcell lcell1 = new Listcell(rtp.getDescricao());
		Listcell lcell2 = new Listcell();
		if (rtp.isEstado())
			lcell2.setLabel("Ativo");
		else
			lcell2.setLabel("Desativo");

		Listcell lcell3 = new Listcell(rtp.getPeticao().getDescricao());
		Listcell lcell4 = new Listcell(rtp.getRequisito().getDescricao());

		item.appendChild(lcell1);
		item.appendChild(lcell2);
		item.appendChild(lcell3);
		item.appendChild(lcell4);
	
		item.setValue(rtp);
		lbxRequisitoPeticao.appendChild(item);
	}
	public void limpar(){
		descricao.setRawValue(null);
		radioSim.setChecked(false);
		radioNao.setChecked(false);
		cbxRequisito.setRawValue(null);
	}
}
