package validar.login;

public final class App {

    private App() {
    }
    public static void main(String[] args) {
        ValidarSenha validarSenha = new ValidarSenha();
        ValidarNome validarNome = new ValidarNome();
        ValidarEmail validarEmail = new ValidarEmail();
        System.out.println("Validação de usuário!!!!");

        System.out.println("Nome: " + validarNome.validarNome("Carlos"));
        System.out.println("Email: " + validarEmail.validarEmail("Carlos@gmail.com"));
        System.out.println("Senha: " + validarSenha.validarSenha("Java@12345"));

    }

}
