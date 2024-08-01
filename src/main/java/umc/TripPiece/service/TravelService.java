package umc.TripPiece.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import umc.TripPiece.converter.TravelConverter;
import umc.TripPiece.converter.TripPieceConverter;
import umc.TripPiece.domain.*;
//import umc.TripPiece.domain.enums.Category;
import umc.TripPiece.repository.*;
import umc.TripPiece.web.dto.request.TravelRequestDto;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TravelService {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final TravelRepository travelRepository;
    private final TripPieceRepository tripPieceRepository;
    private final EmojiRepository emojiRepository;
    private final PictureRepository pictureRepository;
    private final VideoRepository videoRepository;

    public List<Travel> searchByKeyword(String keyword) {
        List<City> cities = cityRepository.findByNameContainingIgnoreCase(keyword);
        List<Country> countries = countryRepository.findByNameContainingIgnoreCase(keyword);

        List<Travel> travels = new ArrayList<>();

        if(!cities.isEmpty()) {
            travels.addAll(cities.stream().flatMap(city -> travelRepository.findByCityId(city.getId()).stream()).toList());
        }
        if(!countries.isEmpty()) {
            travels.addAll(countries.stream().flatMap(country -> travelRepository.findByCity_CountryId(country.getId()).stream()).toList());
        }
        return travels;
    }


    @Transactional
    public TripPiece createMemo(Long travelId, TravelRequestDto.MemoDto request) {

        TripPiece newTripPiece = TravelConverter.toTripPieceMemo(request);
        newTripPiece.setTravel(travelRepository.findById(travelId).get());

        return tripPieceRepository.save(newTripPiece);
    }

    @Transactional
    public TripPiece createEmoji(Long travelId, String emoji, TravelRequestDto.MemoDto request) {

        TripPiece newTripPiece = TravelConverter.toTripPieceMemo(request);
        newTripPiece.setTravel(travelRepository.findById(travelId).get());
        //newTripPiece.setCategory(Category.EMOJI);

        Emoji newEmoji = TripPieceConverter.toTripPieceEmoji(emoji, newTripPiece);

        emojiRepository.save(newEmoji);

        return tripPieceRepository.save(newTripPiece);
    }

    @Transactional
    public TripPiece createPicture(Long travelId, MultipartFile picture, TravelRequestDto.MemoDto request) {

        TripPiece newTripPiece = TravelConverter.toTripPieceMemo(request);
        newTripPiece.setTravel(travelRepository.findById(travelId).get());
        //newTripPiece.setCategory(Category.PICTURE);

        String pictureUrl = null;

        Picture newPicture = TripPieceConverter.toTripPiecePicture(pictureUrl, newTripPiece);

        pictureRepository.save(newPicture);

        return tripPieceRepository.save(newTripPiece);

    }

    @Transactional
    public TripPiece createSelfie(Long travelId, MultipartFile picture, TravelRequestDto.MemoDto request) {

        TripPiece newTripPiece = TravelConverter.toTripPieceMemo(request);
        newTripPiece.setTravel(travelRepository.findById(travelId).get());
        //newTripPiece.setCategory(Category.SELFIE);

        String pictureUrl = null;

        Picture newPicture = TripPieceConverter.toTripPiecePicture(pictureUrl, newTripPiece);

        pictureRepository.save(newPicture);

        return tripPieceRepository.save(newTripPiece);

    }

    @Transactional
    public TripPiece createVideo(Long travelId, MultipartFile video, TravelRequestDto.MemoDto request) {

        TripPiece newTripPiece = TravelConverter.toTripPieceMemo(request);
        newTripPiece.setTravel(travelRepository.findById(travelId).get());
        //newTripPiece.setCategory(Category.VIDEO);

        String videoUrl = null;

        Video newVideo = TripPieceConverter.toTripPieceVideo(videoUrl, newTripPiece);

        videoRepository.save(newVideo);

        return tripPieceRepository.save(newTripPiece);

    }

    @Transactional
    public TripPiece createWhere(Long travelId, MultipartFile video, TravelRequestDto.MemoDto request) {

        TripPiece newTripPiece = TravelConverter.toTripPieceMemo(request);
        newTripPiece.setTravel(travelRepository.findById(travelId).get());
        //newTripPiece.setCategory(Category.WHERE);

        String videoUrl = null;

        Video newVideo = TripPieceConverter.toTripPieceVideo(videoUrl, newTripPiece);

        videoRepository.save(newVideo);

        return tripPieceRepository.save(newTripPiece);

    }

}
