package mk.finki.ukim.mk.labs_emt.model.Views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.springframework.context.annotation.Configuration;
@Data
@Entity
@Subselect("SELECT * FROM hosts_by_country")
@Immutable
public class HostPerCountryView {

        @Id
        @Column(name="country_id")
        Long countryId;

        @Column(name="host_count")
        Long hostCount;

}
