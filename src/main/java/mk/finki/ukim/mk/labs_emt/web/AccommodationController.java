package mk.finki.ukim.mk.labs_emt.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.finki.ukim.mk.labs_emt.dto.CreateAccommodationDto;
import mk.finki.ukim.mk.labs_emt.dto.DisplayAccommodationDto;
import mk.finki.ukim.mk.labs_emt.model.domain.Accommodation;
import mk.finki.ukim.mk.labs_emt.service.application.AccommodationAppService;
import mk.finki.ukim.mk.labs_emt.service.application.HostAppService;
import mk.finki.ukim.mk.labs_emt.service.domain.AccommodationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //http://localhost:8080/swagger-ui.html
@RequestMapping("/api/accommodations")
@Tag(name = "Accommodations", description = "Manage accommodation's information")
public class AccommodationController {
    private final AccommodationAppService accommodationService;
    private final HostAppService hostAppService;

    public AccommodationController(AccommodationAppService accommodationService, HostAppService hostAppService) {
        this.accommodationService = accommodationService;
        this.hostAppService = hostAppService;
    }

    @GetMapping
    @Operation(summary = "Show all accommodations")
    public List<DisplayAccommodationDto> getAllAccommodations() {
        return this.accommodationService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find an accommodation by its id")
    private ResponseEntity<DisplayAccommodationDto> findById(@PathVariable Long id) {
        return accommodationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new accommodation")
    public ResponseEntity<DisplayAccommodationDto> save (@RequestBody CreateAccommodationDto accommodation)
    {
        return accommodationService.save(accommodation)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Edit an existing accommodation by its id")
    public ResponseEntity<DisplayAccommodationDto> update(@PathVariable Long id, @RequestBody CreateAccommodationDto accommodation)
    {
        return accommodationService.update(id, accommodation)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/mark/{id}")
    @Operation(summary = "Mark an accommodation by its id")
    public ResponseEntity<DisplayAccommodationDto> markAsRented(@PathVariable Long id)
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
    @GetMapping("/per-host/{id}")
    @Operation(summary = "Find all the accommodations per host")
    public ResponseEntity<?> findAccommodationsPerHost(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(hostAppService.findAccommodationById(id));
    }
}
