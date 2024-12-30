package umc.TripPiece.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.TripPiece.apiPayload.ApiResponse;
import umc.TripPiece.service.ExploreService;
import umc.TripPiece.web.dto.response.ExploreResponseDto;
import umc.TripPiece.web.dto.response.TravelResponseDto;

import java.util.List;

@Tag(name = "Explore", description = "탐색 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/explore")
public class ExploreController {

    private final ExploreService exploreService;
    @GetMapping("/search")
    @Operation(summary = "도시, 국가 검색 API", description = "도시, 국가 검색")
    public ApiResponse<List<ExploreResponseDto.ExploreListDto>> getSearchedTravelList(@RequestParam String query, @RequestParam(defaultValue = "latest") String sort) {
     List<ExploreResponseDto.ExploreListDto> travels = exploreService.searchTravels(query, sort);
     if(travels.isEmpty()){
         return ApiResponse.onFailure("400", "생성된 여행기 없음.", null);
     }
        return ApiResponse.onSuccess(travels);
    }

    @GetMapping("/popular")
    @Operation(summary = "요즘 떠오르는 도시 API", description = "도시별 여행기순 내림차순")
    public ApiResponse<List<ExploreResponseDto.PopularCitiesDto>> getPopularCities(){
        List<ExploreResponseDto.PopularCitiesDto> cities = exploreService.getCitiesByTravelCount();
        if(cities.isEmpty()){
            return ApiResponse.onFailure("400", "생성된 여행기 없음.", null);
        }
        return ApiResponse.onSuccess(cities);
    }
}
