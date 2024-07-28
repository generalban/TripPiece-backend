package umc.TripPiece.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Country {

    @Id @GeneratedValue
    @Column(name = "country_id")
    private BigInteger id;

    private String name;
    private String countryImage;

    @OneToMany(mappedBy = "country")
    private List<City> cities = new ArrayList<>();

}
