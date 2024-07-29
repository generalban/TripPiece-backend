package umc.TripPiece.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import umc.TripPiece.domain.common.BaseEntity;
import umc.TripPiece.domain.enums.Category;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class TripPiece extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'MEMO'")
    private Category category;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Boolean travel_contain;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_id")
    private Travel travel;

    @OneToMany(mappedBy = "tripPiece", cascade = CascadeType.ALL)
    private List<Picture> pictures = new ArrayList<>();

}
