package servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import anotacao.Perfil;
import dao.AulaDAO;
import excecao.AulaNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import excecao.UsuarioNaoEncontradoException;
import modelo.Aula;
import servico.AulaAppService;

public class AulaAppServiceImpl implements AulaAppService {
	
	@Autowired
	private AulaDAO aulaDAO;
	
	@Perfil(nomes={"admin", "user"})
	@Transactional
	public long inclui(Aula umaAula) {
		aulaDAO.inclui(umaAula);
		return umaAula.getId();
	}
	
	@Perfil(nomes={"admin"})
	@Transactional (rollbackFor={UsuarioNaoEncontradoException.class})
	public void altera(Aula umaAula) throws AulaNaoEncontradoException {
		try {
			aulaDAO.getPorIdComLock(umaAula.getId());
			aulaDAO.altera(umaAula);
		}catch(ObjetoNaoEncontradoException e) {
			throw new AulaNaoEncontradoException("Aula não encontrado");
		}

	}

	@Perfil(nomes={"admin"})
	@Transactional (rollbackFor={UsuarioNaoEncontradoException.class})
	public void exclui(Aula umaAula) throws AulaNaoEncontradoException {
		try {
			Aula aula = aulaDAO.getPorIdComLock(umaAula.getId());
			aulaDAO.exclui(aula);
		}catch(ObjetoNaoEncontradoException e) {
			throw new AulaNaoEncontradoException("Aula não encontrado");
		}

	}

	public Aula recuperaUmaAula(long numero) throws AulaNaoEncontradoException {
		try {
			return aulaDAO.getPorId(numero);
		} catch (ObjetoNaoEncontradoException e) {
			throw new AulaNaoEncontradoException("Aula não encontrado");
		}
	}

	@Override
	public Aula recuperaUmaAulaAssistida(long numero) throws AulaNaoEncontradoException {
		try {
			return aulaDAO.recuperaUmaAulaAssistida(numero);
		} catch (ObjetoNaoEncontradoException e) {
			throw new AulaNaoEncontradoException("Aula não encontrado");
		}
	}
	
	@Perfil(nomes={"admin", "user"})
	@Override
	public List<Aula> recuperaAulas() {
		return aulaDAO.recuperaLista();
	}

	

	

}
