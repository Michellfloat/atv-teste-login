package validar.login;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValidarTeste {
    private ValidarSenha validacao = new ValidarSenha();

    boolean validarNome = new ValidarSenha().validarNome("Carlos");
    boolean validarEmail = new ValidarSenha().validarEmail("Carlos@gmail.com");
    boolean validarSenha = new ValidarSenha().validarSenha("Javax@123456");


    @BeforeEach
    void setUp(){
        validacao = new ValidarSenha();
    }

    @Test
    void cadastrar(){
        //Deve permitir cadastrar nome, email e senha
        //Campos obrigatórios não podem ser nulos(nome,email,senha)
    }

    @Test
    void testeValida10Caracteres(){
        //A quantidade deve ser entre 10 e 12
        String senha = "Flo4tingM5t@";

        boolean resultado = validacao.validarSenha(senha);
        Assertions.assertTrue(resultado);

    }

    @Test
    void possuirLetra(){
        //Deve possuir letra
        String senha = "51041183951@";

        boolean resultado = validacao.validarSenha(senha);
        Assertions.assertTrue(resultado);
    }

    @Test
    void possuirNumero(){
        //Deve possuir número
        String senha = "Fl@#FD@#FF@";

        boolean resultado = validacao.validarSenha(senha);
        Assertions.assertTrue(resultado);
    }

    @Test
    void possuirCaracteresEspeciais(){
        //Deve possuir Caracteres Especiais
        String senha = "12as56SD901";

        boolean resultado = validacao.validarSenha(senha);
        Assertions.assertTrue(resultado);
    }

    @Test
    void naoNuloNaoVazio(){
        //autenticação de Senha não pode ser nula ou vazio
        String senha = "";

        boolean resultado = validacao.validarSenha(senha);
        Assertions.assertTrue(resultado);
    }

    @Test
    void permitirCasoSenhaCorreta(){
        String usuario = "Float";
        String senha = "Flo4tingM5t@";

        // Valida se o usuário é válido
        boolean usuarioValido = usuario != null && !usuario.isBlank();

        // Valida se a senha é válida
        boolean senhaValida = validacao.validarSenha(senha);

        // O teste só passará se AMBAS as condições forem verdadeiras
        Assertions.assertTrue(usuarioValido && senhaValida);


    }


}
