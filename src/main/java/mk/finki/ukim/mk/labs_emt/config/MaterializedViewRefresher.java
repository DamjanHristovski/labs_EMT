package mk.finki.ukim.mk.labs_emt.config;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.labs_emt.repository.AccommodationPerHostViewRepository;
import mk.finki.ukim.mk.labs_emt.repository.HostPerCountryViewRepository;
import org.springframework.stereotype.Component;


@Component
public class MaterializedViewRefresher {
    private final HostPerCountryViewRepository hostPerCountryViewRepository;
    private final AccommodationPerHostViewRepository accommodationPerHostViewRepository;

    public MaterializedViewRefresher(HostPerCountryViewRepository hostPerCountryViewRepository, AccommodationPerHostViewRepository accommodationPerHostViewRepository) {
        this.hostPerCountryViewRepository = hostPerCountryViewRepository;
        this.accommodationPerHostViewRepository = accommodationPerHostViewRepository;
    }

    @PostConstruct
    public void init() {
        hostPerCountryViewRepository.refreshMaterializedViews();
        accommodationPerHostViewRepository.refreshMaterializedViews();
    }
}
