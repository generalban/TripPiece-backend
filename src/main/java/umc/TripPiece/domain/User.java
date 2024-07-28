package umc.TripPiece.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import umc.TripPiece.domain.common.BaseEntity;

@Entity
@Getter
@Setter

public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id", unique = true, nullable = false)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Column(nullable = false, length = 20)
    private String gender;

    @Column
    private String profile_img;

    @Column(nullable = false, length = 30)
    private String country;

    @Column (nullable = false)
    private Boolean gps_consent;

    @Column(nullable = false, length = 10)
    private String method;

    @Column(nullable = false)
    private Boolean is_public;
}