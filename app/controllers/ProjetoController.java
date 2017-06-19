package controllers;


import daos.ClienteDAO;
import models.Cliente;
import models.Projeto;
import play.data.Form;
import play.data.FormFactory;
import play.db.ebean.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import validadores.ValidadorDeProjeto;
import views.html.formularioDeNovoProjeto;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class ProjetoController extends Controller {

    @Inject
    private FormFactory formularios;
    @Inject
    private ValidadorDeProjeto validadorDeProjeto;
    @Inject
    private ClienteDAO clienteDAO;

    public Result formularioDeNovoProjeto() {
        Form<Projeto> formulario = formularios.form(Projeto.class);
        List<Cliente> clientes = clienteDAO.todos();
        return ok(formularioDeNovoProjeto.render(formulario, clientes));
    }

    @Transactional
    public Result salvaNovoProjeto() {
        Form<Projeto> formulario = formularios.form(Projeto.class).bindFromRequest();
        List<Cliente> clientes = clienteDAO.todos();
        Projeto projeto = formulario.get();

        if (validadorDeProjeto.temErros(formulario)) {
            flash("danger", "Há erros no formulário de cadastro de projeto!");
            return badRequest(formularioDeNovoProjeto.render(formulario, clientes));
        }

        String cliente_id = formulario.field("cliente_id").valueOr("");

        if (cliente_id.isEmpty()) {
            formulario.reject("Cliente não foi encontrado!");
        } else {
            Optional<Cliente> clienteSelecionado = clienteDAO.comId(Long.parseLong(cliente_id));

            if (clienteSelecionado.isPresent()) {
                Cliente cliente = clienteSelecionado.get();
                projeto.setCliente(cliente);
            }
        }

        projeto.save();
        flash("success", "Gravado com sucesso!");
        return redirect(routes.TarefaController.formularioDeNovaTarefa());
    }
}