package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.watched.WatchedUserIdsDto;
import kr.jenna.plmography.models.vo.ContentId;
import kr.jenna.plmography.models.vo.WatchedUserId;
import kr.jenna.plmography.services.content.ToggleContentWatchedService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WatchedController {
    private ToggleContentWatchedService toggleContentWatchedService;

    public WatchedController(ToggleContentWatchedService toggleContentWatchedService) {
        this.toggleContentWatchedService = toggleContentWatchedService;
    }

    @PatchMapping("/contents/{id}/watchedUserIds")
    public WatchedUserIdsDto toggleWatchedContent(
            @PathVariable Long id,
            @RequestAttribute Long userId
    ) {
        ContentId contentId = new ContentId(id);
        WatchedUserId watchedUserId = new WatchedUserId(userId);

        return toggleContentWatchedService.toggleWatched(contentId, watchedUserId);
    }
}
