package daos;

import com.avaje.ebean.Model.Finder;
import models.Cliente;

import java.util.List;
import java.util.Optional;

public class ClienteDAO {

	public static Finder<Long, Cliente> clientes = new Finder<>(Cliente.class);
	
    public Optional<Cliente> comId(Long id) {
		Cliente cliente = clientes.byId(id);
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
