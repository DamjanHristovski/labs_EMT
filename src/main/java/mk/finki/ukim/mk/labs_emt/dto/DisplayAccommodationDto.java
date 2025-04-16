package mk.finki.ukim.mk.labs_emt.dto;

import mk.finki.ukim.mk.labs_emt.model.Enum.Categories;
import mk.finki.ukim.mk.labs_emt.model.domain.Accommodation;
import mk.finki.ukim.mk.labs_emt.model.domain.Host;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAccommodationDto(
        Long id,
        String name,
        Categories category,
        Long host,
        Integer numRooms,
        Boolean rented
) {
    public static DisplayAccommodationDto from(Accommodation accommodation)
    {
        return new DisplayAccommodationDto(
                accommodation.getAccommodation_id(),
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getHost().getId(),
                accommodation.getNumRooms(),
                accommodation.getRented()
        );
    }

    public static List<DisplayAccommodationDto> from(List<Accommodation> accommodations)
    {
        return accommodations.stream().map(DisplayAccommodationDto::from).collect(Collectors.toList());
    }

    public Accommodation toAccommodation(Categories category, Host host)
    {
        return new Accommodation(name,category,host,numRooms,rented);
    }
}
