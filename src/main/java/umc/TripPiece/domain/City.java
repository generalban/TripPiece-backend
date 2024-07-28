package umc.TripPiece.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class City {
    @Id @GeneratedValue
    @Column(name = "city_id")
    private BigInteger id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    private String name;
    private String comment;
    private String cityImage;
    private BigInteger logCount;

    @OneToMany(mappedBy = "city")
    private List<Travel> travels = new ArrayList<>();
}
