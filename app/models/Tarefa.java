package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints.Required;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Tarefa extends Model{
	@Id
	@GeneratedValue
	private long id;
	@Required(message = "Você precisa fornecer o título da tarefa!")
    private String titulo;
	@Required(message = "Você precisa fornecer a descrição da tarefa!")
	private String descricao;
	@ManyToOne
	private TipoTarefa tipo;
	@ManyToOne
	private Usuario responsavel;
	@ManyToOne
	private Usuario membro;

	private LocalDate criacao = LocalDate.now();
	@OneToOne
	private TimeTracking timeTracking;
	@ManyToOne
	private Projeto projeto; 
	@OneToMany(mappedBy = "tarefa")
	private List<Tag> tags;

	public Tarefa(String descricao, Usuario responsavel, Usuario membro) {
		this.descricao = descricao;
		this.responsavel = responsavel;
		this.membro = membro;
		this.timeTracking = new TimeTracking();
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	public void setTimeTracking(TimeTracking timeTracking) {
		this.timeTracking = timeTracking;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public TipoTarefa getTipo() {
		return tipo;
	}
	public void setTipo(TipoTarefa tipo) {
		this.tipo = tipo;
	}
	public Usuario getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalDate getCriacao() {
		return criacao;
	}
	public void setCriacao(LocalDate criacao) {
		this.criacao = criacao;
	}
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	public Usuario getMembro() {
		return membro;
	}
	public void setMembro(Usuario membro) {
		this.membro = membro;
	}
    public TimeTracking getTimeTracking() {
		return timeTracking;
	}
}
