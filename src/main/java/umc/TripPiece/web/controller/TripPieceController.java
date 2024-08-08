package umc.TripPiece.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.TripPiece.payload.ApiResponse;
import umc.TripPiece.service.TripPieceService;
import umc.TripPiece.web.dto.response.TripPieceResponseDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TripPieceController {

    private final TripPieceService tripPieceService;

    @GetMapping("/mytrippieces/all/latest")
    @Operation(summary = "지난 여행 조각(전체, 최신순) API", description = "유저의 여행조각을 최신순으로 반환")
    public ApiResponse<List<TripPieceResponseDto.TripPieceListDto>> getTripPieceListLatest(@RequestParam("userId") Long userId){
        List<TripPieceResponseDto.TripPieceListDto> tripPieceList = tripPieceService.getTripPieceListLatest(userId);
        return ApiResponse.onSuccess(tripPieceList);
    }

    @GetMapping("/mytrippieces/all/earliest")
    @Operation(summary = "지난 여행 조각(전체, 오래된순) API", description = "유저의 여행조각을 오래된순으로 반환")
    public ApiResponse<List<TripPieceResponseDto.TripPieceListDto>> getTripPieceListEarliest(@RequestParam("userId") Long userId){
        List<TripPieceResponseDto.TripPieceListDto> tripPieceList = tripPieceService.getTripPieceListEarliest(userId);
        return ApiResponse.onSuccess(tripPieceList);
    }
}
