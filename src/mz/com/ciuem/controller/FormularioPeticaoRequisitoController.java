package mz.com.ciuem.controller;

import java.util.List;

import org.zkoss.zhtml.Li;
import org.zkoss.zhtml.Text;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;

import mz.com.ciuem.inamar.dao.RequisitoDeTipoPeticaoDAO;
import mz.com.ciuem.model.Peticao;
import mz.com.ciuem.model.RequisitoDeTipoPeticao;

public class FormularioPeticaoRequisitoController extends GenericForwardComposer<Component>{
	
	private Listbox lbxRequisitosPeticao;
	
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);
		Peticao peticao = (Peticao) Executions.getCurrent().getDesktop().getSession().getAttribute("servicosPedido");
		if(peticao != null)
			listar(peticao);
		else
			Clients.showNotification("Selecione o Pedido", "error", lbxRequisitosPeticao, "center", 2000);
	}

	public void listar(Peticao p){
		lbxRequisitosPeticao.getItems().clear();
		
		Listitem item = new Listitem();
		
		Listcell cell1 = new Listcell(p.getCodigo());
		Listcell cell2 = new Listcell(p.getDescricao());
		Listcell cell3 = new Listcell();
		if(p.isIntrumentoLegal())
			cell3.setLabel("Ativo");
		else
			cell3.setLabel("Desativo");
		
		Listcell cell4 = new Listcell(p.getArea().getDescricao());
		Listcell cell5 = new Listcell();
		Listcell cell6 = new Listcell();
		
		for(RequisitoDeTipoPeticao r : RequisitoDeTipoPeticaoDAO.getBayPeticao(p)){
	
			Li li1 = new Li();
			Text t1 = new Text(r.getDescricao());
			
			li1.appendChild(t1);
			cell5.appendChild(li1);
			
			Li li2 = new Li();
			Text t2 = new Text(r.getRequisito().getDescricao());
			
			li2.appendChild(t2);
			cell6.appendChild(li2);
		}
		item.appendChild(cell1);
		item.appendChild(cell2);
		item.appendChild(cell3);
		item.appendChild(cell4);
		item.appendChild(cell5);
		item.appendChild(cell6);
		
		item.setValue(p);
		lbxRequisitosPeticao.appendChild(item);
	}
}
