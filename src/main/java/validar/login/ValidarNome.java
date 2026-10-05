package validar.login;

import validar.login.exception.ValidationException;

public class ValidarNome {
    
    public boolean validarNome(String nome){
        if (nome == null || nome.isBlank()){
           throw new ValidationException("Nome não encontrado!!\nTente novamente.");
       }
        return true;
   }
   
}
