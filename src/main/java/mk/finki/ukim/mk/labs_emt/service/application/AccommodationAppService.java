package mk.finki.ukim.mk.labs_emt.service.application;

import mk.finki.ukim.mk.labs_emt.dto.CreateAccommodationDto;
import mk.finki.ukim.mk.labs_emt.dto.DisplayAccommodationDto;

import java.util.List;
import java.util.Optional;

public interface AccommodationAppService {
    Optional<DisplayAccommodationDto> save(CreateAccommodationDto accommodation);
    List<DisplayAccommodationDto> findAll();
    Optional<DisplayAccommodationDto> findById(Long id);
    Optional<DisplayAccommodationDto> markAsRented(Long id);
    Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto accommodation);
    void delete(Long id);
}
