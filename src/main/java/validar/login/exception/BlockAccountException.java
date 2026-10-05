package validar.login.exception;

public class BlockAccountException extends RuntimeException{
    public BlockAccountException(String message){
        super(message);
    }
}
