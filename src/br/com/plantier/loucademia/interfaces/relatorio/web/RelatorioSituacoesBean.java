package br.com.plantier.loucademia.interfaces.relatorio.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.plantier.loucademia.domain.aluno.Aluno;
import br.com.plantier.loucademia.domain.aluno.Aluno.Situacao;
import br.com.plantier.loucademia.domain.application.service.AlunoService;

@Named
@SessionScoped
public class RelatorioSituacoesBean implements Serializable {

	private Situacao situacao;
	
	private List<Aluno> alunos;
	
	private FacesContext context = FacesContext.getCurrentInstance();
    private Map<String, String> requestParamsMap = context.getExternalContext().getRequestParameterMap();
	
	@EJB
	private AlunoService alunoService;
	
	public void check() {
		
			String clear = requestParamsMap.get("clear");
			
			if(clear != null && Boolean.valueOf(clear)) {
				situacao = null;
				alunos = null;
			}
		
	}
	
	public String gerarRelatorio() {
		alunos = alunoService.listSituacoesAlunos(situacao);
		return null;
	}
	
	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
}
