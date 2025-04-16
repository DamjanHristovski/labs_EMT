package mk.finki.ukim.mk.labs_emt.service.application.impl;

import mk.finki.ukim.mk.labs_emt.dto.DisplayAccommodationDto;
import mk.finki.ukim.mk.labs_emt.dto.ShoppingCartDto;
import mk.finki.ukim.mk.labs_emt.service.application.ShoppingCartAppService;
import mk.finki.ukim.mk.labs_emt.service.domain.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartAppServiceImpl implements ShoppingCartAppService {
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartAppServiceImpl(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public List<DisplayAccommodationDto> listAllAccommodations(Long cartId) {
        return DisplayAccommodationDto.from(shoppingCartService.listAllAccommodations(cartId));
    }

    @Override
    public Optional<ShoppingCartDto> getActiveShoppingCart(String username) {
        return shoppingCartService.getActiveShoppingCart(username).map(ShoppingCartDto::from);
    }

    @Override
    public Optional<ShoppingCartDto> addAccommodationToShoppingCart(String username, Long accommodationId) {
        return shoppingCartService.addAccommodationToShoppingCart(username,accommodationId).map(ShoppingCartDto::from);
    }

    @Override
    public Optional<ShoppingCartDto> confirmReservations(String username) {
        return shoppingCartService.confirmReservations(username).map(ShoppingCartDto::from);
    }
}
