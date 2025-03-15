package mk.finki.ukim.mk.labs_emt.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.finki.ukim.mk.labs_emt.model.Accommodation;
import mk.finki.ukim.mk.labs_emt.model.dto.AccommodationDto;
import mk.finki.ukim.mk.labs_emt.service.AccommodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //http://localhost:8080/swagger-ui.html
@RequestMapping("/api/accommodations")
@Tag(name = "Accommodations", description = "Manage accommodation's information")
public class AccommodationController {
    private final AccommodationService accommodationService;

    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping
    @Operation(summary = "Show all accommodations")
    public List<Accommodation> getAllAccommodations() {
        return accommodationService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find an accommodation by its id")
    private ResponseEntity<Accommodation> findById(@PathVariable Long id) {
        return accommodationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new accommodation")
    public ResponseEntity<Accommodation> save (@RequestBody AccommodationDto accommodation)
    {
        return accommodationService.save(accommodation)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Edit an existing accommodation by its id")
    public ResponseEntity<Accommodation> update(@PathVariable Long id, @RequestBody AccommodationDto accommodation)
    {
        return accommodationService.update(id, accommodation)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/mark/{id}")
    @Operation(summary = "Mark an accommodation by its id")
    public ResponseEntity<Accommodation> markAsRented(@PathVariable Long id)
    {
        return accommodationService.markAsRented(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete an accommodation by its id")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        if (accommodationService.findById(id).isPresent())
        {
            accommodationService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
