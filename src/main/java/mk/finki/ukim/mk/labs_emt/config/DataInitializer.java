package mk.finki.ukim.mk.labs_emt.config;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.labs_emt.model.Enum.Role;
import mk.finki.ukim.mk.labs_emt.model.domain.Accommodation;
import mk.finki.ukim.mk.labs_emt.model.domain.Country;
import mk.finki.ukim.mk.labs_emt.model.Enum.Categories;
import mk.finki.ukim.mk.labs_emt.model.domain.Host;
import mk.finki.ukim.mk.labs_emt.model.domain.User;
import mk.finki.ukim.mk.labs_emt.repository.AccommodationRepository;
import mk.finki.ukim.mk.labs_emt.repository.CountryRepository;
import mk.finki.ukim.mk.labs_emt.repository.HostRepository;
import mk.finki.ukim.mk.labs_emt.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer{

    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;
    private final AccommodationRepository accommodationRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public DataInitializer(HostRepository hostRepository, CountryRepository countryRepository, AccommodationRepository accommodationRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
        this.accommodationRepository = accommodationRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostConstruct
    public void init(){
        Country country1 = new Country("USA", "North America");
        Country country2 = new Country("Italy", "Europe");

        countryRepository.save(country1);
        countryRepository.save(country2);

        Host host1 = new Host("John", "Doe", country1);
        Host host2 = new Host("Maria", "Rossi", country2);

        hostRepository.save(host1);
        hostRepository.save(host2);

        Accommodation accommodation1 = new Accommodation("Oceanfront Villa",Categories.HOUSE,host1,5,false);
        Accommodation accommodation2 = new Accommodation("City Center Apartment",Categories.APARTMENT,host2,3,false);

        accommodationRepository.save(accommodation1);
        accommodationRepository.save(accommodation2);

        userRepository.save(new User(
                "user",
                passwordEncoder.encode("user"),
                "user",
                "user",
                Role.ROLE_HOST
        ));


    }
}
