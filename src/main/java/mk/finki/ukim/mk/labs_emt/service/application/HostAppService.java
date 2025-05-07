package mk.finki.ukim.mk.labs_emt.service.application;

import mk.finki.ukim.mk.labs_emt.dto.CreateHostDto;
import mk.finki.ukim.mk.labs_emt.dto.DisplayHostDto;
import mk.finki.ukim.mk.labs_emt.model.Projections.HostNameProjection;
import mk.finki.ukim.mk.labs_emt.model.Views.AccommodationPerHostView;

import java.util.List;
import java.util.Optional;

public interface HostAppService {
    List<DisplayHostDto> findALl();
    Optional<DisplayHostDto> findById(Long id);
    Optional<DisplayHostDto> save(CreateHostDto host);
    Optional<DisplayHostDto> update(Long id, CreateHostDto hostDto);
    void deleteById(Long id);
    AccommodationPerHostView findAccommodationById(Long id);
    void refreshmaterializedView();
    List<HostNameProjection> getAllNames();
}
