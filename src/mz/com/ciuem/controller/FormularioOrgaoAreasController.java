package mz.com.ciuem.controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zhtml.Li;
import org.zkoss.zhtml.Text;

import mz.com.ciuem.model.Area;
import mz.com.ciuem.model.Orgao;

public class FormularioOrgaoAreasController extends GenericForwardComposer<Component> {
	
	private Listbox lbxAreasOrgao;
	private Div conteudoOrgao;
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);
		Orgao o = (Orgao) Executions.getCurrent().getDesktop().getSession().getAttribute("areasOrgao");
		if(o != null){
		listarAreasOrgao(o);
		} else {
			Clients.showNotification("Selecione um orgao", "error", lbxAreasOrgao, "center", 2000);
		}
	}
	
	public void listarAreasOrgao(Orgao orgao) {

		lbxAreasOrgao.getItems().clear();
		Listitem item = new Listitem();

		Listcell cell1 = new Listcell(orgao.getDescricao());
		Listcell cell2 = new Listcell(orgao.getLocal());
		Listcell cell3 = new Listcell("" + orgao.getConta());
		Listcell cell4 = new Listcell();
		for(Area a : orgao.getAreas()){
			Li li = new Li();
        	Text txt = new Text(a.getDescricao());
        	li.appendChild(txt);
			cell4.appendChild(li);
		}
		item.appendChild(cell1);
		item.appendChild(cell2);
		item.appendChild(cell3);
		item.appendChild(cell4);

		item.setValue(orgao);
		lbxAreasOrgao.appendChild(item);
	}
}
