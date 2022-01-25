package modelo;

import java.sql.Date;
import java.sql.Time;

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
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@NamedQueries({@NamedQuery(name = "Assiste.recuperaLista", query = "select a from Assiste a order by a.id"),
	@NamedQuery(name = "Assiste.recuperaUmaAulaAssistidaProUsuario", query = "select a from Assiste a left outer join fetch a.aula where a.id = ?1"),
	@NamedQuery(name = "Assiste.recuperaUmUsuarioQueAssistiuAula", query = "select a from Assiste a left outer join fetch a.usuario where a.id = ?1") })
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="assiste")
public class Assiste {
	private Long id;
	private int idUsuario;
	private int idAula;
	private Date data;
	private Time hora;
	private int versao;
	
	private Aula aula;
	private Usuario usuario;
	
	public Assiste(){
		
	}
	
	public Assiste(int idUsuario, int idAula, Date data, Time hora) {
		this.idUsuario = idUsuario;
		this.idAula = idAula;
		this.data = data;
		this.hora = hora;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	public Long getId() {
		return id;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public int getIdAula() {
		return idAula;
	}
	public Date getData() {
		return data;
	}
	public Time getHora() {
		return hora;
	}
	@Version
	public int getVersao() {
		return versao;
	}

	public void setVersao(int versao) {
		this.versao = versao;
	}

	@SuppressWarnings("unused")
	public void setId(Long id) {
		this.id = id;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public void setIdAula(int idAula) {
		this.idAula = idAula;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDAULA")
	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDUSUARIO")
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
}
