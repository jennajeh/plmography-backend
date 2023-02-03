package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.content.ContentDto;
import kr.jenna.plmography.dtos.content.ContentsDto;
import kr.jenna.plmography.dtos.content.UserProfileContentsDto;
import kr.jenna.plmography.exceptions.ContentNotFound;
import kr.jenna.plmography.services.content.GetContentService;
import kr.jenna.plmography.services.content.GetContentsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contents")
@CrossOrigin
public class ContentController {
    private GetContentService getContentService;
    private GetContentsService getContentsService;

    public ContentController(GetContentService getContentService, GetContentsService getContentsService) {
        this.getContentService = getContentService;
        this.getContentsService = getContentsService;
    }

    @GetMapping("/topRated")
    public ContentsDto topRated() {
        return getContentsService.topRated();
    }

    @GetMapping("/filter")
    public ContentsDto filter(
            @RequestParam(required = false) String platform,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Integer date,
            @RequestParam(required = false) String searchTitle,
            @RequestParam(required = false, defaultValue = "") String sort,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "8") Integer size
    ) {
        return getContentsService.filter(
                platform, type, genre, date, searchTitle, sort, page, size);
    }

    @GetMapping("/{tmdbId}")
    public ContentDto detail(@PathVariable Long tmdbId) {
        return getContentService.detail(tmdbId);
    }

    @GetMapping("/favorite")
    public UserProfileContentsDto favoriteContents(
            @RequestParam Long userId,
            @RequestParam(required = false) String favoriteContentId
    ) {
        return getContentsService.favoriteContents(userId, favoriteContentId);
    }

    @GetMapping("/watched")
    public UserProfileContentsDto watchedContents(
            @RequestParam Long userId,
            @RequestParam(required = false) String watchedContentId
    ) {
        return getContentsService.watchedContents(userId, watchedContentId);
    }

    @GetMapping("/wish")
    public UserProfileContentsDto wishContents(
            @RequestParam Long userId,
            @RequestParam(required = false) String wishContentId
    ) {
        return getContentsService.wishContents(userId, wishContentId);
    }

    @ExceptionHandler(ContentNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String contentNotFound() {
        return "Content not found!";
    }
}
