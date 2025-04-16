package mk.finki.ukim.mk.labs_emt.service.domain;
import mk.finki.ukim.mk.labs_emt.model.domain.Host;
import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findALl();
    Optional<Host> findById(Long id);
    Optional<Host> save(Host host);
    Optional<Host> update(Long id, Host hostDto);
    void deleteById(Long id);
}

