package models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;

import com.avaje.ebean.Model;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;


@Entity
//@Table(uniqueConstraints={@UniqueConstraint(columnNames={"nome"}), @UniqueConstraint(columnNames={"email"})})
public class Pessoa extends Model{
	@Id
	@GeneratedValue
	private long id;
	@Required(message = "Você precisa fornecer um nome!")
	private String nome;
	@Email
	private String email;	
	@Enumerated(EnumType.STRING)
	private StatusPessoa status = StatusPessoa.ATIVO;
	@Required(message = "Você precisa fornecer a data de cadastro!")
	private LocalDate dataCadastro;
	@OneToOne(mappedBy = "pessoa")
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	@OneToOne(mappedBy = "pessoa")
	private Cliente cliente;
		
	public Pessoa() {
		this.dataCadastro = LocalDate.now();
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public StatusPessoa getStatus() {
		return status;
	}
	public void setStatus(StatusPessoa status) {
		this.status = status;
	}
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
