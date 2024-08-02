package umc.TripPiece.domain.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JWTUtil {

    private final TokenProvider tokenProvider;
    private final JWTProperties jwtProperties;

    @Autowired
    public JWTUtil(TokenProvider tokenProvider, JWTProperties jwtProperties) {
        this.tokenProvider = tokenProvider;
        this.jwtProperties = jwtProperties;
    }

    public String createAccessToken(String email) {
        return tokenProvider.createToken(email, jwtProperties.getAccessTokenValidity());
    }

    public String createRefreshToken(String email) {
        return tokenProvider.createToken(email, jwtProperties.getRefreshTokenValidity());
    }

    public boolean validateToken(String token) {
        return tokenProvider.validateToken(token);
    }

    public String getEmailFromToken(String token) {
        return tokenProvider.getEmailFromToken(token);
    }
}
