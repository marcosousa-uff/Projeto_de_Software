package servico;

import java.util.List;

import excecao.UsuarioNaoEncontradoException;
import modelo.Usuario;

public interface UsuarioAppService {
	
	long inclui(Usuario umUsuario);

	void altera(Usuario umUsuario)throws UsuarioNaoEncontradoException;

	void exclui(Usuario umUsuario)throws UsuarioNaoEncontradoException;

	Usuario recuperaUmUsuario(long numero) throws UsuarioNaoEncontradoException;
	
	Usuario recuperaUmUsuarioEAulasAssitidas(long numero) throws UsuarioNaoEncontradoException;
	
    List<Usuario> recuperaUsuarios();

}
