package validar.login;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import validar.login.exception.AutenticationException;
import validar.login.exception.BlockAccountException;
import validar.login.exception.ValidationException;

public class ValidarTeste {

    private ValidarSenha validadorSenha;
    private ValidarNome validadorNome;
    private ValidarEmail validadorEmail;

    private ValidarLogin validadorLogin;


    @BeforeEach
    void setUp(){
        validadorNome = new ValidarNome();
        validadorEmail = new ValidarEmail();
        validadorSenha = new  ValidarSenha();

        validadorLogin = new ValidarLogin();
    }

    @Test
    void testeValidaMinimo10Caracteres(){
        //A quantidade deve ser entre 10 e 12
        String senha = "Flo4tingM5t@";

        boolean resultado = validadorSenha.validarSenha(senha);
        Assertions.assertTrue(resultado);

    }

    @Test 
    void testeValidaMaximo12Caracteres(){
        //A senha deve possuir no máximo 12 caracteres
        String senha = "Fl@at1ngM5ta";

        boolean resultado = validadorSenha.validarSenha(senha);

        Assertions.assertTrue(resultado);
    }

    @Test
    void possuirLetra(){
        //Deve possuir letra, caso não deve ser falso
        String senha = "51041183951@";

        boolean resultado = validadorSenha.validarSenha(senha);
        Assertions.assertFalse(resultado);
    }

    @Test
    void possuirNumero(){
        //Deve possuir um número, caso não deve ser Falso
        String senha = "Fl@#FD@#FF@";

        boolean resultado = validadorSenha.validarSenha(senha);
        Assertions.assertFalse(resultado);
    }

    @Test
    void possuirCaracteresEspeciais(){
        //Deve possuir Caracteres Especiais, caso não deve ser Falso
        String senha = "12as56SD901";

        boolean resultado = validadorSenha.validarSenha(senha);
        Assertions.assertFalse(resultado);
    }

    @Test
    void senhaNaoNula(){
        //autenticação de Senha não pode ser nula, caso nulo deve ser Falso
        String senha = "";

        boolean resultado = validadorSenha.validarSenha(senha);
        Assertions.assertFalse(resultado);
    }

    @Test
    void usuarioNaoNulo(){
        //autenticação do nome do usuário não pode ser nula, caso nulo deve ser Falso
        String usuario = "";

        Assertions.assertThrows(ValidationException.class, () -> validadorNome.validarNome(usuario));
    }

    @Test 
    void SenhaNaoVazia(){
        //autenticação de Senha não pode ser vazia, caso vazia ela deve ser falso
        String senha = " ";
        
        senha.isBlank(); 
        
        boolean resultado = validadorSenha.validarSenha(senha);
        
        Assertions.assertFalse(resultado);
        
    }

    @Test
    void usuarioNaoVazio(){
        //autenticação do nome do usuário não pode ser nula, caso nulo deve ser Falso
        String usuario = "";

        Assertions.assertThrows(ValidationException.class, () -> validadorNome.validarNome(usuario));
    }

    @Test
    void permitirCasoSenhaENomeCorreto(){
        String usuario = "Float";
        String senha = "Flo4tingM5t@";

        // Valida se o usuário é válido
        boolean usuarioValido = validadorNome.validarNome(usuario);

        // Valida se a senha é válida
        boolean senhaValida = validadorSenha.validarSenha(senha);

        // O teste só passará se AMBAS as condições forem verdadeiras
        Assertions.assertTrue(usuarioValido && senhaValida);


    }

    @Test 
    void bloqueio3Tentativas(){
        String usuario = "Banyue";
        String senha = "Java@1234567";

        Assertions.assertThrows(AutenticationException.class, () ->  validadorLogin.validarLogin(usuario, senha));

        Assertions.assertThrows(AutenticationException.class, () ->  validadorLogin.validarLogin(usuario, senha));

        Assertions.assertThrows(AutenticationException.class, () ->  validadorLogin.validarLogin(usuario, senha));

        Assertions.assertThrows(BlockAccountException.class, () -> validadorLogin.validarLogin(usuario, "Java@123456"));
    }

    
}
