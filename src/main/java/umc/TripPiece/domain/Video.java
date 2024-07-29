package umc.TripPiece.domain;

import jakarta.persistence.*;
import lombok.Getter;
import umc.TripPiece.domain.common.BaseEntity;

@Entity
@Getter
public class Video extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String videoUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_piece_id")
    private TripPiece tripPiece;

}
