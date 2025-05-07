package mk.finki.ukim.mk.labs_emt.service.application.impl;

import mk.finki.ukim.mk.labs_emt.dto.CreateCountryDto;
import mk.finki.ukim.mk.labs_emt.dto.DisplayCountryDto;
import mk.finki.ukim.mk.labs_emt.model.Views.HostPerCountryView;
import mk.finki.ukim.mk.labs_emt.repository.HostPerCountryViewRepository;
import mk.finki.ukim.mk.labs_emt.service.application.CountryAppService;
import mk.finki.ukim.mk.labs_emt.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryAppServiceImpl implements CountryAppService {
    private final CountryService countryService;
    private final HostPerCountryViewRepository hostPerCountryView;

    public CountryAppServiceImpl(CountryService countryService, HostPerCountryViewRepository hostPerCountryView) {
        this.countryService = countryService;
        this.hostPerCountryView = hostPerCountryView;
    }

    @Override
    public List<DisplayCountryDto> findAll() {
        return countryService.findAll().stream().map(DisplayCountryDto::from).toList();
    }

    @Override
    public Optional<DisplayCountryDto> findById(Long id) {
        return countryService.findById(id).map(DisplayCountryDto::from);
    }

    @Override
    public void deleteById(Long id) {
        countryService.deleteById(id);
    }

    @Override
    public Optional<DisplayCountryDto> save(CreateCountryDto country) {
        return countryService.save(country.toCountry())
                .map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> update(Long id, CreateCountryDto country) {
        return countryService.update(id,country.toCountry()).map(DisplayCountryDto::from);
    }

    @Override
    public HostPerCountryView findHostPerCountry(Long id) {
        return this.hostPerCountryView.findById(id).orElseThrow();
    }

    @Override
    public void refreshMaterializedView() {
        this.hostPerCountryView.refreshMaterializedViews();
    }
}
