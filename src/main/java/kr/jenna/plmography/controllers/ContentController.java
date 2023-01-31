package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.content.ContentDto;
import kr.jenna.plmography.dtos.content.ContentsDto;
import kr.jenna.plmography.dtos.page.PagesDto;
import kr.jenna.plmography.exceptions.ContentNotFound;
import kr.jenna.plmography.models.Content;
import kr.jenna.plmography.services.content.GetContentService;
import kr.jenna.plmography.services.content.GetContentsService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/list")
    public ContentsDto list(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "8") Integer size
    ) {
        Page<Content> contents = getContentsService.list(page, size);

        List<ContentDto> contentDtos = contents.stream()
                .map(content -> content.toContentDto())
                .collect(Collectors.toList());

        PagesDto pagesDto = new PagesDto(contents.getTotalPages());

        return new ContentsDto(contentDtos, pagesDto);
    }

    @GetMapping
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

//    @GetMapping("/userProfile")
//    public UserProfileContentsDto userProfile(@PathVariable Long userId) {
//        return getContentService.userProfile(userId);
//    }

    @ExceptionHandler(ContentNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String contentNotFound() {
        return "Content not found!";
    }
}
