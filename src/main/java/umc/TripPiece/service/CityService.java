package umc.TripPiece.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.TripPiece.domain.City;
import umc.TripPiece.domain.Country;
import umc.TripPiece.repository.CityRepository;
import umc.TripPiece.repository.CountryRepository;
import umc.TripPiece.web.dto.request.CityRequestDto;
import umc.TripPiece.web.dto.response.CityResponseDto;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    public List<CityResponseDto.searchDto> searchCity(CityRequestDto.searchDto request){
        String keyword = request.getKeyword();
        List<City> cities = cityRepository.findByNameContainingIgnoreCase(keyword);
        List<Country> countries = countryRepository.findByNameContainingIgnoreCase(keyword);

        List<CityResponseDto.searchDto> searched = new ArrayList<>();

        searched.addAll(cities.stream().map(city -> new CityResponseDto.searchDto(
                city.getName(), city.getCountry().getName(), city.getComment(), city.getCityImage(), city.getLogCount()
        )).toList());

        countries.forEach(country -> {
            List<City> citiesInCountry = cityRepository.findByCountryId(country.getId());

            searched.addAll(citiesInCountry.stream().map(city -> new CityResponseDto.searchDto(
                            city.getName(), city.getCountry().getName(), city.getComment(), city.getCityImage(), city.getLogCount()
                    )).toList()
            );
        });

        return searched;
    }
}