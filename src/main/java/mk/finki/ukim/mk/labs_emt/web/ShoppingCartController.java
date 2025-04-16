package mk.finki.ukim.mk.labs_emt.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.labs_emt.dto.ShoppingCartDto;
import mk.finki.ukim.mk.labs_emt.model.domain.User;
import mk.finki.ukim.mk.labs_emt.service.application.ShoppingCartAppService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.Authenticator;

@RestController
@RequestMapping("/api/shopping-cart")
@Tag(name = "Shopping Cart api", description = "Managing shopping carts")
public class ShoppingCartController {
    private final ShoppingCartAppService shoppingCartAppService;

    public ShoppingCartController(ShoppingCartAppService shoppingCartAppService) {
        this.shoppingCartAppService = shoppingCartAppService;
    }

    @GetMapping
    @Operation(summary = "Get active shopping cart", description = "Active shopping cart for the logged-in user")
    public ResponseEntity<ShoppingCartDto> getActiveShoppingCart(HttpServletRequest req)
    {
        String username = req.getRemoteUser();
        return shoppingCartAppService.getActiveShoppingCart(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add-accommodation/{id}")
    @Operation(summary = "Adds a new accommodation to the shopping cart", description = "Adds a new accommodation to the logged in shopping cart")
    public ResponseEntity<ShoppingCartDto> addAccommodationToShoppingCart(
            @PathVariable Long id,
            Authentication authentication
    ){
        try{
            User user = (User) authentication.getPrincipal();
            return shoppingCartAppService.addAccommodationToShoppingCart(user.getUsername(), id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }catch (RuntimeException exception)
        {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/confirm")
    @Operation(summary = "The user confirms the reservations", description = "The logged in user confirms the reservation and closes the cart")
    public ResponseEntity<ShoppingCartDto> confirmShoppingCart(Authentication authentication)
    {
        try{
            User user = (User) authentication.getPrincipal();
            return shoppingCartAppService.confirmReservations(user.getUsername())
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }catch (RuntimeException ex)
        {
            return ResponseEntity.badRequest().build();
        }
    }
}


