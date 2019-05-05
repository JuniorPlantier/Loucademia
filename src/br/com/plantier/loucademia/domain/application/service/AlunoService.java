package br.com.plantier.loucademia.domain.application.service;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.plantier.loucademia.domain.acesso.Acesso;
import br.com.plantier.loucademia.domain.aluno.Aluno;
import br.com.plantier.loucademia.domain.aluno.Aluno.Situacao;
import br.com.plantier.loucademia.domain.aluno.AlunoRepository;
import br.com.plantier.loucademia.domain.application.util.StringUtils;
import br.com.plantier.loucademia.domain.application.util.Validation;
import br.com.plantier.loucademia.domain.application.util.ValidationException;

@Stateless
public class AlunoService {

	@EJB
	private AlunoRepository alunoRepository; 
	
	public void createOrUpdate(Aluno aluno) {
		if (StringUtils.isEmpty(aluno.getMatricula())) {
			create(aluno);
		} else {
			update(aluno);
		}
	}
	
	private void create(Aluno aluno) {
		Validation.assertNotEmpty(aluno);
		
		String maxMatricula = alunoRepository.getMaxMatriculaAno();
		aluno.gerarMatricula(maxMatricula);
		alunoRepository.store(aluno);
	}
	
	private void update(Aluno aluno) {
		Validation.assertNotEmpty(aluno);
		Validation.assertNotEmpty(aluno.getMatricula());
		
		alunoRepository.update(aluno);
	}
	
	public void delete(String matricula) {
		alunoRepository.delete(matricula);
	}
	
	public Aluno findByMatricula(String matricula) {
		return alunoRepository.findByMatricula(matricula);
	}
	
	public List<Aluno> listAluno(String matricula, String nome, Integer rg, Integer telefone) {
		if (StringUtils.isEmpty(matricula) && StringUtils.isEmpty(nome) && rg == null && telefone == null) {
			throw new ValidationException("Pelo menos um critério deve ser preenchido ");
		}
		
		return alunoRepository.listAlunos(matricula, nome, rg, telefone);
	}
	
	public List<Aluno> listSituacoesAlunos(Situacao situacao) {
		Validation.assertNotEmpty(situacao);
		return alunoRepository.listSituacaoAluno(situacao);
	}
	
	public List<Acesso> listAcessosAlunos(String matricula, LocalDate dataInicial, LocalDate dataFinal) {
		if(StringUtils.isEmpty(matricula) && dataInicial == null && dataInicial == null) {
			throw new ValidationException("Pelo menos um critério deve ser preenchido ");
		}
		
		return alunoRepository.listAcessosAlunos(matricula, dataInicial, dataFinal);
	}
}
