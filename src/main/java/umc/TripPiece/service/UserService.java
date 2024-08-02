package umc.TripPiece.service;

import umc.TripPiece.domain.User;
import umc.TripPiece.web.dto.request.UserRequestDto;

public interface UserService {
    /* 회원가입 */
    User signUp(UserRequestDto.SignUpDto request);

    /* 로그인 */
    User login(UserRequestDto.LoginDto request);

}
