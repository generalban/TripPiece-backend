package umc.TripPiece.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.TripPiece.domain.City;
import umc.TripPiece.domain.Country;
import umc.TripPiece.dto.City.CitySearchDto;
import umc.TripPiece.repository.CityRepository;
import umc.TripPiece.repository.CountryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    public List<CitySearchDto.Response> searchCity(CitySearchDto.Request request){
        String keyword = request.getKeyword();
        List<City> cities = cityRepository.findByNameContainingIgnoreCase(keyword);
        List<Country> countries = countryRepository.findByNameContainingIgnoreCase(keyword);

        List<CitySearchDto.Response> searched = new ArrayList<>();

        searched.addAll(cities.stream().map(city -> new CitySearchDto.Response(
                city.getName(), city.getCountry().getName(), city.getComment(), city.getCityImage()
        )).toList());

        countries.forEach(country -> {
            List<City> citiesInCountry = cityRepository.findByCountryId(country.getId());

            searched.addAll(citiesInCountry.stream().map(city -> new CitySearchDto.Response(
                            city.getName(), city.getCountry().getName(), city.getComment(), city.getCityImage()
                    )).toList()
            );
        });

        return searched;
    }
}
