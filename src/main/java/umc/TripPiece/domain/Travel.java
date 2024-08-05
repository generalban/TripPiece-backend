package umc.TripPiece.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.TripPiece.domain.common.BaseEntity;
import umc.TripPiece.domain.enums.TravelStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Travel extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "travel_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private boolean travelOpen;
    private Long likeCount;
    private String thumbnail;

    @Enumerated(EnumType.STRING)
    private TravelStatus status;

//    @OneToMany(mappedBy = "travel")
//    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "travel")
    private List<TripPiece> tripPieces = new ArrayList<>();

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    public void setStatus(TravelStatus status) {
        this.status = status;
    }

}
