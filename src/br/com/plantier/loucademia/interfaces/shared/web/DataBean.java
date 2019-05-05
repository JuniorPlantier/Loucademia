package br.com.plantier.loucademia.interfaces.shared.web;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.com.plantier.loucademia.domain.aluno.Aluno.Sexo;
import br.com.plantier.loucademia.domain.aluno.Aluno.Situacao;
import br.com.plantier.loucademia.domain.aluno.Estado;
import br.com.plantier.loucademia.domain.application.service.DataService;

@Named
@ApplicationScoped
public class DataBean implements Serializable {

	@EJB
	private DataService dataService;
	
	public Sexo[] getSexos() {
		return dataService.getSexos();
	}
	
	public Situacao[] getSituacoes() {
		return dataService.getSituacoes();
	}
	
	public List<Estado> getEstados() {
		return dataService.listEstados();
	}
	
	public String formatTelefone(Integer ddd, Integer numero) {
		if (ddd == null || numero == null) {
			return "";
		}
		return "(" + ddd + ")" + numero;
	}
}
