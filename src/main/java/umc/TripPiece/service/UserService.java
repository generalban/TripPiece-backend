package umc.TripPiece.service;

import umc.TripPiece.domain.User;
import umc.TripPiece.web.dto.request.UserRequestDto;

public interface UserService {
    User signUp(UserRequestDto.SignUpDto request);
}
