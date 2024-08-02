package umc.TripPiece.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.TripPiece.domain.enums.Category;
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
    public static class TripSummaryDto {
        private String title;
        private String city;
        private String country;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private int totalPieces;
        private int memoCount;
        private int pictureCount;
        private int videoCount;
        private List<TripPieceSummaryDto> pictureSummaries;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TripPieceSummaryDto {
        private Long id;
        private String description;
        private Category category;
        private String mediaUrl;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DailySummaryDto {
        //private LocalDateTime date;
        private List<TripPieceSummaryDto> tripPieces;
    }



}
