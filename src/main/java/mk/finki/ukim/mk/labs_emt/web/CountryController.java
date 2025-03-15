package mk.finki.ukim.mk.labs_emt.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.finki.ukim.mk.labs_emt.model.Country;
import mk.finki.ukim.mk.labs_emt.model.dto.CountryDto;
import mk.finki.ukim.mk.labs_emt.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@Tag(name = "Country", description = "Manage Country information")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    @Operation(summary = "Show all countries")
    public List<Country> getAllCountries()
    {
        return countryService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Show a country by its id")
    public ResponseEntity<Country> findById(@PathVariable Long id)
    {
        return countryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    @Operation(summary = "Add a new country to the list")
    public ResponseEntity<Country> save (@RequestBody Country country)
    {
        return countryService.save(country)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/edit/{id}")
    @Operation(summary = "Edit an existing country by its id")
    public ResponseEntity<Country> update (@PathVariable Long id, @RequestBody Country country)
    {
        return countryService.update(id,country)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete an existing country by its id")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        if (countryService.findById(id).isPresent())
        {
            countryService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
