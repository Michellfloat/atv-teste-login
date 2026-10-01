package validar.login;

import validar.login.exception.NotFoundException;

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
           throw new NotFoundException("Nome não encontrado!!");
       }
        boolean nomeValido = Boolean.valueOf(nome);
        return nomeValido;
   }

    public boolean validarEmail(String email){
        if (email == null || email.isBlank()){
            throw new NotFoundException("Email não encontrado!!");
        }

        boolean emailCorreto = email.toLowerCase().endsWith("@gmail.com");

        boolean emailVerdadeiro = emailCorreto;

        return emailVerdadeiro;
    }


}
