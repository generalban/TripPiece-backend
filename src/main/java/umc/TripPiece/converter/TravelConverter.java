package umc.TripPiece.converter;

import umc.TripPiece.domain.*;
import umc.TripPiece.domain.enums.Category;
import umc.TripPiece.web.dto.request.TravelRequestDto;
import umc.TripPiece.web.dto.response.TravelResponseDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TravelConverter {

    public static TripPiece toTripPieceMemo(TravelRequestDto.MemoDto request) {
        return TripPiece.builder()
                .description(request.getDescription())
                .build();
    }

    public static TravelResponseDto.CreateTripPieceResultDto toCreateTripPieceResultDto(TripPiece tripPiece) {
        return TravelResponseDto.CreateTripPieceResultDto.builder()
                .tripPieceId(tripPiece.getId())
                .createdAt(LocalDate.now())
                .build();
    }

    public static Travel toTravel(TravelRequestDto.Create request, City city){
        return Travel.builder().city(city).title(request.getTitle()).startDate(request.getStartDate()).endDate(request.getEndDate()).build();
    }
    public static TravelResponseDto.Create toCreateResponseDto(Travel travel){
        return new TravelResponseDto.Create(travel.getId());
    }
    public static TravelResponseDto.TripSummaryDto toTripSummary(Travel travel, List<TripPiece> tripPieces) {
        TravelResponseDto.TripSummaryDto.TripSummaryDtoBuilder summaryBuilder = TravelResponseDto.TripSummaryDto.builder()
                .title(travel.getTitle())
                .city(travel.getCity().getName())
                .country(travel.getCity().getCountry().getName())
                .startDate(travel.getStartDate())
                .endDate(travel.getEndDate())
                .totalPieces(tripPieces.size())
                .memoCount((int) tripPieces.stream().filter(tp -> tp.getCategory().equals(Category.MEMO) || tp.getCategory().equals(Category.EMOJI)).count())
                .pictureCount((int) tripPieces.stream().filter(tp -> tp.getCategory().equals(Category.PICTURE) || tp.getCategory().equals(Category.SELFIE)).count())
                .videoCount((int) tripPieces.stream().filter(tp -> tp.getCategory().equals(Category.VIDEO) || tp.getCategory().equals(Category.WHERE)).count());

        // 최대 9개의 사진 조각을 추가합니다.
        List<TravelResponseDto.TripPieceSummaryDto> pictureSummaries = tripPieces.stream()
                .filter(tp -> tp.getCategory().equals(Category.PICTURE) || tp.getCategory().equals(Category.SELFIE))
                .flatMap(tp -> tp.getPictures().stream())
                .limit(9)
                .map(pic -> TravelResponseDto.TripPieceSummaryDto.builder()
                        .id(pic.getTripPiece().getId())
                        .description(pic.getTripPiece().getDescription())
                        .category(pic.getTripPiece().getCategory())
                        .mediaUrl(pic.getPictureUrl())
                        .createdAt(pic.getTripPiece().getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        summaryBuilder.pictureSummaries(pictureSummaries);

        return summaryBuilder.build();
    }


}
