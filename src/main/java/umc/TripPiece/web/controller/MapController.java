package umc.TripPiece.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import umc.TripPiece.service.CityService;
import umc.TripPiece.service.MapService;
import umc.TripPiece.web.dto.request.CityRequestDto;
import umc.TripPiece.web.dto.request.MapRequestDto;

import umc.TripPiece.web.dto.response.CityResponseDto;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import umc.TripPiece.apiPayload.ApiResponse;
import umc.TripPiece.converter.MapConverter;
import umc.TripPiece.service.MapService;
import umc.TripPiece.validation.annotation.ExistMap;
import umc.TripPiece.validation.annotation.ExistUser;
import umc.TripPiece.web.dto.request.MapRequestDto;

import umc.TripPiece.web.dto.response.MapResponseDto;
import umc.TripPiece.web.dto.response.MapStatsResponseDto;
import umc.TripPiece.web.dto.request.MapColorDto;
import umc.TripPiece.web.dto.request.MapColorsDto;

import java.util.List;

@Tag(name = "Map", description = "지도 관련 API")
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/maps")
public class MapController {

    private final MapService mapService;
    private final CityService cityService;

    @GetMapping("/{userId}")
    @Operation(summary = "유저별 맵 불러오기 API", description = "유저별 맵 리스트 반환")
    public ApiResponse<List<MapResponseDto>> getMapsByUserId(@PathVariable(name = "userId") @ExistUser Long userId) {
        List<MapResponseDto> maps = mapService.getMapsByUserId(userId);
        return ApiResponse.onSuccess(maps);
    }

    @PostMapping
    @Operation(summary = "맵 생성 API", description = "맵과 도시 정보 생성")
    public ApiResponse<MapResponseDto> createMap(@RequestBody @Valid MapRequestDto requestDto) {
        MapResponseDto mapResponseDto = mapService.createMapWithCity(requestDto);
        return ApiResponse.onSuccess(mapResponseDto);
    }

    @GetMapping("/stats/{userId}")
    @Operation(summary = "유저별 맵 통계 API", description = "유저별 방문한 나라와 도시 수 반환")
    public ApiResponse<MapStatsResponseDto> getMapStatsByUserId(@PathVariable(name = "userId") @ExistUser Long userId) {
        MapStatsResponseDto stats = mapService.getMapStatsByUserId(userId);
        return ApiResponse.onSuccess(stats);
    }

    @GetMapping("/markers")
    @Operation(summary = "구글 지도 위 마커 반환 API", description = "나의 기록탭의 마커 반환")
    public ApiResponse<List<MapResponseDto.getMarkerResponse>> getMarkers(@RequestHeader("Authorization") String token) {
        String tokenWithoutBearer = token.substring(7);
        List<MapResponseDto.getMarkerResponse> markers = mapService.getMarkers(tokenWithoutBearer);

        return ApiResponse.onSuccess(markers);
    }

    // 맵 색상 수정 엔드포인트
    @PutMapping("/color/{mapId}")
    @Operation(summary = "맵 색상 수정 API", description = "맵의 색상을 수정")
    public ApiResponse<MapResponseDto> updateMapColor(@PathVariable(name = "mapId") @ExistMap Long mapId, @RequestBody @Valid MapColorDto colorDto) {
        MapResponseDto updatedMap = mapService.updateMapColor(mapId, colorDto.getColor());
        return ApiResponse.onSuccess(updatedMap);
    }

    // 맵 색상 삭제 엔드포인트
    @DeleteMapping("/color/delete/{mapId}")
    @Operation(summary = "맵 색상 삭제 API", description = "맵의 색상을 삭제")
    public ApiResponse<Void> deleteMapColor(@PathVariable(name = "mapId") @ExistMap Long mapId) {
        mapService.deleteMapColor(mapId);
        return ApiResponse.onSuccess(null);
    }

    // 여러 색상 선택 엔드포인트
    @PutMapping("/colors/{mapId}")
    @Operation(summary = "맵 여러 색상 선택 API", description = "맵의 색상을 여러 개 선택")
    public ApiResponse<MapResponseDto> updateMultipleMapColors(@PathVariable(name = "mapId") @ExistMap Long mapId, @RequestBody MapColorsDto colorsDto) {
        MapResponseDto updatedMap = mapService.updateMultipleMapColors(mapId, colorsDto.getColors());
        return ApiResponse.onSuccess(updatedMap);
    }

    @GetMapping("/search")
    @Operation(summary = "도시, 국가 검색 API", description = "도시, 국가 검색")
    public ApiResponse<List<CityResponseDto.searchDto>> searchCities(@RequestParam String keyword){
        List<CityResponseDto.searchDto> result = cityService.searchCity(keyword);

        if (result.isEmpty()) {
            return ApiResponse.onFailure("404", "No matching cities or countries found.", null);
        }
        else {
            return ApiResponse.onSuccess(result);
        }
    }
}
