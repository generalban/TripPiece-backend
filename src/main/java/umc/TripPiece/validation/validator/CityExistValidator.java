package umc.TripPiece.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.TripPiece.apiPayload.code.status.ErrorStatus;
import umc.TripPiece.repository.CityRepository;
import umc.TripPiece.validation.annotation.ExistCity;

@Component
@RequiredArgsConstructor
public class CityExistValidator implements ConstraintValidator<ExistCity, Long> {

    private final CityRepository cityRepository;

    @Override
    public void initialize(ExistCity constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        boolean isValid = cityRepository.existsById(value);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.NOT_FOUND_CITY.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
