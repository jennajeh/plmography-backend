package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.watched.WatchedContentIdsDto;
import kr.jenna.plmography.models.vo.WatchedContentId;
import kr.jenna.plmography.services.user.ToggleUserWatchedService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WatchedController {
    private ToggleUserWatchedService toggleUserWatchedService;

    public WatchedController(ToggleUserWatchedService toggleUserWatchedService) {
        this.toggleUserWatchedService = toggleUserWatchedService;
    }

    @PatchMapping("/users/watchedContent/{contentId}")
    public WatchedContentIdsDto toggleWatchedContent(
            @RequestAttribute Long userId,
            @PathVariable Long contentId
    ) {
        WatchedContentId watchedContentId = new WatchedContentId(contentId);

        return toggleUserWatchedService.toggleWatched(userId, watchedContentId);
    }
}
