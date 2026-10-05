package validar.login;

import validar.login.exception.ValidationException;

public class ValidarEmail {

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

}
