package mz.com.ciuem.inamar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mz.com.ciuem.model.Area;
import mz.com.ciuem.model.Orgao;
import mz.com.ciuem.model.Peticao;
import mz.com.ciuem.model.RequisitoDeTipoPeticao;

public class GeradorDeTabelas {

	public static void main(String[] args) {
		OrgaoDAO orgaoDao = new OrgaoDAO();

		Peticao peticoes = PeticaoDAO.getById(1);
		
		System.out.println("Tamanho  = " + peticoes.getRequisitoDeTipoPeticao().size());
		
		for (RequisitoDeTipoPeticao r : peticoes.getRequisitoDeTipoPeticao())
			System.out.println(r.getDescricao() + "   " + r.getPeticao().getDescricao());
	}

}
