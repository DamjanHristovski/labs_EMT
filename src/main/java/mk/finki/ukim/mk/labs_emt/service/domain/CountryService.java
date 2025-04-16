package mk.finki.ukim.mk.labs_emt.service.domain;

import mk.finki.ukim.mk.labs_emt.model.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(Long id);
    void deleteById(Long id);
    Optional<Country> save(Country country);
    Optional<Country> update(Long id, Country country);
}
