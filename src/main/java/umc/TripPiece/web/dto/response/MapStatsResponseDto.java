package umc.TripPiece.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MapStatsResponseDto {
    private long countryCount;
    private long cityCount;

    // 추가: 국가 코드와 도시 ID 리스트
    private List<String> countryCodes;
    private List<Long> cityIds;
}
