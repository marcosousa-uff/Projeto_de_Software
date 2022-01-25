package servico;

import java.util.List;

import excecao.AulaAssistidaNaoEncontradoException;
import modelo.Assiste;

public interface AssisteAppService {
	
	long inclui(Assiste aulaAssistida);
	
	void altera(Assiste aulaAssistida)throws AulaAssistidaNaoEncontradoException;
	
	void exclui(Assiste aulaAssistida)throws AulaAssistidaNaoEncontradoException;
	
	Assiste recuperaUmaAulaAssistida(long numero) throws AulaAssistidaNaoEncontradoException;

    List<Assiste> recuperaAulasAssistidas();

}
