package umc.TripPiece.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import umc.TripPiece.service.TripPieceService;
import umc.TripPiece.web.dto.request.MapRequestDto;
import umc.TripPiece.web.dto.response.ApiResponse;
import umc.TripPiece.web.dto.response.MapResponseDto;
import umc.TripPiece.service.MapService;
import umc.TripPiece.web.dto.response.MapStatsResponseDto;
import umc.TripPiece.web.dto.response.TripPieceResponseDto;

import java.util.List;

@RestController
@RequestMapping("/api/maps")
public class MapController {

    @Autowired
    private MapService mapService;

    @GetMapping
    @Operation(summary = "맵 불러오기 API", description = "맵 리스트 반환")
    public ApiResponse<List<MapResponseDto>> getAllMaps() {
        List<MapResponseDto> maps = mapService.getAllMaps();
        return new ApiResponse<>(true, maps, "All maps retrieved successfully");
    }

    @PostMapping
    @Operation(summary = "맵 생성 API", description = "맵 색깔 지정")
    public ApiResponse<MapResponseDto> createMap(@RequestBody MapRequestDto requestDto) {
        MapResponseDto mapResponseDto = mapService.createMap(requestDto);
        return new ApiResponse<>(true, mapResponseDto, "Map created successfully");
    }

    @GetMapping("/stats")
    @Operation(summary = "방문 나라 수/도시 수 반환 API", description = "현재까지 방문한 나라/도시 갯수")
    public ApiResponse<MapStatsResponseDto> getMapStats(@RequestHeader("Authorization") String token) {
        String tokenWithoutBearer = token.substring(7);
        MapStatsResponseDto stats = mapService.getMapStats(tokenWithoutBearer);
        return new ApiResponse<>(true, stats, "Map stats retrieved successfully");
    }

    @GetMapping("/markers")
    @Operation(summary = "구글 지도 위 마커 반환 API", description = "나의 기록탭의 마커 반환")
    public ApiResponse<List<MapResponseDto.getMarkerResponse>> getMarkers(@RequestHeader("Authorization") String token) {
        String tokenWithoutBearer = token.substring(7);
        List<MapResponseDto.getMarkerResponse> markers = mapService.getMarkers(tokenWithoutBearer);

        if(markers == null || markers.isEmpty()) return new ApiResponse<>(false, null, "No markers found");
        return new ApiResponse<>(true, markers, "Markers retrieved successfully");

    }

}