package umc.TripPiece.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Travel {
    @Id
    @GeneratedValue
    @Column(name = "travel_id")
    private BigInteger id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user; 회원 엔티티 만들면 연결

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private boolean travelOpen;
    private BigInteger likeCount;
    private String thumbnail;

    @OneToMany(mappedBy = "travel")
    private List<Like> likes = new ArrayList<>();

}
