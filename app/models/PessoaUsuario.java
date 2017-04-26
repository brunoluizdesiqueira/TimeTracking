package models;

import java.time.LocalDate;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;

public class PessoaUsuario {
	@Required(message = "Você precisa fornecer o nome!")
	public String nome;
	@Email
	@Required(message = "Você precisa fornecer o E-mail!")
	public String email;	
	public StatusPessoa status;
	public LocalDate dataCadastro; 

	@Required(message = "Você precisa fornecer uma senha")
	private String senha;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public StatusPessoa getStatus() {
		return status;
	}

	public void setStatus(StatusPessoa status) {
		this.status = status;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
