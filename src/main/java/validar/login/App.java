package validar.login;

public final class App {

    private App() {
    }
    public static void main(String[] args) {
        ValidarSenha validar = new ValidarSenha();
        System.out.println("Validação de usuário!!!!");

        System.out.println("Nome: " + validar.validarNome("Carlos"));
        System.out.println("Email: " + validar.validarEmail("Carlos@gmail.com"));
        System.out.println("Senha: " + validar.validarSenha("Java@12345"));

    }

}
