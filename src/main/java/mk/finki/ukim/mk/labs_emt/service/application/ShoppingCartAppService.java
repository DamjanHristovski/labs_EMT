package mk.finki.ukim.mk.labs_emt.service.application;

import mk.finki.ukim.mk.labs_emt.dto.DisplayAccommodationDto;
import mk.finki.ukim.mk.labs_emt.dto.ShoppingCartDto;
import mk.finki.ukim.mk.labs_emt.model.domain.Accommodation;
import mk.finki.ukim.mk.labs_emt.model.domain.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartAppService {
    List<DisplayAccommodationDto> listAllAccommodations(Long cartId);
    Optional<ShoppingCartDto> getActiveShoppingCart (String username);
    Optional<ShoppingCartDto> addAccommodationToShoppingCart (String username, Long accommodationId);
    Optional<ShoppingCartDto> confirmReservations(String username);
}
