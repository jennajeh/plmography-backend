package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.login.LoginResultDto;
import kr.jenna.plmography.dtos.oauth.SocialLoginResultDto;
import kr.jenna.plmography.services.user.LoginService;
import kr.jenna.plmography.utils.KakaoAuthUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth")
public class OauthLoginController {
    private final KakaoAuthUtil kakaoAuthUtil;
    private final LoginService loginService;

    public OauthLoginController(KakaoAuthUtil kakaoAuthUtil,
                                LoginService loginService) {
        this.kakaoAuthUtil = kakaoAuthUtil;
        this.loginService = loginService;
    }

    @GetMapping("/kakao")
    public LoginResultDto login(@RequestParam String code) {
        SocialLoginResultDto kakaoDto = kakaoAuthUtil.process(code);

        return loginService.socialLogin(kakaoDto);
    }
}
