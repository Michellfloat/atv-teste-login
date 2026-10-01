package validar.login;

import validar.login.exception.ValidacaoException;

public class ValidarEmail {
    public boolean validar(String email){
        if (email == null || email.isBlank()) {
            throw new ValidacaoException("O Email não pode ser nulo ou vazio.\nTente novamente.");
        }

        String regexEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,}$";

        if (!email.matches(regexEmail)) {
            return false;
        }

        return true;
    }
}
