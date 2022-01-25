package servico;

import java.util.List;

import excecao.AulaNaoEncontradoException;
import modelo.Aula;

public interface AulaAppService {
	long inclui(Aula umaAula);

	void altera(Aula umaAula)throws AulaNaoEncontradoException;
	
	void exclui(Aula umaAula)throws AulaNaoEncontradoException;
	
	Aula recuperaUmaAula(long numero) throws AulaNaoEncontradoException;
	
	Aula recuperaUmaAulaAssistida(long numero)throws AulaNaoEncontradoException;

    List<Aula> recuperaAulas();

}
