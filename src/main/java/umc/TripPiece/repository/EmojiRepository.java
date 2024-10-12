package umc.TripPiece.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.TripPiece.domain.Emoji;

public interface EmojiRepository extends JpaRepository<Emoji, Long> {
  List<Emoji> findByTripPieceId(Long id);
}
