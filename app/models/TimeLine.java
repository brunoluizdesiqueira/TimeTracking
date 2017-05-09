package models;

import com.avaje.ebean.Model;
import models.enumeradores.TipoTransicao;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Classe responsável por registrar o momento em que ocorre cada transição
 * realizado pela pessoa do parecer 
 * @author Bruno Siqueira
 *
 */
@Entity
@Table(name = "timeline")
public class TimeLine extends Model{
	@Id
	@GeneratedValue
	private long id;
	private LocalDateTime instante;
	@Enumerated(EnumType.STRING)
	private TipoTransicao transicao;
	private String descricao;
	@OneToOne
	private Usuario usuarioParecer;
	@ManyToOne
	private TimeTracking timeTracking;

	public TimeLine() {
		new ArrayList<LocalDateTime>();
	}
	public TimeLine(TipoTransicao transicao, String descricao, Usuario usuarioParecer) {
		super();
		this.transicao = transicao;
		this.descricao = descricao;
		this.usuarioParecer = usuarioParecer;
		this.instante = LocalDateTime.now();	
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public TipoTransicao getTransicao() {
		return transicao;
	}
	public void setTransicao(TipoTransicao transicao) {
		this.transicao = transicao;
	}	
	public LocalDateTime getInstante() {
		return instante;
	}
	public void setInstante(LocalDateTime instante) {
		this.instante = instante;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Usuario getUsuarioParecer() {
		return this.usuarioParecer;
	}
	public void setUsuarioParecer(Usuario usuarioParecer) {
		this.usuarioParecer = usuarioParecer;
	}
	public void iniciaCronometro() {
		// Método apenas para exemplo, em breve estarei removendo
		Timer timer = null;

		final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

		if (timer == null) {
			timer = new Timer();
			TimerTask tarefa = new TimerTask() {
				public void run() {
					try {
						System.out.println("Hora: " + format.format(new Date().getTime()));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			timer.scheduleAtFixedRate(tarefa, 0, 1000);
		}
	}
}
