package validadores;

import javax.inject.Inject;

import daos.PessoaDAO;
import models.PessoaUsuario;
import play.data.Form;
import play.data.validation.ValidationError;

public class ValidadorDePessoaUsuario {
@Inject
private PessoaDAO pessoaDAO;

public boolean temErros(Form<PessoaUsuario> formulario) {
	validaEmail(formulario);
	validaSenha(formulario);
	return formulario.hasErrors();
}

private void validaEmail(Form<PessoaUsuario> formulario) {
	String email = formulario.field("email").valueOr("");	
	if (pessoaDAO.comEmail(email).isPresent()) {
		formulario.reject(new ValidationError("email", "Já existente uma pessoa cadastrado com esse e-mail!"));  
	}
  }

private void validaSenha(Form<PessoaUsuario> formulario) {
	String senha = formulario.field("senha").valueOr("");
	String confirmaSenha = formulario.field("confirmaSenha").valueOr("");

	if (confirmaSenha.isEmpty()) {
		formulario.reject(new ValidationError("confirmaSenha", "Você precida fornecer uma confirmação de senha!"));
	}
	else if (!senha.equals(confirmaSenha)) {
		formulario.reject(new ValidationError("senha", ""));
		formulario.reject(new ValidationError("confirmaSenha", "As senhas precisam ser iguais!"));
	}	
}

}
