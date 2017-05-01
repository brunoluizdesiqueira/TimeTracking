package autenticadores;

import java.util.Optional;

import javax.inject.Inject;

import controllers.*;
import controllers.UsuarioController;
import daos.UsuarioDAO;
import models.Usuario;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security.Authenticator;

public class UsuarioAutenticado extends Authenticator {

	@Inject
	private UsuarioDAO usuarioDAO;
	@Override
	public String getUsername(Context context) {
		String codigo = context.session().get(UsuarioController.AUTH);
		Optional<Usuario> possivelUsuario = usuarioDAO.comToken(codigo);
		if (possivelUsuario.isPresent()) {
			Usuario usuario = possivelUsuario.get();
			context.args.put("usuario", usuario);
			return usuario.getNome();
		}
		return null;
	}
	@Override
	public Result onUnauthorized(Context context) {
		context.flash().put("danger", "Usuário não autorizado!"); 
		return redirect(routes.UsuarioController.formularioDeLogin());
	}	
}
