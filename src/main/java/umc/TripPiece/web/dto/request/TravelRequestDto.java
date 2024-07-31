package umc.TripPiece.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class TravelRequestDto {

    @Getter
    public static class MemoDto {

        @NotBlank
        String description;
    }

}
