package umc.TripPiece.web.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class TripPieceRequestDto {
  @Getter
  @NoArgsConstructor
  public static class update {
    String description;
  }

}
