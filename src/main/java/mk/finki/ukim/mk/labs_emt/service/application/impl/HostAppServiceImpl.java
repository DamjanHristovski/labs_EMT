package mk.finki.ukim.mk.labs_emt.service.application.impl;

import mk.finki.ukim.mk.labs_emt.dto.CreateHostDto;
import mk.finki.ukim.mk.labs_emt.dto.DisplayHostDto;
import mk.finki.ukim.mk.labs_emt.model.Projections.HostNameProjection;
import mk.finki.ukim.mk.labs_emt.model.Views.AccommodationPerHostView;
import mk.finki.ukim.mk.labs_emt.model.domain.Country;
import mk.finki.ukim.mk.labs_emt.repository.AccommodationPerHostViewRepository;
import mk.finki.ukim.mk.labs_emt.service.application.HostAppService;
import mk.finki.ukim.mk.labs_emt.service.domain.CountryService;
import mk.finki.ukim.mk.labs_emt.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostAppServiceImpl implements HostAppService {
    private final HostService hostService;
    private final CountryService countryService;
    private final AccommodationPerHostViewRepository accommodationPerHostViewRepository;

    public HostAppServiceImpl(HostService hostService, CountryService countryService, AccommodationPerHostViewRepository accommodationPerHostViewRepository) {
        this.hostService = hostService;
        this.countryService = countryService;
        this.accommodationPerHostViewRepository = accommodationPerHostViewRepository;
    }

    @Override
    public List<DisplayHostDto> findALl() {
        return hostService.findALl().stream().map(DisplayHostDto::from).toList();
    }

    @Override
    public Optional<DisplayHostDto> findById(Long id) {
        return hostService.findById(id).map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> save(CreateHostDto host) {
        Optional<Country> country = this.countryService.findById(host.country());
        if (country.isPresent()) {
            return hostService.save(host.toHost(country.get())).map(DisplayHostDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayHostDto> update(Long id, CreateHostDto hostDto) {
       Optional<Country> country = this.countryService.findById(hostDto.country());
       if (country.isPresent()) {
           return this.hostService.update(id,hostDto.toHost(country.get())).map(DisplayHostDto::from);
       }
       return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        hostService.deleteById(id);
    }

    @Override
    public AccommodationPerHostView findAccommodationById(Long id) {
        return this.accommodationPerHostViewRepository.findById(id).orElseThrow();
    }

    @Override
    public void refreshmaterializedView() {
        this.accommodationPerHostViewRepository.refreshMaterializedViews();
    }

    @Override
    public List<HostNameProjection> getAllNames() {
        return this.hostService.getAllTheProjected();
    }


}
