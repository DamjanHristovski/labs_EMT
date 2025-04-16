package mk.finki.ukim.mk.labs_emt.service.application;

import mk.finki.ukim.mk.labs_emt.dto.CreateHostDto;
import mk.finki.ukim.mk.labs_emt.dto.DisplayHostDto;

import java.util.List;
import java.util.Optional;

public interface HostAppService {
    List<DisplayHostDto> findALl();
    Optional<DisplayHostDto> findById(Long id);
    Optional<DisplayHostDto> save(CreateHostDto host);
    Optional<DisplayHostDto> update(Long id, CreateHostDto hostDto);
    void deleteById(Long id);
}
