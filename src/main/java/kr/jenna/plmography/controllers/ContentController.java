package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.Content.ContentDto;
import kr.jenna.plmography.dtos.Content.ContentsDto;
import kr.jenna.plmography.dtos.Page.PagesDto;
import kr.jenna.plmography.exceptions.ContentNotFound;
import kr.jenna.plmography.models.Content;
import kr.jenna.plmography.services.Content.GetContentService;
import kr.jenna.plmography.services.Content.GetContentsService;
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

    @GetMapping
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

    @GetMapping("/{tmdbId}")
    public ContentDto detail(@PathVariable String tmdbId) {
        return getContentService.detail(tmdbId);
    }

    @ExceptionHandler(ContentNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String contentNotFound() {
        return "Content not found!";
    }
}
