package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.recomment.RecommentCreationDto;
import kr.jenna.plmography.dtos.recomment.RecommentDto;
import kr.jenna.plmography.dtos.recomment.RecommentModificationDto;
import kr.jenna.plmography.dtos.recomment.RecommentRegistrationDto;
import kr.jenna.plmography.dtos.recomment.RecommentsDto;
import kr.jenna.plmography.models.Recomment;
import kr.jenna.plmography.services.recomment.CreateRecommentService;
import kr.jenna.plmography.services.recomment.DeleteRecommentService;
import kr.jenna.plmography.services.recomment.GetRecommentService;
import kr.jenna.plmography.services.recomment.GetRecommentsService;
import kr.jenna.plmography.services.recomment.PatchRecommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecommentController {
    private final CreateRecommentService createRecommentService;
    private final GetRecommentService getRecommentService;
    private final GetRecommentsService getRecommentsService;
    private final PatchRecommentService patchRecommentService;
    private final DeleteRecommentService deleteRecommentService;

    public RecommentController(CreateRecommentService createRecommentService,
                               GetRecommentService getRecommentService,
                               GetRecommentsService getRecommentsService,
                               PatchRecommentService patchRecommentService,
                               DeleteRecommentService deleteRecommentService) {
        this.createRecommentService = createRecommentService;
        this.getRecommentService = getRecommentService;
        this.getRecommentsService = getRecommentsService;
        this.patchRecommentService = patchRecommentService;
        this.deleteRecommentService = deleteRecommentService;
    }

    @PostMapping("/recomments")
    @ResponseStatus(HttpStatus.CREATED)
    public RecommentCreationDto create(
            @RequestAttribute Long userId,
            @RequestBody RecommentRegistrationDto recommentRegistrationDto
    ) {
        Recomment recomment = createRecommentService.create(userId, recommentRegistrationDto);

        return recomment.toCreateDto();
    }

    @GetMapping("/recomments")
    public RecommentsDto list() {
        return getRecommentsService.recomments();
    }

    @GetMapping("/posts/{id}/recomments")
    public RecommentsDto detail(@PathVariable Long id) {
        return getRecommentService.detail(id);
    }

    @PatchMapping("/recomments/{id}")
    public RecommentModificationDto modify(
            @RequestAttribute Long userId,
            @PathVariable Long id,
            @RequestBody RecommentDto recommentDto
    ) {
        Recomment recomment = patchRecommentService.modify(userId, id, recommentDto);

        return recomment.toRecommentModificationDto();
    }

    @DeleteMapping("/recomments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @RequestAttribute Long userId,
            @PathVariable Long id
    ) {

        deleteRecommentService.delete(userId, id);
    }
}
