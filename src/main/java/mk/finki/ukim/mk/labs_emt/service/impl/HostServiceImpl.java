package mk.finki.ukim.mk.labs_emt.service.impl;

import mk.finki.ukim.mk.labs_emt.model.Host;
import mk.finki.ukim.mk.labs_emt.model.dto.HostDto;
import mk.finki.ukim.mk.labs_emt.repository.HostRepository;
import mk.finki.ukim.mk.labs_emt.service.CountryService;
import mk.finki.ukim.mk.labs_emt.service.HostService;
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
    public Optional<Host> update(Long id, HostDto hostDto) {
        return this.hostRepository.findById(id).map(
                existingHost -> {
                    if (hostDto.getHost_name() != null)
                    {
                        existingHost.setName(hostDto.getHost_name());
                    }
                    if (hostDto.getHost_surname() != null)
                    {
                        existingHost.setSurname(hostDto.getHost_surname());
                    }
                    if (countryService.findById(hostDto.getCountry_id()).isPresent())
                    {
                        existingHost.setCountry(countryService.findById(hostDto.getCountry_id()).get());
                    }
                    return this.hostRepository.save(existingHost);
                }
        );
    }

    @Override
    public void deleteById(Long id) {
        this.hostRepository.deleteById(id);
    }


}
