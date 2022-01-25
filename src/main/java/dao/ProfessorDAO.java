package dao;

import java.util.List;

import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import excecao.ObjetoNaoEncontradoException;
import modelo.Professor;

public interface ProfessorDAO extends DaoGenerico<Professor,Long> {
	@RecuperaLista
	List<Professor> recuperaLista();

	@RecuperaObjeto
	Professor recuperaUmProfessorEAulas(long numero)throws ObjetoNaoEncontradoException;

}
