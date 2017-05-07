package daos;

import java.util.List;
import java.util.Optional;
import com.avaje.ebean.Model.Finder;
import models.Tarefa;

public class TarefaDAO {
    private Finder<Long, Tarefa> tarefas = new Finder<>(Tarefa.class);	
    public Optional<Tarefa> comId(Long id) {
		Tarefa cliente = tarefas.
				where().
				eq("id", id).
				findUnique();

		return Optional.ofNullable(cliente);
	}
	public Optional<Tarefa> comTitulo(String titulo) {
		Tarefa tarefa = tarefas.
				where().
				eq("titulo", titulo).
				findUnique();

		return Optional.ofNullable(tarefa);
	}
	public List<Tarefa> todos() {
		return tarefas.all();
	}
}