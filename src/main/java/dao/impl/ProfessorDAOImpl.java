package dao.impl;

import org.springframework.stereotype.Repository;

import dao.ProfessorDAO;
import modelo.Professor;

@Repository
public abstract class ProfessorDAOImpl extends JPADaoGenerico<Professor, Long> implements ProfessorDAO {
	public ProfessorDAOImpl() {
		super(Professor.class);
	}

}
