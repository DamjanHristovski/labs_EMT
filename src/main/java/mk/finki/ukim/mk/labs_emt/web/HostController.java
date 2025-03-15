package mk.finki.ukim.mk.labs_emt.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.finki.ukim.mk.labs_emt.model.Host;
import mk.finki.ukim.mk.labs_emt.model.dto.HostDto;
import mk.finki.ukim.mk.labs_emt.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
@Tag(name = "Hosts",description = "Manage host's information")
public class HostController {

    private final HostService hostService;

    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    @GetMapping
    @Operation(summary = "Show all the hosts")
    public List<Host> findAll()
    {
        return hostService.findALl();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Show the host by its id")
    public ResponseEntity<Host> findById(@PathVariable Long id)
    {
        return hostService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new host to the list")
    public ResponseEntity<Host> save (Host host)
    {
        return hostService.save(host)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/edit/{id}")
    @Operation(summary = "Edit an existing host by its id")
    public ResponseEntity<Host> update (@PathVariable Long id, @RequestBody HostDto host)
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
}
