package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.favorite.FovoriteContentIdsDto;
import kr.jenna.plmography.models.vo.FavoriteContentId;
import kr.jenna.plmography.services.user.ToggleUserFavoriteService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FavoriteController {
    private ToggleUserFavoriteService toggleUserFavoriteService;

    public FavoriteController(ToggleUserFavoriteService toggleUserFavoriteService) {
        this.toggleUserFavoriteService = toggleUserFavoriteService;
    }

    @PatchMapping("/users/favoriteContent/{contentId}")
    public FovoriteContentIdsDto toggleFavoriteContent(
            @RequestAttribute Long userId,
            @PathVariable Long contentId
    ) {
        FavoriteContentId favoriteContentId = new FavoriteContentId(contentId);

        return toggleUserFavoriteService.toggleFavorite(userId, favoriteContentId);
    }
}
