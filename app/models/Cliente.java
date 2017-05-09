package models;

import com.avaje.ebean.Model;
import models.enumeradores.Status;
import play.data.validation.Constraints.Required;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
}
