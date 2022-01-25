package dao;

import java.util.List;

import anotacao.RecuperaListaDeAulas;
import anotacao.RecuperaObjeto;
import excecao.ObjetoNaoEncontradoException;
import modelo.Aula;

public interface AulaDAO extends DaoGenerico<Aula, Long>{
	@RecuperaListaDeAulas
	List<Aula> recuperaLista();

	@RecuperaObjeto
	Aula recuperaUmaAulaAssistida(long numero)throws ObjetoNaoEncontradoException;

}
