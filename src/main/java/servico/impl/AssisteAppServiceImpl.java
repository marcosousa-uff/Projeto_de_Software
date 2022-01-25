package servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import anotacao.Perfil;
import dao.AssisteDAO;
import excecao.AulaAssistidaNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import excecao.UsuarioNaoEncontradoException;
import modelo.Assiste;
import servico.AssisteAppService;

public class AssisteAppServiceImpl implements AssisteAppService {
	
	@Autowired
	private AssisteDAO assisteDAO;

	@Perfil(nomes={"admin", "user"})
	@Transactional
	public long inclui(Assiste aulaAssistida) {
		assisteDAO.inclui(aulaAssistida);
		return aulaAssistida.getId();
	}

	@Perfil(nomes={"admin"})
	@Transactional (rollbackFor={UsuarioNaoEncontradoException.class})
	public void altera(Assiste aulaAssistida) throws AulaAssistidaNaoEncontradoException {
		try {
			assisteDAO.getPorIdComLock(aulaAssistida.getId());
			assisteDAO.altera(aulaAssistida);
		}catch(ObjetoNaoEncontradoException e) {
			throw new AulaAssistidaNaoEncontradoException("Historico de aula não encontrado");
		}
		

	}

	@Perfil(nomes={"admin"})
	@Transactional (rollbackFor={UsuarioNaoEncontradoException.class})
	public void exclui(Assiste aulaAssistida) throws AulaAssistidaNaoEncontradoException {
		try {
			Assiste assiste = assisteDAO.getPorIdComLock(aulaAssistida.getId());
			assisteDAO.exclui(assiste);
		}catch(ObjetoNaoEncontradoException e) {
			throw new AulaAssistidaNaoEncontradoException("Historico de aula não encontrado");
		}

	}

	@Override
	public Assiste recuperaUmaAulaAssistida(long numero) throws AulaAssistidaNaoEncontradoException {
		try {
			return assisteDAO.getPorId(numero);
		}catch(ObjetoNaoEncontradoException e) {
			throw new AulaAssistidaNaoEncontradoException("Historico de aula não encontrado");
		}
	}

	@Perfil(nomes={"admin", "user"})
	@Override
	public List<Assiste> recuperaAulasAssistidas() {
		return assisteDAO.recuperaLista();
	}

}
