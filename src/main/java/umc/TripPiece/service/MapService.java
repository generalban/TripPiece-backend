package umc.TripPiece.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.TripPiece.converter.MapConverter;
import umc.TripPiece.domain.City;
import umc.TripPiece.domain.Country;
import umc.TripPiece.domain.Map;
import umc.TripPiece.domain.Travel;
import umc.TripPiece.domain.jwt.JWTUtil;
import umc.TripPiece.repository.MapRepository;
import umc.TripPiece.repository.CityRepository;
import umc.TripPiece.repository.TravelRepository;
import umc.TripPiece.web.dto.request.MapRequestDto;
import umc.TripPiece.web.dto.response.MapResponseDto;
import umc.TripPiece.web.dto.response.MapStatsResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MapService {

    private final MapRepository mapRepository;
    private final CityRepository cityRepository;
    private final JWTUtil jwtUtil;
    private final TravelRepository travelRepository;

    public List<MapResponseDto> getAllMaps() {
        return mapRepository.findAll().stream()
                .map(MapConverter::toMapResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public MapResponseDto createMap(MapRequestDto requestDto) {
        Map map = MapConverter.toMap(requestDto);
        Map savedMap = mapRepository.save(map);
        return MapConverter.toMapResponseDto(savedMap);
    }

    public long countCountriesByUserId(Long userId) {
        return mapRepository.countDistinctCountryCodeByUserId(userId);
    }

    public long countCitiesByUserId(Long userId) {
        return cityRepository.countDistinctCityByUserId(userId);
    }

    public MapStatsResponseDto getMapStats(String token) {
        Long userId = jwtUtil.getUserIdFromToken(token);

        long countryCount = countCountriesByUserId(userId);
        long cityCount = countCitiesByUserId(userId);
        return new MapStatsResponseDto(countryCount, cityCount);
    }

    @Transactional
    public List<MapResponseDto.getMarkerResponse> getMarkers(String token) {
        Long userId = jwtUtil.getUserIdFromToken(token);
        List<Travel> travels = travelRepository.findByUserId(userId);
        List<MapResponseDto.getMarkerResponse> markers = new ArrayList<>();

        for(Travel travel : travels) {
            City city = travel.getCity();
            Country country = city.getCountry();
            Map map = mapRepository.findByCountryCodeAndUserId(country.getCode(), userId);
            MapResponseDto.getMarkerResponse marker = MapConverter.toMarkerResponseDto(map, travel.getThumbnail(), country.getName(), city.getName());
            markers.add(marker);
        }

        return markers;

    }


}