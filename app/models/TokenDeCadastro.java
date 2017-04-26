package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.avaje.ebean.Model;

import akka.util.Crypt;

@Entity
public class TokenDeCadastro extends Model {

	@Id
	@GeneratedValue
	private Long id;
	private String codigo;
	@OneToOne
	private Usuario usuario;
	
	public TokenDeCadastro(Usuario usuario) {
		this.usuario = usuario;
		Pessoa pessoaUsuario = usuario.getPessoa();
		this.codigo = Crypt.sha1(pessoaUsuario.getNome()+pessoaUsuario.getEmail()+Crypt.generateSecureCookie());		
	}
	public Long getId() {
		return id;
	}
	public String getCodigo() {
		return codigo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	
}
