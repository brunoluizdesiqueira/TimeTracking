package controllers;

import javax.inject.Inject;
import models.Tarefa;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import validadores.ValidadorDeTarefa;
import views.html.*;

public class TarefaController extends Controller {
    @Inject
    private FormFactory formularios;
    @Inject
    private ValidadorDeTarefa validadorDeTarefa;

    public Result formularioDeNovaTarefa() {
        Form<Tarefa> formulario = formularios.form(Tarefa.class);
		return ok(views.html.formularioDeNovaTarefa.render(formulario));
    }

    public Result salvaNovaTarefa() {
        Form<Tarefa> formulario = formularios.form(Tarefa.class).bindFromRequest();

        Tarefa tarefa = formulario.get();

        if (validadorDeTarefa.temErros(formulario)) {
            flash("danger", "Há erros no formulário de cadastro de tarefa!");			
			return badRequest(formularioDeNovaTarefa.render(formulario));
        }

        tarefa.save();        
        return redirect(routes.TarefaController.formularioDeNovaTarefa());
    }

}