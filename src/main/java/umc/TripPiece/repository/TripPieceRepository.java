package umc.TripPiece.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.TripPiece.domain.TripPiece;

public interface TripPieceRepository extends JpaRepository<TripPiece, Long> {
}
