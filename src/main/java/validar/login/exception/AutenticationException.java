package validar.login.exception;

public class AutenticationException extends RuntimeException{
    public AutenticationException(String message){
        super(message);
    }
}
