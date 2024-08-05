package umc.TripPiece.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import umc.TripPiece.converter.TravelConverter;
import umc.TripPiece.domain.Travel;
import umc.TripPiece.domain.TripPiece;
import umc.TripPiece.payload.ApiResponse;
import umc.TripPiece.service.TravelService;
import umc.TripPiece.validation.annotation.CheckEmoji;
import umc.TripPiece.validation.annotation.CheckEmojiNum;
import umc.TripPiece.validation.annotation.TextLength100;
import umc.TripPiece.validation.annotation.TextLength30;
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

    @PostMapping(value = "/mytravels", consumes = "multipart/form-data")
    @Operation(summary = "여행 생성 API", description = "여행 시작하기")
    public ApiResponse<TravelResponseDto.Create> createTravel(@Valid @RequestPart("data") TravelRequestDto.Create request, @RequestPart("thumbnail") MultipartFile thumbnail){
        TravelResponseDto.Create response = travelService.createTravel(request, thumbnail);
        return ApiResponse.onSuccess(response);
    }

    @PostMapping("/mytravels/{travelId}/end")
    @Operation(summary = "여행 종료 API", description = "여행을 종료하고 요약 정보 반환")
    public ApiResponse<TravelResponseDto.TripSummaryDto> endTravel(@PathVariable("travelId") Long travelId) {
        TravelResponseDto.TripSummaryDto response = travelService.endTravel(travelId);
        return ApiResponse.onSuccess(response);
    }
    @GetMapping("/mytravels/{travelId}/continue")
    @Operation(summary = "여행 이어보기 API", description = "여행을 이어보 날짜별 조각 반환")
    public ApiResponse<List<TravelResponseDto.TripPieceSummaryDto>> continueTravel(@PathVariable("travelId") Long travelId) {
        List<TravelResponseDto.TripPieceSummaryDto> response = travelService.continueTravel(travelId);
        return ApiResponse.onSuccess(response);
    }

    @PostMapping("/mytravels/{travelId}/memo")
    @Operation(summary = "메모 기록 API", description = "특정 여행기에서의 여행조각 추가")
    public ApiResponse<TravelResponseDto.CreateTripPieceResultDto> createTripPieceMemo(@RequestBody @TextLength100 TravelRequestDto.MemoDto request, @PathVariable("travelId") Long travelId){
        TripPiece tripPiece = travelService.createMemo(travelId, request);
        return ApiResponse.onSuccess(TravelConverter.toCreateTripPieceResultDto(tripPiece));
    }

    @PostMapping("/mytravels/{travelId}/emoji")
    @Operation(summary = "이모지 기록 API", description = "특정 여행기에서의 여행조각 추가")
    public ApiResponse<TravelResponseDto.CreateTripPieceResultDto> createTripPieceEmoji(@RequestBody @TextLength30 TravelRequestDto.MemoDto request, @PathVariable("travelId") Long travelId, @RequestParam(name = "emojis") @CheckEmoji @CheckEmojiNum List<String> emojis){
        TripPiece tripPiece = travelService.createEmoji(travelId, emojis, request);
        return ApiResponse.onSuccess(TravelConverter.toCreateTripPieceResultDto(tripPiece));
    }

    @PostMapping(value = "/mytravels/{travelId}/picture", consumes = "multipart/form-data")
    @Operation(summary = "사진 기록 API", description = "특정 여행기에서의 여행조각 추가")
    public ApiResponse<TravelResponseDto.CreateTripPieceResultDto> createTripPiecePicture(@RequestPart("memo") @TextLength30 TravelRequestDto.MemoDto request, @PathVariable("travelId") Long travelId, @RequestPart("photos") List<MultipartFile> photos){
        TripPiece tripPiece = travelService.createPicture(travelId, photos, request);
        return ApiResponse.onSuccess(TravelConverter.toCreateTripPieceResultDto(tripPiece));
    }

    @PostMapping(value = "/mytravels/{travelId}/selfie", consumes = "multipart/form-data")
    @Operation(summary = "셀카 기록 API", description = "특정 여행기에서의 여행조각 추가")
    public ApiResponse<TravelResponseDto.CreateTripPieceResultDto> createTripPieceSelfie(@RequestPart("memo") @TextLength30 TravelRequestDto.MemoDto request, @PathVariable("travelId") Long travelId, @RequestPart("photo") MultipartFile photo){
        TripPiece tripPiece = travelService.createSelfie(travelId, photo, request);
        return ApiResponse.onSuccess(TravelConverter.toCreateTripPieceResultDto(tripPiece));
    }

    @PostMapping(value = "/mytravels/{travelId}/video", consumes = "multipart/form-data")
    @Operation(summary = "비디오 기록 API", description = "특정 여행기에서의 여행조각 추가")
    public ApiResponse<TravelResponseDto.CreateTripPieceResultDto> createTripPieceVideo(@RequestPart("memo") @TextLength30 TravelRequestDto.MemoDto request, @PathVariable("travelId") Long travelId, @RequestPart("video") MultipartFile video){
        TripPiece tripPiece = travelService.createVideo(travelId, video, request);
        return ApiResponse.onSuccess(TravelConverter.toCreateTripPieceResultDto(tripPiece));
    }

    @PostMapping(value = "/mytravels/{travelId}/where", consumes = "multipart/form-data")
    @Operation(summary = "'지금 어디에 있나요?' 카테고리 기록 API", description = "특정 여행기에서의 여행조각 추가")
    public ApiResponse<TravelResponseDto.CreateTripPieceResultDto> createTripPieceWhere(@RequestPart("memo") @TextLength30 TravelRequestDto.MemoDto request, @PathVariable("travelId") Long travelId, @RequestPart("video") MultipartFile video){
        TripPiece tripPiece = travelService.createWhere(travelId, video, request);
        return ApiResponse.onSuccess(TravelConverter.toCreateTripPieceResultDto(tripPiece));
    }

    @GetMapping("/travels")
    @Operation(summary = "생성된 여행기 API", description = "생성된 여행기 리스트 반환")
    public ApiResponse<List<TravelResponseDto.TravelListDto>> getTravelList(){
        List<TravelResponseDto.TravelListDto> travels = travelService.getTravelList();
        return ApiResponse.onSuccess(travels);
    }

    @GetMapping("/mytravels")
    @Operation(summary = "현재 진행중인 여행기 반환 API", description = "현재 진행중인 여행기 반환")
    public ApiResponse<TravelResponseDto.getOngoingTravelResultDto> getOngoingTravel(){
        TravelResponseDto.getOngoingTravelResultDto travel = travelService.getOngoingTravel();
        return ApiResponse.onSuccess(travel);
    }


}
