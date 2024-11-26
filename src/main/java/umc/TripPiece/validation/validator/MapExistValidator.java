package umc.TripPiece.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.TripPiece.apiPayload.code.status.ErrorStatus;
import umc.TripPiece.repository.MapRepository;
import umc.TripPiece.validation.annotation.ExistMap;

@Component
@RequiredArgsConstructor
public class MapExistValidator implements ConstraintValidator<ExistMap, Long> {

    private final MapRepository mapRepository;

    @Override
    public void initialize(ExistMap constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        boolean isValid = mapRepository.existsById(value);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.NOT_FOUND_MAP.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
