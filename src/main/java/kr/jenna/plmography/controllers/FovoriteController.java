package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.favorite.FovoriteUserIdsDto;
import kr.jenna.plmography.models.vo.ContentId;
import kr.jenna.plmography.models.vo.FavoriteUserId;
import kr.jenna.plmography.services.content.ToggleContentFavoriteService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FovoriteController {
    private ToggleContentFavoriteService toggleContentFavoriteService;

    public FovoriteController(ToggleContentFavoriteService toggleContentFavoriteService) {
        this.toggleContentFavoriteService = toggleContentFavoriteService;
    }

    @PatchMapping("/contents/{id}/favoriteUserIds")
    public FovoriteUserIdsDto toggleFavoriteContent(
            @PathVariable Long id,
            @RequestAttribute Long userId
    ) {
        ContentId contentId = new ContentId(id);
        FavoriteUserId favoriteUserId = new FavoriteUserId(userId);

        return toggleContentFavoriteService.toggleFavorite(contentId, favoriteUserId);
    }
}
