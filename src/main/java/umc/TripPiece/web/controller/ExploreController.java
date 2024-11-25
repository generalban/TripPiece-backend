package umc.TripPiece.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.TripPiece.payload.ApiResponse;
import umc.TripPiece.service.ExploreService;
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
    public ResponseEntity<ApiResponse<List<TravelResponseDto.TravelListDto>>> getSearchedTravelList(@RequestParam String query) {
     List<TravelResponseDto.TravelListDto> travels = exploreService.searchTravels(query);

     if(travels.isEmpty()){
         return new ResponseEntity<>(ApiResponse.onFailure("400", "생성된 여행기 없음.", null), HttpStatus.BAD_REQUEST);
     }
        return new ResponseEntity<>(ApiResponse.onSuccess(travels), HttpStatus.OK);
    }
}
