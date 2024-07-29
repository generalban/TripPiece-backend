package umc.TripPiece.dto.City;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CitySearchDto {
    @Getter
    @AllArgsConstructor
    public static class Response{
        private String cityName;
        private String countryName;
        private String cityDescription;
        private String cityImage;
    }

    @Getter
    @NoArgsConstructor
    public static class Request{
        private String keyword;
    }
}
