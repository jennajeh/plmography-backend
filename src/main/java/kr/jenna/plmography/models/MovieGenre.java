package kr.jenna.plmography.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MovieGenre {
    Action(28, "Action"), Adventure(12, "Adventure"), Animation(16, "Animation"),
    Comedy(35, "Comedy"), Crime(80, "Crime"), Documentary(99, "Documentary"),
    Drama(18, "Drama"), Family(10751, "Family"), Fantasy(14, "Fantasy"),
    History(36, "History"), Horror(27, "Horror"), Music(10402, "Music"),
    Mystery(9648, "Mystery"), Romance(10749, "Romance"),
    ScienceFiction(878, "ScienceFiction"), TVMovie(10770, "TVMovie"),
    Thriller(53, "Thriller"), War(10752, "War"), Western(37, "Western");

    private final int value;
    private final String GenreName;
}
