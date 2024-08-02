package umc.TripPiece.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.TripPiece.converter.UserConverter;
import umc.TripPiece.domain.User;
import umc.TripPiece.domain.jwt.JWTUtil;
import umc.TripPiece.payload.ApiResponse;
import umc.TripPiece.service.UserService;
import umc.TripPiece.web.dto.request.UserRequestDto;
import umc.TripPiece.web.dto.response.UserResponseDto;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JWTUtil jwtUtil;


    @Autowired
    public UserController(UserService userService, JWTUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    @Operation(summary = "회원가입 API",
    description = "회원가입")
    public ApiResponse<UserResponseDto.SignUpResultDto> signUp(@RequestBody @Valid UserRequestDto.SignUpDto request) {
        User user = userService.signUp(request);
        return ApiResponse.onSuccess(UserConverter.toSignUpResultDto(user));
    }

    @PostMapping("/login")
    @Operation(summary = "이메일 로그인 API",
    description = "이메일 로그인 (일반)")
    public ApiResponse<UserResponseDto.LoginResultDto> login(@RequestBody @Valid UserRequestDto.LoginDto request) {
        User user = userService.login(request);

        if (user != null) {
            // 로그인 성공 시 토큰 생성
            String accessToken = jwtUtil.createAccessToken(request.getEmail());
            String refreshToken = jwtUtil.createRefreshToken(request.getEmail());

            // LoginResultDTO를 생성하여 반환
            return ApiResponse.onSuccess(UserConverter.toLoginResultDto(user, accessToken, refreshToken));
        } else {
            return ApiResponse.onFailure("400", "로그인에 실패했습니다.", null);
        }
    }
}
