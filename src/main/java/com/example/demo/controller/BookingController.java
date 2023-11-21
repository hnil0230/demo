package com.example.demo.controller;

import com.example.demo.dto.ChangePasswordRequest;
import com.example.demo.dto.JoinRequest;
import com.example.demo.entity.MemberEntity;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class BookingController {
    private final MemberService memberService;
    @GetMapping("/booking/seatChoose")
    public String seat(Model model, @SessionAttribute(name = "userId", required = false) Long userId) {
        if (userId == null) {
            // 사용자가 로그인하지 않은 경우, 로그인 페이지로 리디렉션
            return "redirect:/members/login";
        }

        model.addAttribute("loginType", "session-login");
        model.addAttribute("pageName", "세션 로그인");
        model.addAttribute("changePasswordRequest", new ChangePasswordRequest());
        model.addAttribute("joinRequest", new JoinRequest());
        MemberEntity loginUser = memberService.getLoginUserByMno(userId);
        if(loginUser != null) {
            model.addAttribute("name", loginUser.getMemberName());
        }

        return "/booking/concertSelect";
    }
}
