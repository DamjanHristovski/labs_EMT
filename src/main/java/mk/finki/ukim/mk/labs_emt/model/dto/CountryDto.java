package mk.finki.ukim.mk.labs_emt.model.dto;

import lombok.Data;

@Data
public class CountryDto {
    private String country_name;
    private String continent;

    public CountryDto(String country_name, String continent) {
        this.country_name = country_name;
        this.continent = continent;
    }
}
