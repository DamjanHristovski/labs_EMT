package mk.finki.ukim.mk.labs_emt.dto;

import mk.finki.ukim.mk.labs_emt.model.domain.Country;
import mk.finki.ukim.mk.labs_emt.model.domain.Host;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayHostDto(
        Long id,
        String name,
        String surname,
        Long countryId
) {

    public static DisplayHostDto from(Host host)
    {
        return new DisplayHostDto(
                host.getId(),
                host.getName(),
                host.getSurname(),
                host.getCountry().getCountry_id()
        );
    }

    public static List<DisplayHostDto> from(List<Host> hosts)
    {
        return hosts.stream().map(DisplayHostDto::from).collect(Collectors.toList());
    }

    public Host toHost(Country country)
    {
        return new Host(name,surname,country);
    }

}
