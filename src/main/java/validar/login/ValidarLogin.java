package validar.login;

import java.util.HashMap;
import java.util.Map;

import validar.login.exception.AutenticacaoException;
import validar.login.exception.ConexaoBancoException;
import validar.login.exception.ContaBloqueadaException;
import validar.login.exception.NotFoundException;
import validar.login.exception.ValidacaoException;
import validar.login.model.Usuario;

public class ValidarLogin {
    private final ValidarSenha validarSenha = new ValidarSenha();
    private final ValidarNome validarNome = new ValidarNome();
    private final ValidarEmail validarEmail = new ValidarEmail();

    // Simulação de Banco de Dados em Memória, já que não iremos por o Hibernate(H2)
    private final Map<String, Usuario>bancoUsuarios = new HashMap<>();
    private boolean bancoConectado = true;

    public void setBancoConectado(boolean status){
        this.bancoConectado = status;
    }

    public void cadastrarUsuario(Usuario usuario){
        verificarConexaoBanco();

        if (usuario == null) {
            throw new ValidacaoException("Usuário não pode ser nulo.");
        }

        validarNome.validarNome(usuario.getNome());
        validarEmail.validar(usuario.getEmail());

        if (usuario.getLogin() == null || usuario.getLogin().isBlank()) {
            throw new ValidacaoException("Login é obrigatório.");
        }

        if (!validarSenha.validarSenha(usuario.getSenha())) {
            throw new ValidacaoException("Senha inválida segundo os requisitos de segurança.");
        }

        bancoUsuarios.put(usuario.getLogin(), usuario);
    }

    public boolean autenticar(String login, String senha){
        verificarConexaoBanco();

        if (login == null || login.isBlank() || senha == null || senha.isBlank()) {
            throw new ValidacaoException("Login e senha são obrigatórios.");
        }

        if (!bancoUsuarios.containsKey(login)) {
            throw new NotFoundException("Usuário não encontrado.");
        }

        Usuario usuario = bancoUsuarios.get(login);

        if (usuario.isBloqueado()) {
            throw new ContaBloqueadaException("Conta bloqueada por excesso de tentativas incorretas.");
        }

        if (usuario.getSenha().equals(senha) && validarSenha.validarSenha(senha)) {
            usuario.resetarTentativas();
            return true;
        }else{
            usuario.incrementarTentativas();
            throw new AutenticacaoException("Senha incorreta.\nTente novamente.");
        }
    }

    private void verificarConexaoBanco(){
        if (!bancoConectado) {
            throw new ConexaoBancoException("Erro de conexão com o banco de dados.");
        }
    }
}
