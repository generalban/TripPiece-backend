package umc.TripPiece.domain;

import jakarta.persistence.*;
import lombok.Getter;
import umc.TripPiece.domain.common.BaseEntity;

@Entity
@Getter
public class Picture extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String pictureUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_piece_id")
    private TripPiece tripPiece;


}
