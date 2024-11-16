package umc.TripPiece.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;

    public String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // 6자리 숫자 생성
        return String.valueOf(code);
    }

    public void sendVerificationCode(String toEmail, String code) throws MessagingException {
        String subject = "이메일 인증 코드";
        String content = "<p>아래의 인증 코드를 입력하세요:</p>" +
                "<h3>" + code + "</h3>";
        sendEmail(toEmail, subject, content);
    }

    public void sendEmail(String toEmail, String title, String content) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(toEmail);
        helper.setSubject(title);
        helper.setText(content, true); // HTML 사용 가능
        try {
            emailSender.send(message);
        } catch (RuntimeException e) {
            log.error("Failed to send email", e);
            throw new RuntimeException("Unable to send email", e);
        }
    }
}