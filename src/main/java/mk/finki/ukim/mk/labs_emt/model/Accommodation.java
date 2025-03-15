package mk.finki.ukim.mk.labs_emt.model;

import lombok.Data;
import jakarta.persistence.*;
import mk.finki.ukim.mk.labs_emt.model.Enum.Categories;

@Data
@Entity
public class Accommodation {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long accommodation_id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Categories category;

    @ManyToOne
    private Host host;

    private Integer numRooms;

    private Boolean rented;



    public Accommodation() {

    }

    public Accommodation(String name, Categories category, Host host, Integer numRooms, Boolean rented) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        this.rented = rented;
    }

    public Long getAccommodation_id() {
        return accommodation_id;
    }

    public void setAccommodation_id(Long accommodation_id) {
        this.accommodation_id = accommodation_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
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
