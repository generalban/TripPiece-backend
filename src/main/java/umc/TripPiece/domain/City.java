package umc.TripPiece.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    private String name;
    private String comment;
    private String cityImage;

    @Setter
    @Column(name = "log_count", nullable = false, columnDefinition = "BIGINT DEFAULT 0")
    private Long logCount = 0L;

    @OneToMany(mappedBy = "city")
    private List<Travel> travels = new ArrayList<>();
}
