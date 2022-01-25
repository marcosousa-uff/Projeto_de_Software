package dao;

import java.util.List;

import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import excecao.ObjetoNaoEncontradoException;
import modelo.Usuario;

public interface UsuarioDAO extends DaoGenerico<Usuario, Long> {
	@RecuperaLista
	List<Usuario> recuperaLista();
	
	@RecuperaObjeto
	Usuario recuperaUmUsuarioEAulasAssitidas(long numero)throws ObjetoNaoEncontradoException;

}
