package umc.TripPiece.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.TripPiece.dto.City.CitySearchDto;
import umc.TripPiece.service.CityService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @PostMapping("search/cities")
    public List<CitySearchDto.Response> searchCities(@RequestParam CitySearchDto.Request request){
        return cityService.searchCity(request);
    }
}
