package umc.TripPiece.converter;

import umc.TripPiece.domain.*;
import umc.TripPiece.web.dto.request.TravelRequestDto;
import umc.TripPiece.web.dto.response.TravelResponseDto;

import java.time.LocalDate;

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


}
