package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.avaje.ebean.Model;

import play.data.validation.Constraints.Required;

@Entity	
public class TipoTarefa extends Model{
	@Id
	@GeneratedValue
	private long id;
	@Required(message = "Você precisa fornecer um nome do tipo da tarefa!")
	private String nome;
	@Required(message = "Você precisa fornecer o tempo estimado de duração para esse tipo de tarefa!")
	private String tempoEstimado;
	@OneToOne(mappedBy = "tipo")
	private Tarefa tarefa;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTempoEstimado() {
		return tempoEstimado;
	}
	public void setTempoEstimado(String tempoEstimado) {
		this.tempoEstimado = tempoEstimado;
	}
}
