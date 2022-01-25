package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@NamedQueries({@NamedQuery(name = "Aula.recuperaLista", query = "select a from Aula a order by a.id"),
	@NamedQuery(name = "Aula.recuperaUmaAulaComProfessor", query = "select a from Aula a left outer join fetch a.professor where a.id = ?1"),
	@NamedQuery(name = "Aula.recuperaUmaAulaAssistida", query = "select a from Aula a left outer join fetch a.assistiu where a.id = ?1") })

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="aula")
public class Aula {
	private Long id;
	private int numero;
	private int idProfessor;
	private String curso;
	private String topico;
	private String categoria;
	private String link;
	private int versao;
	
	private Professor professor;
	private List<Assiste> assistiu = new ArrayList<Assiste>();
	
	public Aula() {
		
	}
	public Aula(int numero, int idProfessor, String curso,String topico, String categoria, String link) {
		this.numero = numero;
		this.idProfessor = idProfessor;
		this.categoria = categoria;
		this.topico = topico;
		this.curso = curso;
		this.link = link;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	public Long getId() {
		return id;
	}

	public int getNumero() {
		return numero;
	}

	public int getIdProfessor() {
		return idProfessor;
	}

	public String getCurso() {
		return curso;
	}

	public String getTopico() {
		return topico;
	}

	public String getCategoria() {
		return categoria;
	}

	public String getLink() {
		return link;
	}

	@Version
	public int getVersao() {
		return versao;
	}

	
	@SuppressWarnings("unused")
	public void setId(Long id) {
		this.id = id;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	public void setIdProfessor(int idProfessor) {
		this.idProfessor = idProfessor;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public void setTopico(String topico) {
		this.topico = topico;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setVersao(int versao) {
		this.versao = versao;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDPROFESSOR")
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	@OneToMany(mappedBy = "aula")
	@OrderBy
	public List<Assiste> getAssistiu() {
		return assistiu;
	}
	public void setAssistiu(List<Assiste> assistiu) {
		this.assistiu = assistiu;
	}
	
	
	

}
