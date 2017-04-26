package validadores;

import javax.inject.Inject;

import daos.PessoaDAO;
import daos.UsuarioDAO;
import play.data.DynamicForm;
import play.data.validation.ValidationError;

public class ValidadorDeUsuario {

	@Inject
	private UsuarioDAO usuarioDAO;
	
	public boolean temErros(DynamicForm formulario) {
		validaSenha(formulario);
		validaEmail(formulario);
		return formulario.hasErrors();
	}

	private void validaEmail(DynamicForm formulario) {
	//	String email = formulario.field("email").valueOr("");	
	//	if (usuarioDAO.comEmail(email).isPresent()) {
	//		formulario.reject(new ValidationError("email", "Já existente um usuário cadastrado com esse e-mail!"));  
	//	}
	}

	private void validaSenha(DynamicForm formulario) {
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
