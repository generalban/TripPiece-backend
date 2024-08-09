package umc.TripPiece.service;

import umc.TripPiece.domain.User;
import umc.TripPiece.web.dto.request.UserRequestDto;

public interface UserService {
    /* 회원가입 */
    User signUp(UserRequestDto.SignUpDto request);

    /* 카카오 회원가입 */
    User signUpKakao(UserRequestDto.SignUpKakaoDto request);

    /* 로그인 */
    User login(UserRequestDto.LoginDto request);

    /* 카카오 로그인 */
    User loginKakao(UserRequestDto.LoginKakaoDto request);

    /* 토큰 재발급 */
    User reissue(UserRequestDto.ReissueDto request);

    /* 로그아웃 */
    void logout(Long userId);

    User save(User user);
}
