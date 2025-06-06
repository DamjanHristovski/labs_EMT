package mk.finki.ukim.mk.labs_emt.repository;

import mk.finki.ukim.mk.labs_emt.model.domain.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
}
