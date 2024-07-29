package umc.TripPiece.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.TripPiece.domain.Travel;
import umc.TripPiece.service.TravelService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TravelController {

    private final TravelService travelService;

    @GetMapping("/explore/search")
    public List<Travel> searchByKeyword(@RequestParam String keyword) {
        return travelService.searchByKeyword(keyword);
    }
}
