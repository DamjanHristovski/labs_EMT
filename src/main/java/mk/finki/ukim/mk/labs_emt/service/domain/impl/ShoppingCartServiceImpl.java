package mk.finki.ukim.mk.labs_emt.service.domain.impl;

import mk.finki.ukim.mk.labs_emt.model.Enum.ShoppingCartStatus;
import mk.finki.ukim.mk.labs_emt.model.domain.Accommodation;
import mk.finki.ukim.mk.labs_emt.model.domain.ShoppingCart;
import mk.finki.ukim.mk.labs_emt.model.exceptions.AccommodationAlreadyRentedException;
import mk.finki.ukim.mk.labs_emt.model.exceptions.AccommodationNotFound;
import mk.finki.ukim.mk.labs_emt.model.exceptions.ShoppingCartNotFoundException;
import mk.finki.ukim.mk.labs_emt.repository.ShoppingCartRepository;
import mk.finki.ukim.mk.labs_emt.service.domain.AccommodationService;
import mk.finki.ukim.mk.labs_emt.service.domain.ShoppingCartService;
import mk.finki.ukim.mk.labs_emt.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserService userService;
    private final AccommodationService accommodationService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserService userService, AccommodationService accommodationService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userService = userService;
        this.accommodationService = accommodationService;
    }

    @Override
    public List<Accommodation> listAllAccommodations(Long cartId) {
        if (shoppingCartRepository.findById(cartId).isEmpty())
            throw new ShoppingCartNotFoundException(cartId);
        return shoppingCartRepository.findById(cartId).get().getAccommodationList();
    }

    @Override
    public Optional<ShoppingCart> getActiveShoppingCart(String username) {
        return shoppingCartRepository.findByUserAndStatus(userService.findByUsername(username), ShoppingCartStatus.CREATED)
                .or(() ->{
                    ShoppingCart cart = new ShoppingCart(userService.findByUsername(username));
                    return Optional.of(shoppingCartRepository.save(cart));
                });
    }

    @Override
    public Optional<ShoppingCart> addAccommodationToShoppingCart(String username, Long accommodationId) {
        Accommodation accommodation = accommodationService.findById(accommodationId).orElseThrow(AccommodationNotFound::new);

        if (accommodation.getRented())
        {
            throw new AccommodationAlreadyRentedException(accommodationId);
        }
        ShoppingCart cart = getActiveShoppingCart(username).orElseThrow();

        if (cart.getAccommodationList().contains(accommodation)){
            return Optional.of(cart);
        }

        cart.getAccommodationList().add(accommodation);
        return Optional.of(shoppingCartRepository.save(cart));
    }

    @Override
    public Optional<ShoppingCart> confirmReservations(String username) {
        ShoppingCart cart = getActiveShoppingCart(username).orElseThrow();

        for (Accommodation accommodation : cart.getAccommodationList())
        {
            accommodation.setRented(true);
            accommodationService.save(accommodation);
        }

        cart.setStatus(ShoppingCartStatus.FINISHED);
        return Optional.of(shoppingCartRepository.save(cart));
    }


}
