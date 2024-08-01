package umc.TripPiece.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import umc.TripPiece.converter.TravelConverter;
import umc.TripPiece.domain.Picture;
import umc.TripPiece.domain.Travel;
import umc.TripPiece.domain.TripPiece;
import umc.TripPiece.payload.ApiResponse;
import umc.TripPiece.service.TravelService;
import umc.TripPiece.web.dto.request.TravelRequestDto;
import umc.TripPiece.web.dto.response.TravelResponseDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TravelController {

    private final TravelService travelService;

    @GetMapping("/explore/search")
    public List<Travel> searchByKeyword(@RequestParam String keyword) {
        return travelService.searchByKeyword(keyword);
    }

    @PostMapping("/mytravels")
    @Operation(summary = "여행 생성 API", description = "여행 시작하기")
    public ApiResponse<TravelResponseDto.Create> createTravel(@RequestBody TravelRequestDto.Create request){
        TravelResponseDto.Create response = travelService.createTravel(request);
        return ApiResponse.onSuccess(response);
    }

    @PostMapping("/mytravels/{travelId}/end")
    @Operation(summary = "여행 종료 API", description = "여행을 종료하고 요약 정보 반환")
    public ApiResponse<TravelResponseDto.TravelSummaryDto> endTravel(@PathVariable("travelId") Long travelId) {
        TravelResponseDto.TravelSummaryDto response = travelService.endTravel(travelId);
        return ApiResponse.onSuccess(response);
    }

    @PostMapping("/mytravels/{travelId}/memo")
    @Operation(summary = "메모 기록 API", description = "특정 여행기에서의 여행조각 추가")
    public ApiResponse<TravelResponseDto.CreateTripPieceResultDto> createTripPieceMemo(@RequestBody TravelRequestDto.MemoDto request, @PathVariable("travelId") Long travelId){
        TripPiece tripPiece = travelService.createMemo(travelId, request);
        return ApiResponse.onSuccess(TravelConverter.toCreateTripPieceResultDto(tripPiece));
    }

    @PostMapping("/mytravels/{travelId}/emoji")
    @Operation(summary = "이모지 기록 API", description = "특정 여행기에서의 여행조각 추가")
    public ApiResponse<TravelResponseDto.CreateTripPieceResultDto> createTripPieceEmoji(@RequestBody TravelRequestDto.MemoDto request, @PathVariable("travelId") Long travelId, @RequestParam(name = "emoji") String emoji){
        TripPiece tripPiece = travelService.createEmoji(travelId, emoji, request);
        return ApiResponse.onSuccess(TravelConverter.toCreateTripPieceResultDto(tripPiece));
    }

    @PostMapping(value = "/mytravels/{travelId}/picture", consumes = "multipart/form-data")
    @Operation(summary = "사진 기록 API", description = "특정 여행기에서의 여행조각 추가")
    public ApiResponse<TravelResponseDto.CreateTripPieceResultDto> createTripPiecePicture(@RequestPart TravelRequestDto.MemoDto request, @PathVariable("travelId") Long travelId, @RequestPart MultipartFile photo){
        TripPiece tripPiece = travelService.createPicture(travelId, photo, request);
        return ApiResponse.onSuccess(TravelConverter.toCreateTripPieceResultDto(tripPiece));
    }

    @PostMapping(value = "/mytravels/{travelId}/selfie", consumes = "multipart/form-data")
    @Operation(summary = "셀카 기록 API", description = "특정 여행기에서의 여행조각 추가")
    public ApiResponse<TravelResponseDto.CreateTripPieceResultDto> createTripPieceSelfie(@RequestPart TravelRequestDto.MemoDto request, @PathVariable("travelId") Long travelId, @RequestPart MultipartFile photo){
        TripPiece tripPiece = travelService.createSelfie(travelId, photo, request);
        return ApiResponse.onSuccess(TravelConverter.toCreateTripPieceResultDto(tripPiece));
    }

    @PostMapping(value = "/mytravels/{travelId}/video", consumes = "multipart/form-data")
    @Operation(summary = "비디오 기록 API", description = "특정 여행기에서의 여행조각 추가")
    public ApiResponse<TravelResponseDto.CreateTripPieceResultDto> createTripPieceVideo(@RequestPart TravelRequestDto.MemoDto request, @PathVariable("travelId") Long travelId, @RequestPart MultipartFile video){
        TripPiece tripPiece = travelService.createVideo(travelId, video, request);
        return ApiResponse.onSuccess(TravelConverter.toCreateTripPieceResultDto(tripPiece));
    }

    @PostMapping(value = "/mytravels/{travelId}/where", consumes = "multipart/form-data")
    @Operation(summary = "'지금 어디에 있나요?' 카테고리 기록 API", description = "특정 여행기에서의 여행조각 추가")
    public ApiResponse<TravelResponseDto.CreateTripPieceResultDto> createTripPieceWhere(@RequestPart TravelRequestDto.MemoDto request, @PathVariable("travelId") Long travelId, @RequestPart MultipartFile video){
        TripPiece tripPiece = travelService.createWhere(travelId, video, request);
        return ApiResponse.onSuccess(TravelConverter.toCreateTripPieceResultDto(tripPiece));
    }


}
