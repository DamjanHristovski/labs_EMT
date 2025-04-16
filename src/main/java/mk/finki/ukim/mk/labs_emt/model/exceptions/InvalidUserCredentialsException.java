package mk.finki.ukim.mk.labs_emt.model.exceptions;

public class InvalidUserCredentialsException extends RuntimeException{
    public InvalidUserCredentialsException(){
        super("Invalid user credentials");
    }
}
