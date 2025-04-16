package mk.finki.ukim.mk.labs_emt.service.domain;

import mk.finki.ukim.mk.labs_emt.model.domain.Accommodation;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    Optional<Accommodation> save(Accommodation accommodation);
    List<Accommodation> findAll();
    Optional<Accommodation> findById(Long id);
    Optional<Accommodation> markAsRented(Long id);
    Optional<Accommodation> update(Long id, Accommodation accommodation);
    void delete(Long id);
}