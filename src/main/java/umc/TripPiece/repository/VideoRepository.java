package umc.TripPiece.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.TripPiece.domain.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {
  List<Video> findByTripPieceId(Long id);
}
