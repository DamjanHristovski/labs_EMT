package mk.finki.ukim.mk.labs_emt.model.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String username){
        super("Username : " + username + " already exists");
    }
}
