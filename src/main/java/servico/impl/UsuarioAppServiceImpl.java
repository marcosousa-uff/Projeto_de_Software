package servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import anotacao.Perfil;
import dao.UsuarioDAO;
import excecao.ObjetoNaoEncontradoException;
import excecao.UsuarioNaoEncontradoException;
import modelo.Usuario;
import servico.UsuarioAppService;

public class UsuarioAppServiceImpl implements UsuarioAppService {
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Perfil(nomes={"admin", "user"})
	@Transactional
	public long inclui(Usuario umUsuario) {
		usuarioDAO.inclui(umUsuario);
		return umUsuario.getId();
	}

	@Perfil(nomes={"admin"})
	@Transactional (rollbackFor={UsuarioNaoEncontradoException.class})
	public void altera(Usuario umUsuario) throws UsuarioNaoEncontradoException {
		try {
			usuarioDAO.getPorIdComLock(umUsuario.getId());
			usuarioDAO.altera(umUsuario);
		}catch(ObjetoNaoEncontradoException e) {
			throw new UsuarioNaoEncontradoException("Usuario não encontrado");
		}

	}
	
	@Perfil(nomes={"admin"})
	@Transactional (rollbackFor={UsuarioNaoEncontradoException.class})
	public void exclui(Usuario umUsuario) throws UsuarioNaoEncontradoException {
		try {
			Usuario usuario = usuarioDAO.getPorIdComLock(umUsuario.getId());
			usuarioDAO.exclui(usuario);
		}catch(Exception e) {
			throw new UsuarioNaoEncontradoException("Usuario não encontrado");
		}

	}

	public Usuario recuperaUmUsuario(long numero) throws UsuarioNaoEncontradoException {
		try {
			return usuarioDAO.getPorId(numero);
		}catch(ObjetoNaoEncontradoException e) {
			throw new UsuarioNaoEncontradoException("Usuario não encontrado");
		}
	}
	
	@Override
	public Usuario recuperaUmUsuarioEAulasAssitidas(long numero) throws UsuarioNaoEncontradoException {
		try {
			return usuarioDAO.recuperaUmUsuarioEAulasAssitidas(numero);
		}catch(ObjetoNaoEncontradoException e) {
			throw new UsuarioNaoEncontradoException("Usuario não encontrado");
		}
	}

	@Perfil(nomes={"admin", "user"})
	@Override
	public List<Usuario> recuperaUsuarios() {
		return usuarioDAO.recuperaLista();
	}

	

}
