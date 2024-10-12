package umc.TripPiece.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.TripPiece.domain.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long> {
  List<Picture> findByTripPieceId(Long id);
}
