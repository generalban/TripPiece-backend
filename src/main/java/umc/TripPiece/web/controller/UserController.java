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
import umc.TripPiece.payload.ApiResponse;
import umc.TripPiece.service.UserService;
import umc.TripPiece.web.dto.request.UserRequestDto;
import umc.TripPiece.web.dto.response.UserResponseDto;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
//    private final JWTUtul jwtUtul;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    @Operation(summary = "회원가입 API",
    description = "회원가입 API 구현")
    public ApiResponse<UserResponseDto.SignUpResultDto> signUp(@RequestBody @Valid UserRequestDto.SignUpDto request) {
        User user = userService.signUp(request);
        return ApiResponse.onSuccess(UserConverter.toSignUpResultDto(user));
    }
}
