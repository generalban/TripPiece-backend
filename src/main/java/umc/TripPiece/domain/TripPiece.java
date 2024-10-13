package umc.TripPiece.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import umc.TripPiece.domain.common.BaseEntity;
import umc.TripPiece.domain.enums.Category;
//import umc.TripPiece.domain.enums.Category;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TripPiece extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'MEMO'")
    private Category category;

    @Column(nullable = false)
    private String description;

    @Column
    @ColumnDefault("true")
    private Boolean travel_contain;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_id")
    private Travel travel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "tripPiece", cascade = CascadeType.ALL)
    private List<Picture> pictures = new ArrayList<>();

    @OneToMany(mappedBy = "tripPiece", cascade = CascadeType.ALL)
    private List<Video> videos = new ArrayList<>();

    @OneToMany(mappedBy = "tripPiece", cascade = CascadeType.ALL)
    private List<Emoji> emojis = new ArrayList<>();

    public void updateMemo(String description) {
        this.description = description;
    }

    public void updatePicture(String description, List<Picture> pictures) {
        this.description = description;
        this.pictures = pictures;
    }

    public void updateVideo(String description, List<Video> videos) {
        this.description = description;
        this.videos = videos;
    }

    public void updateEmoji(String description, List<Emoji> emojis) {
        this.description = description;
        this.emojis = emojis;
    }

}
