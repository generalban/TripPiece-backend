package umc.TripPiece.web.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.TripPiece.domain.Emoji;
import umc.TripPiece.domain.Picture;
import umc.TripPiece.domain.Video;

public class TripPieceRequestDto {
  @Getter
  @NoArgsConstructor
  public static class MemoUpdateDto {
    String description;
  }

  @Getter
  @NoArgsConstructor
  public static class PictureUpdateDto {
    String description;
    List<Picture> pictures;
  }

  @Getter
  @NoArgsConstructor
  public static class VideoUpdateDto {
    String description;
    List<Video> videos;
  }

  @Getter
  @NoArgsConstructor
  public static class EmojiUpdateDto {
    String description;
    List<Emoji> emojis;
  }

}
