package validar.login;

import validar.login.exception.NotFoundException;

public class ValidarSenha {
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

    public boolean validarEmail(String email){
        if (email == null || email.isBlank()){
            throw new NotFoundException("Email não encontrado!!");
        }

        boolean emailCorreto = email.matches(".*[@]*.");

        boolean emailVerdadeiro = emailCorreto;

        return emailVerdadeiro;
    }


}
