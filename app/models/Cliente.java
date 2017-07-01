package models;

import com.avaje.ebean.Model;
import models.enumeradores.Status;
import play.data.validation.Constraints.Required;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Cliente extends Model{
	@Id
	@GeneratedValue
	private long id;

	@Required(message = "Você precisa fornecer um nome!")
	private String nome;

	private LocalDate dataCadastro = LocalDate.now();

	@Enumerated(EnumType.STRING)
	private Status status = Status.ATIVO;

	@OneToMany(mappedBy = "cliente")
	private List<Projeto> projetos;

	/**
	 * Generic query helper para entidade Cliente with id Long
	 */
	public static Finder<Long, Cliente> clientes = new Finder<>(Cliente.class);

	public Cliente(Projeto projeto) {
		this.dataCadastro = LocalDate.now();
		this.status = Status.ATIVO;
		this.projetos = new ArrayList<Projeto>();				
		this.setProjeto(projeto);
	}
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
	public List<Projeto> getProjetos() {
		return projetos;
	}
	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}
	public Projeto getProjeto(Projeto p) {
		if (this.projetos.contains(p)) {
			return this.projetos.get(this.projetos.indexOf(p));
		}
		else {
			return null;
		}
	}
	public void setProjeto(Projeto projeto) {
		this.projetos.add(projeto);
	}
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public static Map<String,String> options() {

		LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();

		for(Cliente c: clientes.orderBy("nome").findList()) {
			Long id = c.getId();
			options.put(id.toString(), c.getNome());
		}
		return options;
	}
}
