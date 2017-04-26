package models;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.avaje.ebean.Model;

/**
 * @author Bruno
 * Classe TimeTracking responsável por controlar as diversas alterações da linha do tempo
 *
 */
@Entity
@Table(name = "timeTracking")
public class TimeTracking extends Model{
	@Id
	@GeneratedValue
	private long id;
	@OneToMany(mappedBy = "timeTracking")
	private List<TimeLine> timeLine;
	@OneToOne(mappedBy = "timeTracking")
	private Tarefa tarefa;
	
	public TimeTracking() {
	    this.timeLine = new ArrayList<TimeLine>();
	}
	public Duration getTempoTotal() {	
	  return Duration.between(this.timeLine.get(0).getInstante(), this.timeLine.get(this.timeLine.size() -1).getInstante());
	}
	public long getId() {
		return id;
	}
	public List<TimeLine> getTimeLine() {
		return timeLine;
	}
	public void setTimeLine(List<TimeLine> timeLine) {
		this.timeLine = timeLine;
	}
	public void registrar(TipoTransicao transicao, String descricao, Usuario usuarioParecer){
	   timeLine.add(new TimeLine(transicao, descricao, usuarioParecer));		
	}
}
