package daos;

import java.util.List;
import java.util.Optional;
import com.avaje.ebean.Model.Finder;
import models.Projeto;

public class ProjetoDAO {
    private Finder<Long, Projeto> projetos = new Finder<>(Projeto.class);

    public Optional<Projeto> comId(long id) {
        Projeto projeto = projetos.byId(id);
        return Optional.ofNullable(projeto);
    }
    public Optional<Projeto> comNome(String nome) {
        Projeto projeto = projetos.
                    where().
                    eq("nome", nome).
                    findUnique();
        return Optional.ofNullable(projeto);            
    }
    public List<Projeto> todos() {
        return projetos.all();
    }
}