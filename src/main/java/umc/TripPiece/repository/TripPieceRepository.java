package umc.TripPiece.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.TripPiece.domain.TripPiece;

import java.util.List;

public interface TripPieceRepository extends JpaRepository<TripPiece, Long> {
    List<TripPiece> findByTravelId(Long travelId);
    List<TripPiece> findByUserIdOrderByCreatedAtDesc(Long userId);
    List<TripPiece> findByUserIdOrderByCreatedAtAsc(Long userId);
}
