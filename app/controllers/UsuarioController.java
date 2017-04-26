package controllers;

import java.util.Optional;

import javax.inject.Inject;
import akka.util.Crypt;
import autenticadores.UsuarioAutenticado;
import daos.TokenDeCadastroDAO;
import daos.UsuarioDAO;
import models.EmailDeCadastro;
import models.Pessoa;
import models.PessoaUsuario;
import models.TokenDaApi;
import models.TokenDeCadastro;
import models.Usuario;
import play.api.libs.mailer.MailerClient;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import play.mvc.Security.Authenticated;
import validadores.ValidadorDePessoaUsuario;
import views.html.*;

public class UsuarioController extends Controller {
	
	public static final String AUTH = "AUTH";
	@Inject
	private FormFactory formularios;
	@Inject
	private MailerClient enviador;
	@Inject 
	private ValidadorDePessoaUsuario validadorDePessoaUsuario;
	@Inject
	private UsuarioDAO usuarioDAO;
	@Inject
	private TokenDeCadastroDAO tokenDeCadastroDAO;
	
	public Result formularioDeNovoUsuario() {
		Form<PessoaUsuario> formulario = formularios.form(PessoaUsuario.class);
		return ok(formularioDeNovoUsuario.render(formulario));
	}	
	public Result salvaNovoUsuario() {
		Form<PessoaUsuario> formulario = formularios.form(PessoaUsuario.class).bindFromRequest();	
		
		if (validadorDePessoaUsuario.temErros(formulario)) {
			flash("danger", "Há erros no formulário de cadastro!");			
			return badRequest(formularioDeNovoUsuario.render(formulario));	
		}
	
		PessoaUsuario pessoaUsuario = formulario.get();		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(pessoaUsuario.getNome());
		pessoa.setEmail(pessoaUsuario.getEmail());
		pessoa.save();
		
		Usuario usuario = new Usuario();
		usuario.setSenha(pessoaUsuario.getSenha());
		usuario.setSenha(Crypt.sha1(usuario.getSenha()));
        usuario.setPessoa(pessoa);
		usuario.save();
		
		TokenDeCadastro token = new TokenDeCadastro(usuario);
		token.save();
		
		enviador.send(new EmailDeCadastro(token));
		flash("success", "Um e-mail foi enviado para confiramar o cadastro!");
		return redirect(routes.UsuarioController.formularioDeLogin());
	}
	public Result formularioDeLogin() {
	    return ok(formularioDeLogin.render(formularios.form()));
	}
	public Result fazLogin() {
		DynamicForm formulario = formularios.form().bindFromRequest();
		String email = formulario.get("email");
		String senha = Crypt.sha1(formulario.get("senha"));
		
		Optional<Usuario> possivelUsuario = usuarioDAO.comEmailESenha(email, senha);
		if (possivelUsuario.isPresent()) {
			Usuario usuario = possivelUsuario.get();
			if (usuario.isVerificado()) {
				insereUsuarioNaSessao(usuario);
				flash("success", "Login foi efetuado com sucesso!");
				return redirect(routes.UsuarioController.painel());
			}
			else {
				flash("warning", "Usuário ainda não confirmado! Verifique seu e-mail!");
			}
		}
		else {
			flash("danger", "Credenciais inválidas!");
		}	
	    return redirect(routes.UsuarioController.formularioDeLogin());
	}
	
	public Result confirmaUsuario(String email, String codigo) {
		Optional<Usuario> possivelUsuario = usuarioDAO.comEmail(email);
		Optional<TokenDeCadastro> possivelToken = tokenDeCadastroDAO.comCodigo(codigo);
		if (possivelToken.isPresent() && possivelUsuario.isPresent()) {
			TokenDeCadastro token = possivelToken.get();
			Usuario usuario = possivelUsuario.get();
			if (token.getUsuario().equals(usuario)) {
				token.delete();
			    usuario.setVerificado(true);
			    TokenDaApi tokenDaApi = new TokenDaApi(usuario);
			    tokenDaApi.save();
			    usuario.setToken(tokenDaApi);
			    usuario.update();
			    flash("success", "Cadastro confirmado com suacesso!");
			    insereUsuarioNaSessao(usuario);
			    return redirect(routes.UsuarioController.painel());
			}				
		}
		flash("danger", "Algo deu errado ao tentar confirmar o cadastro!");
		return redirect(routes.UsuarioController.formularioDeLogin());	
	}
	private void insereUsuarioNaSessao(Usuario usuario) {
		session(AUTH, usuario.getToken().getCodigo());
	}
	@Authenticated(UsuarioAutenticado.class)
	public Result painel() {
		String codigo = session(AUTH);
		Usuario usuario = usuarioDAO.comToken(codigo).get();
				
	    return ok(views.html.painel.render(usuario));
	}
	@Authenticated(UsuarioAutenticado.class)
	public Result fazLogout() {
		session().clear();
		flash("success", "Logout efetuado com sucesso!");
		return redirect(routes.UsuarioController.formularioDeLogin());
	}
}
