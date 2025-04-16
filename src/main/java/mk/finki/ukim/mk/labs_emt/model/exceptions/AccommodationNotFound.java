package mk.finki.ukim.mk.labs_emt.model.exceptions;

public class AccommodationNotFound extends RuntimeException{
    public AccommodationNotFound() {
        super("Accommodation not found");
    }
}
