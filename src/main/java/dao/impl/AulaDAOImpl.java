package dao.impl;

import org.springframework.stereotype.Repository;

import dao.AulaDAO;
import modelo.Aula;

@Repository
public abstract class AulaDAOImpl extends JPADaoGenerico<Aula, Long> implements AulaDAO {
	public AulaDAOImpl() {
		super(Aula.class);
	}

}
