package mk.finki.ukim.mk.labs_emt.repository;

import jakarta.transaction.Transactional;
import mk.finki.ukim.mk.labs_emt.model.Views.HostPerCountryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface HostPerCountryViewRepository extends JpaRepository<HostPerCountryView,Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value="REFRESH MATERIALIZED VIEW public.hosts_by_country", nativeQuery = true)
    void refreshMaterializedViews();
}
