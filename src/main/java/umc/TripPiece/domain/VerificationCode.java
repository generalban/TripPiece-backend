package umc.TripPiece.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class VerificationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String code;
    private LocalDateTime expirationTime;

    public VerificationCode(String email, String code, int expirationMinutes) {
        this.email = email;
        this.code = code;
        this.expirationTime = LocalDateTime.now().plusMinutes(expirationMinutes);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expirationTime);
    }
}
