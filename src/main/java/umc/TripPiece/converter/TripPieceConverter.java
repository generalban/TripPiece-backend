package umc.TripPiece.converter;

import umc.TripPiece.domain.Emoji;
import umc.TripPiece.domain.Picture;
import umc.TripPiece.domain.TripPiece;
import umc.TripPiece.domain.Video;
import umc.TripPiece.web.dto.request.TravelRequestDto;

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
}
