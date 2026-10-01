package validar.login.exception;

public class ContaBloqueadaException extends RuntimeException{
    public ContaBloqueadaException(String message){
        super(message);
    }
}
