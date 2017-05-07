package validadores;

import javax.inject.Inject;
import daos.ProjetoDAO;
import models.Projeto;
import play.data.Form;
import play.data.validation.ValidationError;

public class ValidadorDeProjeto {

    @Inject
    ProjetoDAO projetoDAO;

    public boolean temErros(Form<Projeto> formulario) {
        validaNome(formulario);
        return formulario.hasErrors();
    }

    public void validaNome(Form<Projeto> formulario) {
        String nome = formulario.field("nome").valueOr("");
        
        if (projetoDAO.comNome(nome).isPresent()) {
            formulario.reject(new ValidationError("nome", "Já existente um projeto cadastrado com esse nome!"));  
        } 
    }
}