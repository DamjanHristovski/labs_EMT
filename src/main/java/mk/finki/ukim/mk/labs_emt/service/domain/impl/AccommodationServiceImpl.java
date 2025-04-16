package mk.finki.ukim.mk.labs_emt.service.domain.impl;

import mk.finki.ukim.mk.labs_emt.model.domain.Accommodation;
import mk.finki.ukim.mk.labs_emt.model.Enum.Categories;
import mk.finki.ukim.mk.labs_emt.model.domain.Host;
import mk.finki.ukim.mk.labs_emt.repository.AccommodationRepository;
import mk.finki.ukim.mk.labs_emt.service.domain.AccommodationService;
import mk.finki.ukim.mk.labs_emt.service.domain.HostService;
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
    public Optional<Accommodation> save(Accommodation accommodation) {
        if (accommodation.getName() != null &&
                accommodation.getCategory() != null && accommodation.getRented() != null
                && this.hostService.findById(accommodation.getHost().getId()).isPresent()
                && accommodation.getNumRooms() != null)
        {
            Host host = this.hostService.findById(accommodation.getHost().getId()).get();
            return Optional.of(
                    this.accommodationRepository.save(
                            new Accommodation(accommodation.getName(), Categories.valueOf(accommodation.getCategory().toString()),host,accommodation.getNumRooms(),accommodation.getRented())
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
    public Optional<Accommodation> update(Long id, Accommodation accommodation) {
        return this.accommodationRepository.findById(id).map(
                existingAccommodation -> {
                    if (accommodation.getName() != null)
                    {
                        existingAccommodation.setName(accommodation.getName());
                    }
                    if (accommodation.getRented() != null)
                    {
                        existingAccommodation.setRented(accommodation.getRented());
                    }
                    if (accommodation.getNumRooms() != null){
                        existingAccommodation.setNumRooms(accommodation.getNumRooms());
                    }
                    if (this.hostService.findById(accommodation.getHost().getId()).isPresent())
                    {
                        existingAccommodation.setHost(this.hostService.findById(accommodation.getHost().getId()).get());
                    }
                    if (accommodation.getCategory() != null)
                    {
                        existingAccommodation.setCategory(Categories.valueOf(accommodation.getCategory().toString()));
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