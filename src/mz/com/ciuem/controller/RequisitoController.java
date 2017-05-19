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
import org.zkoss.zul.Textbox;

import mz.com.ciuem.inamar.dao.RequisitoDAO;
import mz.com.ciuem.model.Requisito;

public class RequisitoController extends GenericForwardComposer<Component>{
	
	private Textbox descricao;
	private Radio radioSim;
	private Radio radioNao;
	private Button btnCancelar;
	private Button btnGravar;
	private Button btnListar;
	private Button btnPesquisar;
	private Listbox lbxRequisitos;
	
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);
	}
  
	public void onClick$btnGravar(Event e){
		lbxRequisitos.getItems().clear();
		Requisito requisito = new Requisito();
		
		requisito.setDescricao(descricao.getValue());
		
		if(radioSim.isChecked()) 
			requisito.setEstado(true);
		else if(radioNao.isChecked())
			requisito.setEstado(false);
		
		if(requisito.isValid()){
			if(radioSim.isChecked() || radioNao.isChecked()){
				RequisitoDAO.adicionar(requisito);
				listar(requisito);
				limpar();
				Clients.showNotification("Requisito adicionado com sucesso", "info", btnGravar, "center", 2000);
			}else{
				Clients.showNotification("Seleciona o Estado", "error", btnGravar, "center", 2000);
			}
		}else{
			Clients.showNotification("Introdusa a descricao", "error", btnGravar, "center", 2000);
		}
	}
	public void onClick$btnListar(Event e){
		lbxRequisitos.getItems().clear();
		List<Requisito> requisitos = RequisitoDAO.listar();
		limpar();
		for(Requisito requisito : requisitos){
			listar(requisito);
		}
	}
	public void onClick$btnCancelar(Event e){
		lbxRequisitos.getItems().clear();
		limpar();
	}
	public void onClick$btnPesquisar(Event e){
		lbxRequisitos.getItems().clear();
		if(!descricao.getValue().isEmpty()){
		List<Requisito> requisitos = RequisitoDAO.getByDescricao(descricao.getValue());
		
		for(Requisito requisito : requisitos){
			listar(requisito);
		}
		}else{
			Clients.showNotification("Introdusa um dado para pesquisa", "error", btnGravar, "center", 2000);
		}
	}
	public void listar(Requisito requisito){
		
		Listitem item = new Listitem();
		
		Listcell cell1 = new Listcell(requisito.getDescricao());
		Listcell cell2 = new Listcell();
		
		if(requisito.isEstado())
			cell2.setLabel("Ativo");
		else
			cell2.setLabel("Desativo");
		
		item.appendChild(cell1);
		item.appendChild(cell2);
		
		item.setValue(requisito);
		
		lbxRequisitos.appendChild(item);
	}
	public void limpar(){
		
		descricao.setRawValue(null);
		radioSim.setChecked(false);
		radioNao.setChecked(false);
	}
}
