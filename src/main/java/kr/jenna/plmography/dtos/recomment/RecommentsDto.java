package kr.jenna.plmography.dtos.recomment;

import java.util.List;

public class RecommentsDto {
    private List<RecommentDto> recomments;

    public RecommentsDto(List<RecommentDto> recomments) {
        this.recomments = recomments;
    }

    public List<RecommentDto> getRecomments() {
        return recomments;
    }
}
