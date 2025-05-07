package mk.finki.ukim.mk.labs_emt.service.domain.impl;

import mk.finki.ukim.mk.labs_emt.model.Projections.HostNameProjection;
import mk.finki.ukim.mk.labs_emt.model.domain.Host;
import mk.finki.ukim.mk.labs_emt.repository.HostRepository;
import mk.finki.ukim.mk.labs_emt.service.domain.CountryService;
import mk.finki.ukim.mk.labs_emt.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;
    private final CountryService countryService;

    public HostServiceImpl(HostRepository hostRepository, CountryService countryService) {
        this.hostRepository = hostRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Host> findALl() {
        return this.hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return this.hostRepository.findById(id);
    }

    @Override
    public Optional<Host> save(Host host) {
        return Optional.of(this.hostRepository.save(host));
    }

    @Override
    public Optional<Host> update(Long id, Host hostDto) {
        return this.hostRepository.findById(id).map(
                existingHost -> {
                    if (hostDto.getName() != null)
                    {
                        existingHost.setName(hostDto.getName());
                    }
                    if (hostDto.getSurname() != null)
                    {
                        existingHost.setSurname(hostDto.getSurname());
                    }
                    if (countryService.findById(hostDto.getCountry().getCountry_id()).isPresent())
                    {
                        existingHost.setCountry(countryService.findById(hostDto.getCountry().getCountry_id()).get());
                    }
                    return this.hostRepository.save(existingHost);
                }
        );
    }

    @Override
    public void deleteById(Long id) {
        this.hostRepository.deleteById(id);
    }

    @Override
    public List<HostNameProjection> getAllTheProjected() {
        return this.hostRepository.findAllProjections();
    }


}
