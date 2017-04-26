package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cliente {
	@Id
	@GeneratedValue
	private long id;
	@OneToOne
	private Pessoa pessoa;	
	@OneToMany(mappedBy = "cliente")
	private List<Projeto> projetos;	

	public Cliente(Projeto projeto) {
		this.projetos = new ArrayList<Projeto>();				
		this.setProjeto(projeto);
	}
	public List<Projeto> getAllProjetos() {
		return projetos;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
