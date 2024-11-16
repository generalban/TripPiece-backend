package umc.TripPiece.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.TripPiece.domain.VerificationCode;
import umc.TripPiece.payload.ApiResponse;
import umc.TripPiece.repository.VerificationCodeRepository;
import umc.TripPiece.service.EmailService;
import umc.TripPiece.web.dto.request.EmailRequestDto;

import java.util.Optional;

@Tag(name = "Email", description = "이메일 인증 관련 API")
@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;
    private final VerificationCodeRepository verificationCodeRepository;

    @PostMapping("/send")
    @Operation(summary = "이메일 인증번호 전송 API",
            description = "이메일로 6자리 인증번호 발송")
    public ResponseEntity<ApiResponse<String>> sendVerificationCode(@RequestBody EmailRequestDto.SendCodeDto request) {
        String email = request.getEmail();

        // 이메일 주소 유효성 체크
        if (!isValidEmail(email)) {
            return ResponseEntity.badRequest().body(ApiResponse.onFailure("400", "유효한 이메일 주소여야 합니다.", null));
        }

        String code = emailService.generateVerificationCode();

        VerificationCode verificationCode = new VerificationCode(request.getEmail(), code, 3); // 인증코드 유효시간 (3분)
        verificationCodeRepository.save(verificationCode);

        try {
            emailService.sendVerificationCode(email, code);
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.onFailure("500", "이메일 전송에 실패했습니다.", null));
        }

        return ResponseEntity.ok(ApiResponse.onSuccess("해당 이메일로 인증번호를 전송했습니다."));
    }

    @PostMapping("/verify")
    @Operation(summary = "이메일 인증번호 검증 API",
            description = "이메일로 발송된 인증번호의 일치 여부 검증")
    public ResponseEntity<ApiResponse<String>> verifyCode(@RequestBody EmailRequestDto.VerifyCodeDto request) {
        Optional<VerificationCode> optionalCode = verificationCodeRepository.findByEmail(request.getEmail());

        if (optionalCode.isEmpty()) {
            return ResponseEntity.badRequest().body(ApiResponse.onFailure("400", "인증코드가 발송되지 않은 이메일입니다.", null));
        }

        VerificationCode verificationCode = optionalCode.get();

        if (verificationCode.isExpired()) {
            return ResponseEntity.badRequest().body(ApiResponse.onFailure("400", "이메일 인증 시간인 3분을 초과했습니다.", null));
        }

        if (!verificationCode.getCode().equals(request.getCode())) {
            return ResponseEntity.badRequest().body(ApiResponse.onFailure("400", "인증번호가 일치하지 않습니다.", null));
        }

        return ResponseEntity.ok(ApiResponse.onSuccess("이메일 인증에 성공했습니다."));
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*\\.(com|net|org|co\\.kr|ac\\.kr|gov|edu)$";
        return email.matches(emailRegex);
    }
}
