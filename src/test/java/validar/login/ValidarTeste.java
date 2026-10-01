package validar.login;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import validar.login.exception.AutenticacaoException;
import validar.login.exception.ContaBloqueadaException;
import validar.login.exception.NotFoundException;
import validar.login.exception.ValidacaoException;
import validar.login.model.Usuario;
import validar.login.model.enums.NivelUsuario;

public class ValidarTeste {

    private ValidarSenha validadorSenha;
    private ValidarLogin sevicoLogin;


    @BeforeEach
    void setUp(){
        validadorSenha = new ValidarSenha();
        sevicoLogin = new  ValidarLogin();
    }

    @Test 
    @DisplayName("CT01/CT09 - Senha válida com exatamente 10 caracteres")
    void testeSenhaValida10Caracteres(){
        Assertions.assertTrue(validadorSenha.validarSenha("Java@123456"));
        Assertions.assertTrue(validadorSenha.validarSenha("Java@12345"));

    }

    @Test 
    @DisplayName("CT02/CT13 - Senha menor que 10 caracteres (9)")
    void testeSenhaMenorQueMinimo(){
        Assertions.assertFalse(validadorSenha.validarSenha("Java@1234"));
    }

    @Test 
    @DisplayName("CT03 - Senha maior que 12 caracteres (13 caracteres+)")
    void testeSenhaMaiorQueMaximo(){
        Assertions.assertFalse(validadorSenha.validarSenha("Java@123456789"));
    }

    @Test 
    @DisplayName("CT10/CT12 - Senha limite máximo de 12 caracteres")
    void  testeSenhaLimiteMaximo12(){
        Assertions.assertTrue(validadorSenha.validarSenha("Java@1234567"));
    }

    @Test 
    @DisplayName("CT04/CT14 - Senha sem caractere especial")
    void testeSenhaSemEspecial(){
        Assertions.assertFalse(validadorSenha.validarSenha("Java1234567"));
    }

    @Test 
    @DisplayName("CT05/CT15 - Senha sem letra")
    void testeSenhaSemLetra(){
        Assertions.assertFalse(validadorSenha.validarSenha("123456@7890"));
    }

    @Test 
    @DisplayName("CT06/CT16 - Senha sem número")
    void testeSenhaSemNumero(){
        Assertions.assertFalse(validadorSenha.validarSenha("Java@Special"));
    }

    @Test 
    @DisplayName("CT07 - Senha 'Nula'")
    void testeSenhaNula(){
        Assertions.assertFalse(validadorSenha.validarSenha(null));
    }

    @Test 
    @DisplayName("CT08 - Senha Vazia")
    void testSenhaVazia(){
        assertFalse(validadorSenha.validarSenha(""));
    }

    //-- Teste de REQUISITOS FUNCIONAIS e REGRAS --------------------------------------------------------

    @Test 
    @DisplayName("RF01/RF08 - Cadastro de Usuário com Nível ADMIN")
    void testeCadastroUsuarioComSucesso(){
        Usuario usuario = new Usuario("Float", "Float999", "float@email.com", "Java@123456", NivelUsuario.ADMIN);

        assertDoesNotThrow(() -> sevicoLogin.cadastrarUsuario(usuario));
    }

    @Test 
    @DisplayName("RF04/RF06 - Erro ao autenticar com campos vazios")
    void testeAutenticacaoCamposVazios(){
        assertThrows(ValidacaoException.class, () -> sevicoLogin.autenticar("", ""));
    }

    @Test 
    @DisplayName("RF06 - Exceção ao tentar logar com Usuário Inexistente")
    void testeUsuarioInexistente(){
        assertThrows(NotFoundException.class, () -> sevicoLogin.autenticar("naoExiste", "Java@123456"));
    }

    @Test 
    @DisplayName("RF07 - Bloqueio de conta após 3 tentativas inválidas consecutivas")
    void testeBloqueioContaApos3Tentativas(){
        Usuario usuario = new Usuario("Float", "floatOMEGA", "float@email.com", "Java@123456", NivelUsuario.CLIENTE);

        sevicoLogin.cadastrarUsuario(usuario);
        
        //-- 1° Tentativa
        assertThrows(AutenticacaoException.class, () -> sevicoLogin.autenticar("floatOMEGA", "Errada@1234"));
        
        //-- 2° Tentativa
        assertThrows(AutenticacaoException.class, () -> sevicoLogin.autenticar("floatOMEGA", "Errada@1234"));

        //-- 3° Tentativa:Bloqueado
        assertThrows(AutenticacaoException.class, () -> sevicoLogin.autenticar("floatOMEGA", "Errada@1234"));

        //-- 4° Tentativa:Conta já bloqueada
        assertThrows(ContaBloqueadaException.class, () -> sevicoLogin.autenticar("floatOMEGA", "Java@123456"));
    } 
    
}
