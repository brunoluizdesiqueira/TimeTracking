package daos;

import com.avaje.ebean.Model.Finder;
import models.Cliente;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

	public static Map<String,String> options() {

        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();

		for(Cliente c: clientes.orderBy("nome").findList()) {
			Long id = c.getId();
		    options.put(id.toString(), c.getNome());
		}
		return options;
	}
}
