package umc.TripPiece.converter;

import umc.TripPiece.domain.City;
import umc.TripPiece.domain.Travel;
import umc.TripPiece.domain.User;
import umc.TripPiece.web.dto.response.ExploreResponseDto;
import umc.TripPiece.web.dto.response.TravelResponseDto;

public class ExploreConverter {
    public static ExploreResponseDto.ExploreListDto toExploreListDto(Travel travel) {
        User user = travel.getUser();
        City city = travel.getCity();

        return ExploreResponseDto.ExploreListDto.builder()
                .id(travel.getId())
                .title(travel.getTitle())
                .thumbnail(travel.getThumbnail())
                .startDate(travel.getStartDate().toLocalDate())
                .endDate(travel.getEndDate().toLocalDate())
                .cityName(city.getName())
                .countryName(city.getCountry().getName())
                .status(travel.getStatus())
                .countryImage(city.getCountry().getCountryImage())
                .userId(user.getId())
                .profileImg(user.getProfileImg())
                .nickname(user.getNickname())
                .build();
    }

    public static ExploreResponseDto.PopularCitiesDto toPopularCitiesDto(City city){
        return ExploreResponseDto.PopularCitiesDto.builder()
                .id(city.getId())
                .city(city.getName())
                .country(city.getCountry().getName())
                .thumbnail(city.getCityImage())
                .count(city.getLogCount())
                .build();
    }
}
