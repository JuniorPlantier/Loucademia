package br.com.plantier.loucademia.interfaces.aluno.web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.plantier.loucademia.domain.aluno.Aluno;
import br.com.plantier.loucademia.domain.application.service.AlunoService;
import br.com.plantier.loucademia.domain.application.util.StringUtils;

@Named
@RequestScoped
public class AlunoBean implements Serializable {

	@EJB
	private AlunoService alunoService;
	
	private Aluno aluno = new Aluno();

	private String matricula;
	
	private String titulo = "Novo Aluno";
	
	private FacesContext facesContext = FacesContext.getCurrentInstance();

	public void carregar() {
		if(!StringUtils.isEmpty(matricula)) {
			aluno = alunoService.findByMatricula(matricula);
			titulo = "Alterar Aluno";
		}
	}
	
	public String gravar() {
		alunoService.createOrUpdate(aluno);
		facesContext.addMessage(null, new FacesMessage("Dados gravados com sucesso"));
		return null;
	}
	
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getTitulo() {
		return titulo;
	}

}
