package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.avaje.ebean.Model;

import play.data.validation.Constraints.Required;

@Entity
public class Projeto extends Model {
	@Id
	@GeneratedValue
	private long id;
	@Required(message = "Você precisa fornecer um nome do projeto!")
	private String nome;
	@OneToMany(mappedBy = "projeto")
	private List<Tarefa> tarefas;
	@OneToOne
	private Cliente cliente;
	
	public Projeto(String nome) {
		super();
		this.tarefas = new ArrayList<Tarefa>();
		this.nome = nome;
	}
	public List<Tarefa> getTarefas() {
		return tarefas;
	}
	public void setTarefa(Tarefa tarefa) {
		this.tarefas.add(tarefa);
	}
	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas.addAll(tarefas);
	}
	public StringBuilder getAllDescricaoTarefas(){
		StringBuilder descricao = new StringBuilder();
		this.tarefas.forEach(t -> descricao.append("- Descricao da tarefa: " + t.getDescricao() + ' '));		
		return descricao;		
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getNome() {
		return nome;
	}
    public void setNome(String nome) {
		this.nome = nome;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
}
