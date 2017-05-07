package daos;

import java.util.List;
import java.util.Optional;
import com.avaje.ebean.Model.Finder;
import models.Cliente;

public class ClienteDAO {

	private Finder<Long, Cliente> clientes = new Finder<>(Cliente.class);
	
    public Optional<Cliente> comId(Long id) {
		Cliente cliente = clientes.
				where().
				eq("id", id).
				findUnique();

		return Optional.ofNullable(cliente);
	}
	public Optional<Cliente> comNome(String nome) {
		Cliente cliente = clientes.
				where().
				eq("nome", nome).
				findUnique();

		return Optional.ofNullable(cliente);
	}
	public List<Cliente> todos() {
		return clientes.all();
	}

}
