package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints.Required;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Projeto extends Model {
	@Id
	@GeneratedValue
	private long id;
	@Required(message = "Você precisa fornecer um nome do projeto!")
	private String nome;
	@Required(message = "Você precisa fornecer uma descrição para o projeto")
	@Column(length=500)
	private String descricao;
	@OneToMany(mappedBy = "projeto")
	private List<Tarefa> tarefas;
	@ManyToOne
	private Cliente cliente;

	/**
	 * Generic query helper para entidade Projeto with id Long
	 */
	public static Finder<Long, Projeto> projetos = new Finder<>(Projeto.class);

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Projeto(String nome) {
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
	public String getDescricao() {
       return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static Map<String,String> options() {

		LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();

		for(Projeto p: projetos.orderBy("nome").findList()) {
			Long id = p.getId();
			options.put(id.toString(), p.getNome());
		}
		return options;
	}
}
