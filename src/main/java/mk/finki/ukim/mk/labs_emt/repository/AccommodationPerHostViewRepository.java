package mk.finki.ukim.mk.labs_emt.repository;

import jakarta.transaction.Transactional;
import mk.finki.ukim.mk.labs_emt.model.Views.AccommodationPerHostView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AccommodationPerHostViewRepository extends JpaRepository<AccommodationPerHostView,Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value="REFRESH MATERIALIZED VIEW public.accommodation_count_by_host", nativeQuery = true)
    void refreshMaterializedViews();
}
