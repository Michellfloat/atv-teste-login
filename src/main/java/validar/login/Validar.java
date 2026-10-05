package validar.login;

import validar.login.exception.ValidationException;

public class Validar {

    public boolean validarSenha(String senha) {
        if (senha == null || senha.isBlank()) {
            return false;
        }
        if (senha.length() < 10 || senha.length() > 12) {
            return false;
        }
        boolean possuiNumero =
                senha.matches(".*\\d.*");
        boolean possuiLetra =
                senha.matches(".*[a-zA-Z].*");
        boolean possuiEspecial =
                senha.matches(".*[!@#$%&*()].*");
        return possuiNumero &&
                possuiLetra &&
                possuiEspecial;
    }

   public boolean validarNome(String nome){
        if (nome == null || nome.isBlank()){
           throw new ValidationException("Nome não encontrado!!\nTente novamente.");
       }
        return true;
   }

    public boolean validarEmail(String email){
        if (email == null || email.isBlank()){
            throw new ValidationException("Email não pode ser nulo ou vazio.\nTente novamente.");
        }
        //É melhor inserir o regex dentro de uma variável e apenas chamá-la
        String regexEmail = "[A-Za-z0-9+_.-]+@[a-Za-z0-9.-]\\.[a-zA-Z]{2,}$";

        if (!email.matches(regexEmail)) {
            return false;
        }
        return true;
    }

    public boolean validarLogin(String nome, String senha){
        
        boolean senhaValida = validarSenha(senha);
        boolean nomeValido = validarNome(nome);

        
        if (nomeValido == true && senhaValida == true) {
            return true;
        }

        return true;
    }


}
