package models;

import play.libs.mailer.Email;

public class EmailDeCadastro extends Email {

	private static final String REMETENTE = "Caelum <no-reply@caelum.com.br>";
    private static final String ASSUNTO = "Confirmação de cadastro na API de produtos!";
    private static final String CORPO_FORMAT = "Olá, %s! Por favor clique no link a seguir para confirmar seu cadastro! <a href='%s'>Confirmar cadastro!</a>";
    
	public EmailDeCadastro(TokenDeCadastro token) {
		Usuario usuario = token.getUsuario();
		Pessoa pessoaUsuario = usuario.getPessoa();
				
	    String destinatario = String.format("%s <%s>", pessoaUsuario.getNome(), pessoaUsuario.getEmail());
	    String link = String.format("http://localhost:9000/usuario/confirma/%s/%s", pessoaUsuario.getEmail(), token.getCodigo());
	    String corpo = String.format(CORPO_FORMAT, pessoaUsuario.getNome(), link);

	    this.addTo(destinatario);
	    this.setFrom(REMETENTE);
	    this.setSubject(ASSUNTO);
	    this.setBodyHtml(corpo);		
	}

}
