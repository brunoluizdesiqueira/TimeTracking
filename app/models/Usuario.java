package models;

import com.avaje.ebean.Model;
import models.enumeradores.Papel;
import models.enumeradores.Status;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Usuario extends Model{
	@Id
	@GeneratedValue
	private long id;
	@Required(message = "Você precisa fornecer um nome!")
	private String nome;
	@Email
	@Required(message = "Você precisa fornecer um e-mail!")
	private String email;
	@Required(message = "Você precisa fornecer uma senha")
	private String senha;
	@Enumerated(EnumType.STRING)
	private Status status = Status.ATIVO;
	private boolean verificado;
	@Required(message = "Você precisa fornecer a data de cadastro!")
	private LocalDate dataCadastro = LocalDate.now();
	private String AvatarURL;
	@OneToOne(mappedBy = "usuario")
	private TokenDaApi token;
	@OneToMany(mappedBy = "usuario")
	private List<RegistroDeAcesso> acessos;
	@Enumerated(EnumType.STRING)
    private Papel papel = Papel.COLABORADOR;
	@OneToMany(mappedBy = "responsavel")
	private List<Tarefa> tarefa_responsaveis;
	@OneToMany(mappedBy = "membro")
	private List<Tarefa> tarefa_Membros;
	@OneToOne(mappedBy = "usuarioParecer")
	private TimeLine timeLine;

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
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
	public String getAvatarURL() {
		return AvatarURL;
	}
	public void setAvatarURL(String avatarURL) {
		AvatarURL = avatarURL;
	}

	public List<Tarefa> getTarefa_responsaveis() {
		return tarefa_responsaveis;
	}

	public void setTarefa_responsaveis(List<Tarefa> tarefa_responsaveis) {
		this.tarefa_responsaveis = tarefa_responsaveis;
	}

	public List<Tarefa> getTarefa_Membros() {
		return tarefa_Membros;
	}

	public void setTarefa_Membros(List<Tarefa> tarefa_Membros) {
		this.tarefa_Membros = tarefa_Membros;
	}
}

