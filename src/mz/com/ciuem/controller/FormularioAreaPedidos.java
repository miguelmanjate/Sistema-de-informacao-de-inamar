package mz.com.ciuem.controller;

import org.zkoss.zhtml.Li;
import org.zkoss.zhtml.Text;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.ExecutionCleanup;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;

import mz.com.ciuem.model.Area;
import mz.com.ciuem.model.Orgao;
import mz.com.ciuem.model.Peticao;

public class FormularioAreaPedidos extends GenericForwardComposer<Component> {
	private Listbox lbxPedidosArea;
	public void doAfterCompose(Component comp) throws Exception{
		
		super.doAfterCompose(comp);
	  Area area = (Area) Executions.getCurrent().getDesktop().getSession().getAttribute("pedidosArea");
	  if(area != null){
		  listarAreasOrgao(area);
	  }else {
		  Clients.showNotification("Selecione uma area", "error", lbxPedidosArea, "center", 2000);
	  }
	  
	}

	public void listarAreasOrgao(Area area) {
		
		lbxPedidosArea.getItems().clear();
		Listitem item = new Listitem();

		Listcell cell1 = new Listcell(area.getCodigo());
		Listcell cell2 = new Listcell(area.getDescricao());
		Listcell cell3 = new Listcell("" + area.getOrgao().getDescricao());
		Listcell cell4 = new Listcell();
		for(Peticao p : area.getPeticoes()){
			Li li = new Li();
        	Text txt = new Text(p.getDescricao());
        	li.appendChild(txt);
			cell4.appendChild(li);
		}
		item.appendChild(cell1);
		item.appendChild(cell2);
		item.appendChild(cell3);
		item.appendChild(cell4);

		item.setValue(area);
		lbxPedidosArea.appendChild(item);
	}
}
