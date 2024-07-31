package umc.TripPiece.web.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.TripPiece.domain.enums.Gender;

public class UserRequestDto {

    /* 회원가입 */
    @Getter
    @NoArgsConstructor
    public static class SignUpDto {

        @NotBlank(message = "이름은 필수 입력 항목입니다.")
        @Size(min = 2, max = 10, message = "이름은 2자에서 10자 사이여야 합니다.")
        private String name;

        @NotBlank(message = "이메일은 필수 입력 항목입니다.")
        @Email(message = "유효한 이메일 주소여야 합니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
        @Pattern(
                regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,15}$",
                message = "비밀번호는 영문, 숫자, 특수문자를 포함한 8자에서 15자 사이여야 합니다."
        )
        private String password;

        @NotBlank(message = "닉네임은 필수 입력 항목입니다.")
        @Size(min = 2, max = 10, message = "닉네임은 2자에서 10자 사이여야 합니다.")
        private String nickname;

        private Gender gender;
        private String birth;
        private String profileImg;

        @NotBlank(message = "국가는 필수 입력 항목입니다.")
        private String country;

    }

    /* 로그인 */
    @Getter
    public static class LoginDto {
        String email;
        String password;
    }

}