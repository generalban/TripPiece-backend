package umc.TripPiece.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
//import umc.TripPiece.domain.enums.Category;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TravelSummaryDto {
        private String title;
        private String city;
        private String country;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private int totalPieces;
        private int memoCount;
        private int pictureCount;
        private int videoCount;
        private List<TripPictureSummaryDto> pictureSummaries;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TripPictureSummaryDto {
        private Long id;
        private String description;
        private String pictureUrl;
        private LocalDateTime createdAt;
    }



}
