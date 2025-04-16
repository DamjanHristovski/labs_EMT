package mk.finki.ukim.mk.labs_emt.service.domain.impl;

import mk.finki.ukim.mk.labs_emt.model.domain.Country;
import mk.finki.ukim.mk.labs_emt.repository.CountryRepository;
import mk.finki.ukim.mk.labs_emt.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.countryRepository.deleteById(id);
    }

    @Override
    public Optional<Country> save(Country country) {
        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public Optional<Country> update(Long id,Country country) { // Moze i so countryDto
        return this.countryRepository.findById(id).map(
                existingCountry -> {
                    if (country.getName() != null) // Proverka dali isprateniot country e validen.
                    {
                        existingCountry.setName(country.getName());
                    }
                    if (country.getContinent() != null)
                    {
                        existingCountry.setContinent(country.getContinent());
                    }
                    return countryRepository.save(existingCountry);
                }
        );
    }


}
