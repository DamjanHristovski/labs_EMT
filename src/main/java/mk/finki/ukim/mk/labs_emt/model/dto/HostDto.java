package mk.finki.ukim.mk.labs_emt.model.dto;

import lombok.Data;

@Data
public class HostDto {
    private String host_name;
    private String host_surname;
    private Long country_id;

    public HostDto(String host_name, String host_surname, Long country_id) {
        this.host_name = host_name;
        this.host_surname = host_surname;
        this.country_id = country_id;
    }

    public String getHost_name() {
        return host_name;
    }

    public void setHost_name(String host_name) {
        this.host_name = host_name;
    }

    public String getHost_surname() {
        return host_surname;
    }

    public void setHost_surname(String host_surname) {
        this.host_surname = host_surname;
    }

    public Long getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Long country_id) {
        this.country_id = country_id;
    }
}
