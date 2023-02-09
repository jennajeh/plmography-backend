package kr.jenna.plmography.dtos.like;

import java.util.List;

public class SelectedLikeDto {
    public List<Long> likeId;

    public SelectedLikeDto() {
    }

    public SelectedLikeDto(List<Long> likeId) {
        this.likeId = likeId;
    }

    public List<Long> getLikeId() {
        return likeId;
    }

    public static SelectedLikeDto fake() {
        return new SelectedLikeDto(List.of(1L));
    }
}
