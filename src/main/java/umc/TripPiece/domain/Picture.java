package umc.TripPiece.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.TripPiece.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Picture extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String pictureUrl;

    @Column
    @ColumnDefault("false")
    @Setter
    private Boolean travel_thumbnail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_piece_id")
    private TripPiece tripPiece;


}
