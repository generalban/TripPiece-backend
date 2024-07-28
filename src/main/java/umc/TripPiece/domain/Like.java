package umc.TripPiece.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigInteger;

@Entity
@Getter
public class Like {
    @Id @GeneratedValue
    @Column(name = "like_id")
    private BigInteger id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "travel_id")
   private Travel travel;

}
