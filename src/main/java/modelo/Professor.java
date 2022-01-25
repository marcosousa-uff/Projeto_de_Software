package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@NamedQueries({ @NamedQuery(name = "Professor.recuperaLista", query = "select p from Professor p order by p.id"),
		@NamedQuery(name = "Professor.recuperaUmProfessorEAulas", query = "select p from Professor p left outer join fetch p.aulas where p.id = ?1") })

@Entity
@Table(name = "professor")
public class Professor {
	private Long id;
	private String nome;
	private String login;
	private String senha;
	private int versao;
	
	private List<Aula> aulas = new ArrayList<Aula>();

	public Professor() {

	}

	public Professor(String nome, String login, String senha) {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	@SuppressWarnings("unused")
	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Version
	public int getVersao() {
		return versao;
	}

	public void setVersao(int versao) {
		this.versao = versao;
	}

	@OneToMany(mappedBy = "professor")
	@OrderBy
	public List<Aula> getAulas() {
		return aulas;
	}
	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}
	

}
