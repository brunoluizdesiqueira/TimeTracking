package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.avaje.ebean.Model;

import play.data.validation.Constraints.Required;

@Entity
public class Usuario extends Model{
	@Id
	@GeneratedValue
	private long id;
	@Required(message = "Você precisa fornecer uma senha")
	private String senha;	
	private boolean verificado;
	@OneToOne
	private Pessoa pessoa;	
	@OneToOne(mappedBy = "usuario")
	private TokenDaApi token;
	@OneToMany(mappedBy = "usuario")
	private List<RegistroDeAcesso> acessos;	
	@Enumerated(EnumType.STRING)
    private Papel papel = Papel.COLABORADOR;
	@OneToOne(mappedBy = "responsavel")
	private Tarefa tarefa_responsavel;
	@OneToOne(mappedBy = "membro")
	private Tarefa tarefa_Membro;
	@OneToOne(mappedBy = "usuarioParecer")
	private TimeLine timeLine;


	public void setAcessos(List<RegistroDeAcesso> acessos) {
		this.acessos = acessos;
	}
	public Papel getPapel() {
		return papel;
	}
	public void setPapel(Papel papel) {
		this.papel = papel;
	}
	public long getId() {
		return id;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public Tarefa getTarefa_responsavel() {
		return tarefa_responsavel;
	}
	public void setTarefa_responsavel(Tarefa tarefa_responsavel) {
		this.tarefa_responsavel = tarefa_responsavel;
	}
	public Tarefa getTarefa_Membro() {
		return tarefa_Membro;
	}
	public void setTarefa_Membro(Tarefa tarefa_Membro) {
		this.tarefa_Membro = tarefa_Membro;
	}
	public TimeLine getTimeLine() {
		return timeLine;
	}
	public void setTimeLine(TimeLine timeLine) {
		this.timeLine = timeLine;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public TokenDaApi getToken() {
		return token;
	}
	
	public void setToken(TokenDaApi token) {
		this.token = token;
	}
	public List<RegistroDeAcesso> getAcessos() {
		return acessos;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isVerificado() {
		return verificado;
	}
	
	public void setVerificado(boolean verificado) {
		this.verificado = verificado;
	}
	
}

