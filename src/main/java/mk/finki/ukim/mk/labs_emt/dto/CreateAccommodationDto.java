package mk.finki.ukim.mk.labs_emt.dto;

import mk.finki.ukim.mk.labs_emt.model.Enum.Categories;
import mk.finki.ukim.mk.labs_emt.model.domain.Accommodation;
import mk.finki.ukim.mk.labs_emt.model.domain.Host;

import java.util.List;
import java.util.stream.Collectors;

public record CreateAccommodationDto(
        String name,
        Categories category,
        Long host,
        Integer numRooms,
        Boolean rented
) {
    public static CreateAccommodationDto from(Accommodation accommodation) {
        {
            return new CreateAccommodationDto(
                    accommodation.getName(),
                    accommodation.getCategory(),
                    accommodation.getHost().getId(),
                    accommodation.getNumRooms(),
                    accommodation.getRented()
            );
        }
    }

    public static List<CreateAccommodationDto> from(List<Accommodation> accommodations) {
        return accommodations.stream().map(CreateAccommodationDto::from).collect(Collectors.toList());
    }
    public Accommodation toAccomodation(Categories category, Host host)
    {
        return new Accommodation(name, category, host, numRooms, rented);
    }
}
