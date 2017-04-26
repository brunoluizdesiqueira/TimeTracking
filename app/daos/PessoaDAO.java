package daos;

import java.util.Optional;

import com.avaje.ebean.Model.Finder;

import models.Pessoa;

public class PessoaDAO {
	private Finder<Long, Pessoa> pessoas = new Finder<>(Pessoa.class);
	
	public Optional<Pessoa> comEmail(String email) {
		Pessoa pessoa = pessoas.where().eq("email", email).findUnique();
		return Optional.ofNullable(pessoa);
	}
}
