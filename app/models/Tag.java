package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.avaje.ebean.Model;
import play.data.validation.Constraints.Required;

@Entity
public class Tag extends Model{
	@Id
	@GeneratedValue
	private long id;
	@Required(message = "Você precisa fornecer a tag!")
	private String tag;
	@ManyToOne
	private Tarefa tarefa;

	public Tarefa getTarefa() {
		return tarefa;
	}
	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
}
