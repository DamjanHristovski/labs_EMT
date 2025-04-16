package mk.finki.ukim.mk.labs_emt.dto;

import mk.finki.ukim.mk.labs_emt.model.Enum.ShoppingCartStatus;
import mk.finki.ukim.mk.labs_emt.model.domain.ShoppingCart;

import java.time.LocalDateTime;
import java.util.List;

public record ShoppingCartDto(
        Long id,
        LocalDateTime dateCreated,
        DisplayUserDto user,
        List<DisplayAccommodationDto> accommodations,
        ShoppingCartStatus status
) {
    public static ShoppingCartDto from (ShoppingCart shoppingCart)
    {
        return new ShoppingCartDto(
                shoppingCart.getId(),
                shoppingCart.getDateCreated(),
                DisplayUserDto.from(shoppingCart.getUser()),
                DisplayAccommodationDto.from(shoppingCart.getAccommodationList()),
                shoppingCart.getStatus()
        );
    }
}
