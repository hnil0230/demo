package com.example.demo.controller;

import com.example.demo.entity.MemberEntity;
import com.example.demo.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class MainController {

    @GetMapping("/main")
    public String main(Model model, @SessionAttribute(name = "userId", required = false) Long userId) {
        model.addAttribute("loginType", "session-login");
        model.addAttribute("pageName", "세션 로그인");

        MemberEntity loginUser = MemberService.getLoginUser;

        return "main";
    }
}
