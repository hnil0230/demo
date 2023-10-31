package com.example.demo.controller;

//import com.example.demo.domain.Member;
import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.MemberEntity;
import com.example.demo.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;


    @GetMapping("/members/new")
    public String createFrom() {
        return "members/createAccount";
    }
    @PostMapping("/members/new")
    public String create(@ModelAttribute MemberDTO memberDTO,  Model model){
        // 회원가입 요청 처리
        String errorMessage = memberService.save(memberDTO);

        if (errorMessage != null) {
            // 회원가입 실패
            model.addAttribute("errorMessage", errorMessage);
            return "members/createAccount";
        }
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        System.out.println("name = " + memberDTO.getMemberName());
        System.out.println("Email = " + memberDTO.getMemberEmail());
        System.out.println("password = " + memberDTO.getMemberPassword());


        return "redirect:/members/login";
    }

/*    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return  "members/memberList";
    }*/

    @GetMapping("/members/login")
    public String loginForm() {
        return "members/login";
    }

    @PostMapping("/members/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session, Model model) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null){
            // 로그인 성공
            session.setAttribute("loginEmail",loginResult.getMemberEmail());
            return "/main";
        }else {
            // 로그인 실패
            model.addAttribute("errorMessage", "로그인 실패. 아이디 또는 비밀번호를 확인하세요.");
            return "/members/login";
        }
    }

}

