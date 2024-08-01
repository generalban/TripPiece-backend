package umc.TripPiece.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
//import umc.TripPiece.domain.enums.Category;

import java.time.LocalDate;

public class TravelResponseDto {

    @Getter
    @AllArgsConstructor
    public static class Create {
        private Long travelId;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateTripPieceResultDto {
        Long tripPieceId;
        LocalDate createdAt;
    }


}
