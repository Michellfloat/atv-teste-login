package validar.login;

public class ValidarLogin {

    private ValidarNome validarNome;
    private ValidarSenha validar;
    private ValidarEmail validarEmail;
    public boolean validarLogin(String nome, String senha){
        
        boolean senhaValida = validarSenha(senha);
        boolean nomeValido = validarNome(nome);

            
        if (nomeValido == true && senhaValida == true) {
            return true;
        }

        return true;
    }

}
