package umc.TripPiece.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class MapColorDto {
    @NotBlank
    @Getter
    private String color;
}
