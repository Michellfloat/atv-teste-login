package validar.login;


import validar.login.exception.ValidacaoException;

public class ValidarNome {
    public boolean validarNome(String nome){
        if (nome == null || nome.isBlank()){
           throw new ValidacaoException("O nome não pode ser nulo ou vazio.\nTente novamente.");
       }
        
       return true;

   }

}
