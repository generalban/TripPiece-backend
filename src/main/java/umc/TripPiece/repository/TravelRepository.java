package umc.TripPiece.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.TripPiece.domain.Travel;

import java.math.BigInteger;
import java.util.List;

public interface TravelRepository extends JpaRepository<Travel, BigInteger> {
    List<Travel> findByCityId(BigInteger cityId);
    List<Travel> findByCity_CountryId(BigInteger countryId);

}
