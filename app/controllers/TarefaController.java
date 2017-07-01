package controllers;

import daos.ClienteDAO;
import daos.ProjetoDAO;
import daos.TipoTarefaDAO;
import daos.UsuarioDAO;
import models.*;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import validadores.ValidadorDeTarefa;
import views.html.formularioDeNovaTarefa;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TarefaController extends Controller {
    @Inject
    private FormFactory formularios;
    @Inject
    private ValidadorDeTarefa validadorDeTarefa;
    @Inject
    private TipoTarefaDAO tipoTarefaDAO;
    @Inject
    private ProjetoDAO projetoDAO;
    @Inject
    private UsuarioDAO usuarioDAO;
    @Inject
    private ClienteDAO clienteDAO;

    public Result formularioDeNovaTarefa() {
        Form<Tarefa> formulario = formularios.form(Tarefa.class);
        Form<Projeto> formProjeto = formularios.form(Projeto.class);
        List<Cliente> clientes = clienteDAO.todos();
        List<TipoTarefa> tipos = tipoTarefaDAO.todos();
        List<Projeto> projetos = projetoDAO.todos();
        List<Usuario> responsaveis = usuarioDAO.todos();
        List<Usuario> membros = usuarioDAO.todos();

        return ok(views.html.formularioDeNovaTarefa.render(formulario, formProjeto, clientes, tipos, projetos, responsaveis, membros));
    }

    public Result salvaNovaTarefa() {
        Form<Tarefa> formulario = formularios.form(Tarefa.class).bindFromRequest();
        Form<Projeto> formProjeto = formularios.form(Projeto.class);
        List<Cliente> clientes = clienteDAO.todos();
        List<TipoTarefa> tipos = tipoTarefaDAO.todos();
        List<Projeto> projetos = projetoDAO.todos();
        List<Usuario> responsaveis = usuarioDAO.todos();
        List<Usuario> membros = usuarioDAO.todos();

        Tarefa tarefa = formulario.get();
        tarefa.setCriacao(LocalDate.now());

        if (validadorDeTarefa.temErros(formulario)) {
            flash("danger", "Há erros no formulário de cadastro de tarefa!");			
			return badRequest(formularioDeNovaTarefa.render(formulario, formProjeto, clientes, tipos, projetos, responsaveis, membros));
        }

        String tipoTarefa_id = formulario.field("tipoTarefa_id").valueOr("");

        if (tipoTarefa_id.isEmpty()) {
            formulario.reject("Cadastre um tipo da tarefa!");
        }
        else {

            Optional<TipoTarefa> cliente = tipoTarefaDAO.comId(Long.parseLong(tipoTarefa_id));

            if (cliente.isPresent()) {
                TipoTarefa tipo = cliente.get();
                tarefa.setTipo(tipo);
            }
        }

        String projeto_id = formulario.field("projeto_id").valueOr("");

        if (projeto_id.isEmpty()) {
            formulario.reject("O projeto não foi encontrado!");
        } else {
            Optional<Projeto> projetoSelecionado = projetoDAO.comId(Long.parseLong(projeto_id));

            if (projetoSelecionado.isPresent()) {
                Projeto projeto = projetoSelecionado.get();
                tarefa.setProjeto(projeto);
            }
        }

        tarefa.save();

        return redirect(routes.TarefaController.formularioDeNovaTarefa());
    }

}