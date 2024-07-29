package umc.TripPiece.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.TripPiece.domain.City;

import java.math.BigInteger;
import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findByNameContainingIgnoreCase(String name);
    List<City> findByCountryId(Long countryId);
}
