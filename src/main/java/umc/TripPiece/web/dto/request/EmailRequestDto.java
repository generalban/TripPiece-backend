package umc.TripPiece.web.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class EmailRequestDto {

    /* 인증 코드 요청 */
    @Getter
    @NoArgsConstructor
    public static class SendCodeDto {

        @NotBlank(message = "이메일은 필수 입력 항목입니다.")
        @Email(message = "유효한 이메일 주소여야 합니다.")
        private String email;
    }

    /* 인증 코드 검증 */
    @Getter
    @NoArgsConstructor
    public static class VerifyCodeDto {

        @NotBlank(message = "이메일은 필수 입력 항목입니다.")
        @Email(message = "유효한 이메일 주소여야 합니다.")
        private String email;

        @NotBlank(message = "인증 코드는 필수 입력 항목입니다.")
        @Size(min = 6, max = 6, message = "인증 코드는 6자리여야 합니다.")
        private String code;
    }
}