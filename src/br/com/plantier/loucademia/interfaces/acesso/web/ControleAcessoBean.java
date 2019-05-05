package br.com.plantier.loucademia.interfaces.acesso.web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.plantier.loucademia.domain.acesso.TipoAcesso;
import br.com.plantier.loucademia.domain.application.service.AcessoService;
import br.com.plantier.loucademia.domain.application.util.ValidationException;

@Named
@RequestScoped
public class ControleAcessoBean implements Serializable {

	@EJB
	private AcessoService acessoService;
	
	private String matricula;
	private Integer rg;

	public String registrarAcesso() {
		TipoAcesso tipoAcesso;
		try {
			tipoAcesso = acessoService.registrarAcesso(matricula, rg);
		} catch (ValidationException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
			return null;
		}
		
		String msg = "";
		
		if(tipoAcesso == tipoAcesso.ENTRADA ) {
			msg = "Entrada registrada";
		} else if (tipoAcesso == tipoAcesso.SAIDA) {
			msg = "Saída registrada";
		} else {
			msg = "Dados de registro de acesso inconsistente";
		}
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));		
		return null;
	}
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Integer getRg() {
		return rg;
	}

	public void setRg(Integer rg) {
		this.rg = rg;
	}

}
