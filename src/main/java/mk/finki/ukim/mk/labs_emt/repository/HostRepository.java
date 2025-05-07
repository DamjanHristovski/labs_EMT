package mk.finki.ukim.mk.labs_emt.repository;

import mk.finki.ukim.mk.labs_emt.model.Projections.HostNameProjection;
import mk.finki.ukim.mk.labs_emt.model.domain.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
    @Query("select h.name as name, h.surname as surname from Host h")
    List<HostNameProjection> findAllProjections();
}
