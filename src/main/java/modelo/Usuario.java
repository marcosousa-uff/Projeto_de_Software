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


@NamedQueries({@NamedQuery(name = "Usuario.recuperaLista", query = "select u from Usuario u order by u.id"),
	@NamedQuery(name = "Usuario.recuperaUmUsuarioEAulasAssitidas", query = "select u from Usuario u left outer join fetch u.assistiu where u.id = ?1") })

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="usuario")

public class Usuario {
	private Long id;
	private String nome;
	private String login;
	private String senha;
	private int versao;
	
	private List<Assiste> assistiu = new ArrayList<Assiste>();
	
	public Usuario() {
		
	}

	public Usuario(String nome, String login,String senha) {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
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
	@OneToMany(mappedBy = "usuario")
	@OrderBy
	public List<Assiste> getAssistiu() {
		return assistiu;
	}
	public void setAssistiu(List<Assiste> assistiu) {
		this.assistiu = assistiu;
	}

}
