package mk.finki.ukim.mk.labs_emt.service.domain;

import mk.finki.ukim.mk.labs_emt.model.domain.Accommodation;
import mk.finki.ukim.mk.labs_emt.model.domain.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {

    List<Accommodation> listAllAccommodations(Long cartId);
    Optional<ShoppingCart> getActiveShoppingCart (String username);
    Optional<ShoppingCart> addAccommodationToShoppingCart (String username, Long accommodationId);
    Optional<ShoppingCart> confirmReservations(String username);
}
