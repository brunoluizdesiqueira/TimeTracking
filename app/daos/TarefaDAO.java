package daos;

import com.avaje.ebean.Model.Finder;
import models.Tarefa;

import java.util.List;
import java.util.Optional;

public class TarefaDAO {
    private Finder<Long, Tarefa> tarefas = new Finder<>(Tarefa.class);	
    public Optional<Tarefa> comId(Long id) {
		Tarefa tarefa = tarefas.byId(id);
		return Optional.ofNullable(tarefa);
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