package mk.finki.ukim.mk.labs_emt.service.application.impl;

import mk.finki.ukim.mk.labs_emt.dto.CreateAccommodationDto;
import mk.finki.ukim.mk.labs_emt.dto.DisplayAccommodationDto;
import mk.finki.ukim.mk.labs_emt.model.domain.Accommodation;
import mk.finki.ukim.mk.labs_emt.model.domain.Host;
import mk.finki.ukim.mk.labs_emt.service.application.AccommodationAppService;
import mk.finki.ukim.mk.labs_emt.service.domain.AccommodationService;
import mk.finki.ukim.mk.labs_emt.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationAppServiceImpl implements AccommodationAppService {
   private final AccommodationService accommodationService;
   private final HostService hostService;

    public AccommodationAppServiceImpl(AccommodationService accommodationService, HostService hostService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
    }

    @Override
    public Optional<DisplayAccommodationDto> save(CreateAccommodationDto accommodation) {
        Optional<Host> host = hostService.findById(accommodation.host());
        if (host.isPresent()) {
            return accommodationService.save(accommodation.toAccomodation(accommodation.category(),host.get())).map(DisplayAccommodationDto::from);
        }
        return Optional.empty();
    }

    @Override
    public List<DisplayAccommodationDto> findAll() {
        return accommodationService.findAll().stream().map(DisplayAccommodationDto::from).toList();
    }

    @Override
    public Optional<DisplayAccommodationDto> findById(Long id) {
        return accommodationService.findById(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> markAsRented(Long id) {
        Optional<Accommodation> accommodation = accommodationService.findById(id);
        if(accommodation.isPresent()) {
            Accommodation acc = accommodation.get();
            acc.setRented(true);
            return accommodationService.save(acc).map(DisplayAccommodationDto::from);
        }
        return Optional.empty();

    }

    @Override
    public Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto accommodation) {
        Optional<Host>host = hostService.findById(accommodation.host());
        if (host.isPresent()) {
            return accommodationService.update(id,accommodation.toAccomodation(accommodation.category(),host.get())).map(DisplayAccommodationDto::from);
        }
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        accommodationService.delete(id);
    }
}
