package umc.TripPiece.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import umc.TripPiece.apiPayload.ApiResponse;
import umc.TripPiece.service.CityService;
import umc.TripPiece.web.dto.request.CityRequestDto;
import umc.TripPiece.web.dto.response.CityResponseDto;

import java.util.List;

@Tag(name = "City", description = "도시 관련 API")
@RestController
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @PostMapping("search/cities")
    @Operation(
            summary = "도시, 국가 검색 API",
            description = "도시, 국가 검색"

    )
    public ApiResponse<List<CityResponseDto.searchDto>> searchCities(@RequestBody @Valid CityRequestDto.searchDto request){
        List<CityResponseDto.searchDto> result = cityService.searchCity(request);

        return ApiResponse.onSuccess(result);
    }
}