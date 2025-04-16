package mk.finki.ukim.mk.labs_emt.service.application;

import mk.finki.ukim.mk.labs_emt.dto.CreateCountryDto;
import mk.finki.ukim.mk.labs_emt.dto.DisplayCountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryAppService {
    List<DisplayCountryDto> findAll();
    Optional<DisplayCountryDto> findById(Long id);
    void deleteById(Long id);
    Optional<DisplayCountryDto> save(CreateCountryDto country);
    Optional<DisplayCountryDto> update(Long id, CreateCountryDto country);
}
