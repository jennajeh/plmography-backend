package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.ContentDto;
import kr.jenna.plmography.dtos.ContentsDto;
import kr.jenna.plmography.dtos.PagesDto;
import kr.jenna.plmography.exceptions.ContentNotFound;
import kr.jenna.plmography.models.Content;
import kr.jenna.plmography.services.GetContentService;
import kr.jenna.plmography.services.GetContentsService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{contentId}")
    public ContentDto detail(@PathVariable Long contentId) {
        return getContentService.detail(contentId);
    }

    @ExceptionHandler(ContentNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String contentNotFound() {
        return "Content not found!";
    }
}
