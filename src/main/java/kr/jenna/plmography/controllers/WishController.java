package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.Wish.WishUserIdsDto;
import kr.jenna.plmography.models.VO.ContentId;
import kr.jenna.plmography.models.VO.WishUserId;
import kr.jenna.plmography.services.Content.ToggleContentWishService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WishController {
    private ToggleContentWishService toggleContentWishService;

    public WishController(ToggleContentWishService toggleContentWishService) {
        this.toggleContentWishService = toggleContentWishService;
    }

    @PatchMapping("/contents/{id}/wishUserIds")
    public WishUserIdsDto toggleWishContent(
            @PathVariable Long id,
            @RequestAttribute Long userId
    ) {
        ContentId contentId = new ContentId(id);
        WishUserId wishUserId = new WishUserId(userId);

        return toggleContentWishService.toggleWish(contentId, wishUserId);
    }
}
