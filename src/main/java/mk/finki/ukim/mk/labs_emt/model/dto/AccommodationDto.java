package mk.finki.ukim.mk.labs_emt.model.dto;

import lombok.Data;

@Data
public class AccommodationDto {
    private String accommodation_name;
    private String category;
    private Long host_id;
    private Integer numRooms;
    private Boolean rented;

    public AccommodationDto(String accommodation_name, String category, Long host_id, Integer numRooms, Boolean rented) {
        this.accommodation_name = accommodation_name;
        this.category = category;
        this.host_id = host_id;
        this.numRooms = numRooms;
        this.rented = rented;
    }

    public String getAccommodation_name() {
        return accommodation_name;
    }

    public void setAccommodation_name(String accommodation_name) {
        this.accommodation_name = accommodation_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getHost_id() {
        return host_id;
    }

    public void setHost_id(Long host_id) {
        this.host_id = host_id;
    }

    public Integer getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(Integer numRooms) {
        this.numRooms = numRooms;
    }

    public Boolean getRented() {
        return rented;
    }

    public void setRented(Boolean rented) {
        this.rented = rented;
    }
}
