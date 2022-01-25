package servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import anotacao.Perfil;
import dao.ProfessorDAO;
import excecao.ObjetoNaoEncontradoException;
import excecao.ProfessorNaoEncontradoException;
import modelo.Professor;
import servico.ProfessorAppService;

public class ProfessorAppServiceImpl implements ProfessorAppService {
	
	@Autowired
	private ProfessorDAO professorDAO;

	@Perfil(nomes={"admin", "user"})
	@Transactional
	public long inclui(Professor umProfessor) {
		professorDAO.inclui(umProfessor);
		return umProfessor.getId();
	}

	@Perfil(nomes={"admin"})
	@Transactional (rollbackFor={ProfessorNaoEncontradoException.class})
	public void altera(Professor umProfessor) throws ProfessorNaoEncontradoException {
		try {
			professorDAO.getPorIdComLock(umProfessor.getId());
			professorDAO.altera(umProfessor);
		}catch(ObjetoNaoEncontradoException e){
			throw new  ProfessorNaoEncontradoException("Professor não encontrado");
		}
	}

	@Perfil(nomes={"admin"})
	@Transactional (rollbackFor={ProfessorNaoEncontradoException.class})
	public void exclui(Professor umProfessor) throws ProfessorNaoEncontradoException {
		try {
			Professor professor = professorDAO.getPorIdComLock(umProfessor.getId());
			professorDAO.exclui(professor);
		}catch(ObjetoNaoEncontradoException e){
			throw new  ProfessorNaoEncontradoException("Professor não encontrado");
		}

	}

	@Override
	public Professor recuperaUmProfessor(long numero) throws ProfessorNaoEncontradoException {
		try {
			return professorDAO.getPorId(numero);
		}catch(ObjetoNaoEncontradoException e){
			throw new  ProfessorNaoEncontradoException("Professor não encontrado");
		}
	}

	@Override
	public Professor recuperaUmProfessorEAulas(long numero) throws ProfessorNaoEncontradoException {
		try {
			return professorDAO.recuperaUmProfessorEAulas(numero);
		}catch(ObjetoNaoEncontradoException e){
			throw new  ProfessorNaoEncontradoException("Professor não encontrado");
		}
	}
	
	@Perfil(nomes={"admin", "user"})
	@Override
	public List<Professor> recuperaProfessores() {
		return professorDAO.recuperaLista();
	}

	

}
