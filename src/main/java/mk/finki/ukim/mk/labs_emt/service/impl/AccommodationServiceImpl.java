package mk.finki.ukim.mk.labs_emt.service.impl;

import mk.finki.ukim.mk.labs_emt.model.Accommodation;
import mk.finki.ukim.mk.labs_emt.model.Enum.Categories;
import mk.finki.ukim.mk.labs_emt.model.Host;
import mk.finki.ukim.mk.labs_emt.model.dto.AccommodationDto;
import mk.finki.ukim.mk.labs_emt.repository.AccommodationRepository;
import mk.finki.ukim.mk.labs_emt.service.AccommodationService;
import mk.finki.ukim.mk.labs_emt.service.CountryService;
import mk.finki.ukim.mk.labs_emt.service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final HostService hostService;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostService hostService) {
        this.accommodationRepository = accommodationRepository;
        this.hostService = hostService;
    }

    @Override
    public Optional<Accommodation> save(AccommodationDto accommodation) {
        if (accommodation.getAccommodation_name() != null &&
            accommodation.getCategory() != null && accommodation.getRented() != null
                && this.hostService.findById(accommodation.getHost_id()).isPresent()
                && accommodation.getNumRooms() != null)
        {
            Host host = this.hostService.findById(accommodation.getHost_id()).get();
            return Optional.of(
                    this.accommodationRepository.save(
                            new Accommodation(accommodation.getAccommodation_name(), Categories.valueOf(accommodation.getCategory()),host,accommodation.getNumRooms(),accommodation.getRented())
                    )
            );
        }
        return Optional.empty();
    }

    @Override
    public List<Accommodation> findAll() {
        return this.accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return this.accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> markAsRented(Long id) {
        return this.accommodationRepository.findById(id).map(
                accommodation -> {
                    if (accommodation.getRented() != null || accommodation.getRented()){
                        accommodation.setRented(true);
                    }
                    return this.accommodationRepository.save(accommodation);
                }
        );
    }

    @Override
    public Optional<Accommodation> update(Long id, AccommodationDto accommodation) {
        return this.accommodationRepository.findById(id).map(
                existingAccommodation -> {
                    if (accommodation.getAccommodation_name() != null)
                    {
                        existingAccommodation.setName(accommodation.getAccommodation_name());
                    }
                    if (accommodation.getRented() != null)
                    {
                        existingAccommodation.setRented(accommodation.getRented());
                    }
                    if (accommodation.getNumRooms() != null){
                        existingAccommodation.setNumRooms(accommodation.getNumRooms());
                    }
                    if (this.hostService.findById(accommodation.getHost_id()).isPresent())
                    {
                        existingAccommodation.setHost(this.hostService.findById(accommodation.getHost_id()).get());
                    }
                    if (accommodation.getCategory() != null)
                    {
                        existingAccommodation.setCategory(Categories.valueOf(accommodation.getCategory()));
                    }
                    return accommodationRepository.save(existingAccommodation);
                }
        );
    }

    @Override
    public void delete(Long id) {
        this.accommodationRepository.deleteById(id);
    }
}
