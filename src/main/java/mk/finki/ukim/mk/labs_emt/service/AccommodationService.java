package mk.finki.ukim.mk.labs_emt.service;

import mk.finki.ukim.mk.labs_emt.model.Accommodation;
import mk.finki.ukim.mk.labs_emt.model.dto.AccommodationDto;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    Optional<Accommodation> save(AccommodationDto accommodation);
    List<Accommodation> findAll();
    Optional<Accommodation> findById(Long id);
    Optional<Accommodation> markAsRented(Long id);
    Optional<Accommodation> update(Long id, AccommodationDto accommodation);
    void delete(Long id);
}
