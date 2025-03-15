package mk.finki.ukim.mk.labs_emt.service;

import mk.finki.ukim.mk.labs_emt.model.Host;
import mk.finki.ukim.mk.labs_emt.model.dto.HostDto;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findALl();
    Optional<Host> findById(Long id);
    Optional<Host> save(Host host);
    Optional<Host> update(Long id, HostDto hostDto);
    void deleteById(Long id);
}
