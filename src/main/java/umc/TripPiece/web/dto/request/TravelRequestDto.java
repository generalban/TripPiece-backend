package umc.TripPiece.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class TravelRequestDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        private String cityName;
        private String countryName;
        private String title;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
    }

    @Getter
    public static class MemoDto {

        @NotBlank
        String description;
    }

}
