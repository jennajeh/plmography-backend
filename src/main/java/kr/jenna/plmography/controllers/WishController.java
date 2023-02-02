package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.wish.WishContentIdsDto;
import kr.jenna.plmography.models.vo.WishContentId;
import kr.jenna.plmography.services.user.ToggleUserWishService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WishController {
    private ToggleUserWishService toggleUserWishService;

    public WishController(ToggleUserWishService toggleUserWishService) {
        this.toggleUserWishService = toggleUserWishService;
    }

    @PatchMapping("/users/wishContent/{contentId}")
    public WishContentIdsDto toggleWishContent(
            @RequestAttribute Long userId,
            @PathVariable Long contentId
    ) {
        WishContentId wishContentId = new WishContentId(contentId);

        return toggleUserWishService.toggleWish(userId, wishContentId);
    }
}
