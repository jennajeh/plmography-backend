package kr.jenna.plmography.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum TvDramaGenre {
    Action_Adventure(10759, "Action"), Animation(16, "Animation"),
    Comedy(35, "Comedy"), Crime(80, "Crime"),
    Documentary(99, "Documentary"), Drama(18, "Drama"),
    Family(10751, "Family"), Kids(10762, "Kids"),
    Mystery(9648, "Mystery"), ScienceFiction(10765, "ScienceFiction"),
    War(10768, "War"), Western(37, "Western");

    private final int value;
    private final String genreName;

    private static final Map<Integer, String> GENRE_MAP =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(TvDramaGenre::getValue, TvDramaGenre::name)));

    public static TvDramaGenre of(final int value) {
        return TvDramaGenre.valueOf(GENRE_MAP.get(value));
    }
}
