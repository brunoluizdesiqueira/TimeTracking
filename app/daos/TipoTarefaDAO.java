package daos;

import com.avaje.ebean.Model;
import models.TipoTarefa;
import java.util.List;
import java.util.Optional;

/**
 * Created by Bruno on 07/05/17.
 */
public class TipoTarefaDAO {
    private Model.Finder<Long, TipoTarefa> tipoTarefas = new Model.Finder<>(TipoTarefa.class);

    public Optional<TipoTarefa> comId(Long id) {
        TipoTarefa tipoTarefa = tipoTarefas.byId(id);
        return Optional.ofNullable(tipoTarefa);
    }
    public Optional<TipoTarefa> comNome(String nome) {
        TipoTarefa tipoTarefa = tipoTarefas.
                where().
                eq("nome", nome).
                findUnique();

        return Optional.ofNullable(tipoTarefa);
    }
    public List<TipoTarefa> todos() {
        return tipoTarefas.all();
    }
}
