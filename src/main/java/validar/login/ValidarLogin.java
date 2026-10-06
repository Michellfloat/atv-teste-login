package validar.login;

import validar.login.exception.AutenticationException;
import validar.login.exception.BlockAccountException;
import validar.login.exception.NotFoundException;
import validar.login.exception.ValidationException;

public class ValidarLogin {

    private ValidarNome nomeValidado = new ValidarNome();
    private ValidarSenha senhaValidada = new ValidarSenha();

    private final String usuarioCadastrado = "Banyue";
    private final String senhaCadastrada = "Java@123456";

    private int tentativasIncorretas = 0;
    private boolean contaBloqueada = false;

    public boolean validarLogin(String nome, String senha){
        
        if (nome == null || nome.isBlank() || senha == null || senha.isBlank()) {
            throw new ValidationException("O nome e a senha são obrigatórios para loggar.");
        }

        if (contaBloqueada) {
            throw new BlockAccountException("Conta bloqueada por excesso de tentativas incorretas.");
        }

        nomeValidado.validarNome(nome);

        if (!usuarioCadastrado.equalsIgnoreCase(nome.trim())) {
            throw new NotFoundException("Usuário não encontrado!!!");
        }

        if (!senhaValidada.validarSenha(senha)) {
            throw new ValidationException("Senha informada não cumpre os requisitos de segurança do sistema.");
        }

        if (senhaCadastrada.equals(senha)) {
            tentativasIncorretas = 0;
            return true;
        }else{
            tentativasIncorretas++;
            if (tentativasIncorretas>=3) {
                contaBloqueada = true;
            }
            throw new  AutenticationException("Senha incorreta.\nTente novamente...");
        }

    }

}
