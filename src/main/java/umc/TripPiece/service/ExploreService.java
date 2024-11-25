package umc.TripPiece.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.TripPiece.converter.TravelConverter;
import umc.TripPiece.domain.City;
import umc.TripPiece.domain.Country;
import umc.TripPiece.domain.Travel;
import umc.TripPiece.repository.CityRepository;
import umc.TripPiece.repository.CountryRepository;
import umc.TripPiece.repository.TravelRepository;
import umc.TripPiece.web.dto.response.TravelResponseDto;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExploreService {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final TravelRepository travelRepository;

    @Transactional
    public List<TravelResponseDto.TravelListDto> searchTravels(String query){
    List<City> cities = cityRepository.findByNameContainingIgnoreCase(query);
    List<Country> countries = countryRepository.findByNameContainingIgnoreCase(query);

    List<Travel> travels = new ArrayList<>();

    if(!cities.isEmpty()){
        List<Long> cityIds = cities.stream().map(City::getId).toList();
        travels.addAll(travelRepository.findByCityIdIn(cityIds));
    }

    if(!countries.isEmpty()){
        countries.forEach(country -> {
            List<City> citiesInCountry = cityRepository.findByCountryId(country.getId());
            List<Long> cityIdsInCountry = citiesInCountry.stream().map(City::getId).toList();
            travels.addAll(travelRepository.findByCityIdIn(cityIdsInCountry));
        });
    }

    return travels.stream().map(TravelConverter::toTravelListDto).toList();
    }
}
