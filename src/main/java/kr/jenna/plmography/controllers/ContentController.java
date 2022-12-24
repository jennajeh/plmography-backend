package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.ContentDto;
import kr.jenna.plmography.exceptions.ContentNotFound;
import kr.jenna.plmography.services.GetContentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contents")
public class ContentController {
    private GetContentService getContentService;

    public ContentController(GetContentService getContentService) {
        this.getContentService = getContentService;
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
