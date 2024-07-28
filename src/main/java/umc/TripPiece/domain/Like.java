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

    //    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user; 회원 엔티티 만들면 연결

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "travel_id")
   private Travel travel;

}
