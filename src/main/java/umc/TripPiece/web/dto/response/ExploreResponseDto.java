package umc.TripPiece.web.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;
import umc.TripPiece.domain.enums.TravelStatus;

import java.time.LocalDate;

public class ExploreResponseDto {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExploreListDto {
        private Long id;
        private String title;
        private String thumbnail;
        @JsonFormat(pattern = "yyyy/MM/dd")
        private LocalDate startDate;
        @JsonFormat(pattern = "yyyy/MM/dd")
        private LocalDate endDate;
        private String cityName;
        private String countryName;
        private TravelStatus status;
        private String countryImage;
        private Long userId;
        private String profileImg;
        private String nickname;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PopularCitiesDto {
        private Long id;
        private String city;
        private String country;
        private String thumbnail;
        private Long count;
    }
}
