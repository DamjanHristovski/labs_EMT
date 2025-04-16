package mk.finki.ukim.mk.labs_emt.model.exceptions;

public class ShoppingCartNotFoundException extends RuntimeException{

    public ShoppingCartNotFoundException(Long id)
    {
        super(String.format("Shopping cart with id : %d was not found",id));
    }
}
