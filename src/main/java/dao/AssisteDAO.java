package dao;

import java.util.List;

import anotacao.RecuperaAulasAssistidas;
import modelo.Assiste;

public interface AssisteDAO extends DaoGenerico<Assiste, Long> {
	@RecuperaAulasAssistidas
	List<Assiste> recuperaLista();

}
