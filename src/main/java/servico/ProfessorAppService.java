package servico;

import java.util.List;

import excecao.ProfessorNaoEncontradoException;
import modelo.Professor;

public interface ProfessorAppService {
	long inclui(Professor umProfessor);
	
	void altera(Professor umProfessor)throws ProfessorNaoEncontradoException;
	
	void exclui(Professor umProfessor)throws ProfessorNaoEncontradoException;
	
	Professor recuperaUmProfessor(long numero) throws ProfessorNaoEncontradoException;
	
	Professor recuperaUmProfessorEAulas(long numero) throws ProfessorNaoEncontradoException;

    List<Professor> recuperaProfessores();


}
