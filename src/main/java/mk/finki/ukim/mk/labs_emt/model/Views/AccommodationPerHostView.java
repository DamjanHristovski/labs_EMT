package mk.finki.ukim.mk.labs_emt.model.Views;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.internal.log.SubSystemLogging;

@Data
@Entity
@Subselect("SELECT * FROM accommodation_count_by_host")
@Immutable
public class AccommodationPerHostView {

    @Id
    @Column(name="host_id")
    private Long hostId;

    @Column(name="accommodation_count")
    private Integer numAccommodations;
}
