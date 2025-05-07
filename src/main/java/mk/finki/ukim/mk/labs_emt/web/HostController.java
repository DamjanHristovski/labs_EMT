package mk.finki.ukim.mk.labs_emt.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.finki.ukim.mk.labs_emt.dto.CreateHostDto;
import mk.finki.ukim.mk.labs_emt.dto.DisplayHostDto;
import mk.finki.ukim.mk.labs_emt.model.Projections.HostNameProjection;
import mk.finki.ukim.mk.labs_emt.model.domain.Host;
import mk.finki.ukim.mk.labs_emt.repository.HostPerCountryViewRepository;
import mk.finki.ukim.mk.labs_emt.service.application.CountryAppService;
import mk.finki.ukim.mk.labs_emt.service.application.HostAppService;
import mk.finki.ukim.mk.labs_emt.service.domain.HostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
@Tag(name = "Hosts",description = "Manage host's information")
public class HostController {

    private final HostAppService hostService;
    private final CountryAppService countryAppService;


    public HostController(HostAppService hostService, CountryAppService countryAppService) {
        this.hostService = hostService;
        this.countryAppService = countryAppService;
    }

    @GetMapping
    @Operation(summary = "Show all the hosts")
    public List<DisplayHostDto> findAll()
    {
        return hostService.findALl();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Show the host by its id")
    public ResponseEntity<DisplayHostDto> findById(@PathVariable Long id)
    {
        return hostService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new host to the list")
    public ResponseEntity<DisplayHostDto> save (@RequestBody CreateHostDto host)
    {
        return hostService.save(host)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/edit/{id}")
    @Operation(summary = "Edit an existing host by its id")
    public ResponseEntity<DisplayHostDto> update (@PathVariable Long id, @RequestBody CreateHostDto host)
    {
        return hostService.update(id, host)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete an existing host by its id")
    public ResponseEntity<Void> delete (@PathVariable Long id)
    {
        if (hostService.findById(id).isPresent())
        {
            hostService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/per-country/{id}")
    @Operation(summary = "Get the number of hosts per country")
    public ResponseEntity<?> getNumberOfHostsPerCountry(@PathVariable Long id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(countryAppService.findHostPerCountry(id));
    }

    @GetMapping("/names")
    @Operation(summary = "Names of all the hosts")
    public List<HostNameProjection> getAllnames()
    {
        return hostService.getAllNames();
    }
}
