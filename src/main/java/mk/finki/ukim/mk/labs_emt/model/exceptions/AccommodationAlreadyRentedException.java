package mk.finki.ukim.mk.labs_emt.model.exceptions;

public class AccommodationAlreadyRentedException extends RuntimeException{
    public AccommodationAlreadyRentedException(Long id) {
        super(String.format("Accommodation with id : %d is already rented",id));
    }
}
