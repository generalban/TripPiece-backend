package umc.TripPiece.converter;

import umc.TripPiece.domain.*;
import umc.TripPiece.web.dto.response.TripPieceResponseDto;

public class TripPieceConverter {

    public static Emoji toTripPieceEmoji(String emoji, TripPiece tripPiece) {
        return Emoji.builder()
                .emoji(emoji)
                .tripPiece(tripPiece)
                .build();
    }

    public static Picture toTripPiecePicture(String pictureUrl, TripPiece tripPiece) {
        return Picture.builder()
                .pictureUrl(pictureUrl)
                .tripPiece(tripPiece)
                .build();
    }

    public static Video toTripPieceVideo(String videoUrl, TripPiece tripPiece) {
        return Video.builder()
                .videoUrl(videoUrl)
                .tripPiece(tripPiece)
                .build();
    }

    public static TripPieceResponseDto.TripPieceListDto toTripPieceList(TripPiece tripPiece) {
        return TripPieceResponseDto.TripPieceListDto.builder()
                .createdAt(tripPiece.getCreatedAt())
                .category(tripPiece.getCategory())
                .build();

    }
}
