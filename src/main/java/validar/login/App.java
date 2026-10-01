package validar.login;

public final class App {

    private App() {
    }
    public static void main(String[] args) {
        ValidarNome nome = new ValidarNome();
        ValidarEmail email = new ValidarEmail();
        ValidarSenha senha = new ValidarSenha();
        
        
        System.out.println("Validação de usuário!!!!");
        System.out.println("Nome: " + nome.validarNome("Float"));
        System.out.println("Email: " + email.validar("float@gmail.com"));
        System.out.println("Senha: " + senha.validarSenha("Flo4t1ngM5t@"));
    }

}
