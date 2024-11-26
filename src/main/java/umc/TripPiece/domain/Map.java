package umc.TripPiece.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import umc.TripPiece.domain.enums.Color;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Map {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visit_id")
    private Long visitId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "country_code", nullable = false)
    private String countryCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    private Color color;  // 단일 색상

    // 여러 색상을 위한 필드 추가
    @ElementCollection(targetClass = Color.class)
    @CollectionTable(name = "map_colors", joinColumns = @JoinColumn(name = "map_id"))
    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    private List<Color> colors = new ArrayList<>();  // 다중 색상

    // City와 연관 관계 추가
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
}
