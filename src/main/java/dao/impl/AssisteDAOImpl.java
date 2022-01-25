package dao.impl;

import dao.AssisteDAO;
import modelo.Assiste;

public abstract class AssisteDAOImpl extends JPADaoGenerico<Assiste, Long> implements AssisteDAO {
	public AssisteDAOImpl() {
		super(Assiste.class);
	}
	

}
