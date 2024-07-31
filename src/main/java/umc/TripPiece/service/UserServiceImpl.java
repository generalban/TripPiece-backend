package umc.TripPiece.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import umc.TripPiece.converter.UserConverter;
import umc.TripPiece.domain.User;
import umc.TripPiece.repository.UserRepository;
import umc.TripPiece.web.dto.request.UserRequestDto;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User signUp(@Valid UserRequestDto.SignUpDto request) {


        // 이메일 중복 확인
        userRepository.findByEmail(request.getEmail()).ifPresent(user -> {
//            throw new BaseException("이미 사용 중인 이메일입니다.");
        });

        // 닉네임 중복 확인
        userRepository.findByNickname(request.getNickname()).ifPresent(user -> {
//            throw new BaseException("이미 사용 중인 닉네임입니다.");
        });

        // 비밀번호 암호화
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        User newUser = UserConverter.toUser(request, hashedPassword);
        return userRepository.save(newUser);
    }
}