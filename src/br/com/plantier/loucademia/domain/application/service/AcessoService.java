package br.com.plantier.loucademia.domain.application.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.plantier.loucademia.domain.acesso.Acesso;
import br.com.plantier.loucademia.domain.acesso.AcessoRepository;
import br.com.plantier.loucademia.domain.acesso.TipoAcesso;
import br.com.plantier.loucademia.domain.aluno.Aluno;
import br.com.plantier.loucademia.domain.aluno.AlunoRepository;
import br.com.plantier.loucademia.domain.application.util.StringUtils;
import br.com.plantier.loucademia.domain.application.util.ValidationException;

@Stateless
public class AcessoService {

	@EJB
	private AcessoRepository acessoRepository;
	
	@EJB
	private AlunoRepository alunoRepository;
	
	public TipoAcesso registrarAcesso(String matricula, Integer rg) {
		if(StringUtils.isEmpty(matricula) && rg == null) {
			throw new ValidationException("� preciso fornecer a matr�cula ou RG do aluno.");
		}
		
		Aluno aluno;
		if(!StringUtils.isEmpty(matricula)) {
			aluno = alunoRepository.findByMatricula(matricula);
		} else {
			aluno = alunoRepository.findByRg(rg);
		}
		
		if(aluno == null) {
			throw new ValidationException("O aluno n�o foi encontrado");
		}
		
		Acesso ultimoAcesso = acessoRepository.findUltimoAcesso(aluno);
		TipoAcesso tipoAcesso;
		
		if (ultimoAcesso == null || ultimoAcesso.isEntradaSaidaPreenchidas()) {
			ultimoAcesso = new Acesso();
			ultimoAcesso.setAluno(aluno);
			tipoAcesso = ultimoAcesso.registrarAcesso();
			acessoRepository.store(ultimoAcesso);
			
		} else {
			// o update � atualizado automaticamente, pois ele est� sendo gerenciado pelo JPA e qualquer altera��o 
			// realizada automaticamente � feito a altera��o no momento em que a trasa��o termina.
			tipoAcesso = ultimoAcesso.registrarAcesso();
		}
		
		return tipoAcesso;
	}
	
}
