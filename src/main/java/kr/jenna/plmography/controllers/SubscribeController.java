package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.subscribe.MySubscribeDto;
import kr.jenna.plmography.dtos.subscribe.OtherSubscribeDto;
import kr.jenna.plmography.dtos.subscribe.SubscribesDto;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.services.subscribe.CreateSubscribeService;
import kr.jenna.plmography.services.subscribe.DeleteSubscribeService;
import kr.jenna.plmography.services.subscribe.GetSubscribesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class SubscribeController {
    private CreateSubscribeService createSubscribeService;
    private GetSubscribesService getSubscribesService;
    private DeleteSubscribeService deleteSubscribeService;

    public SubscribeController(
            CreateSubscribeService createSubscribeService,
            GetSubscribesService getSubscribesService,
            DeleteSubscribeService deleteSubscribeService) {
        this.createSubscribeService = createSubscribeService;
        this.getSubscribesService = getSubscribesService;
        this.deleteSubscribeService = deleteSubscribeService;
    }

    @PostMapping("/subscribe/{followingId}")
    @ResponseStatus(HttpStatus.CREATED)
    public String follow(
            @RequestAttribute Long userId,
            @PathVariable Long followingId
    ) {
        createSubscribeService.follow(userId, followingId);

        return "follow completed";
    }

    @GetMapping("/subscribe/me")
    public MySubscribeDto mySubscribeCount(@RequestAttribute Long userId) {
        MySubscribeDto mySubscribeDto =
                getSubscribesService.mySubscribeCount(userId);

        return mySubscribeDto;
    }

    @GetMapping("/subscribe/{otherUserId}")
    public OtherSubscribeDto otherSubscribeCount(
            @RequestAttribute Long userId,
            @PathVariable Long otherUserId
    ) {
        OtherSubscribeDto otherSubscribeDto =
                getSubscribesService.otherSubscribeCount(userId, otherUserId);

        return otherSubscribeDto;
    }

    @GetMapping("/users/following")
    public SubscribesDto followingList(
            @RequestParam Long userId,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) {
        return getSubscribesService.followingList(userId, page, size);
    }

    @GetMapping("/users/follower")
    public SubscribesDto followerList(
            @RequestParam Long userId,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) {
        return getSubscribesService.followerList(userId, page, size);
    }

    @DeleteMapping("/subscribe/{followingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unFollow(
            @RequestAttribute Long userId,
            @PathVariable Long followingId
    ) {
        deleteSubscribeService.unFollow(userId, followingId);
    }

    @ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userNotFound() {
        return "User not found!";
    }
}
