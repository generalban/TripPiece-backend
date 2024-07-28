package umc.TripPiece.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import umc.TripPiece.domain.common.BaseEntity;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Travel extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "travel_id")
    private BigInteger id;

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
    private BigInteger likeCount;
    private String thumbnail;

    @OneToMany(mappedBy = "travel")
    private List<Like> likes = new ArrayList<>();

}