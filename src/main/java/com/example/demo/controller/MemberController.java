package com.example.demo.controller;

//import com.example.demo.domain.Member;
//import com.example.demo.dto.MemberDTO;
import com.example.demo.dto.JoinRequest;
import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.MemberEntity;
import com.example.demo.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;


    @GetMapping("/members/new")
    public String createFrom(Model model) {
        model.addAttribute("loginType", "session-login");
        model.addAttribute("pageName", "세션 로그인");

        model.addAttribute("joinRequest", new JoinRequest());
        return "members/createAccount";
    }
    @PostMapping("/members/new")
    public String create(@ModelAttribute JoinRequest joinRequest, Model model, BindingResult bindingResult){
        model.addAttribute("loginType", "session-login");
        model.addAttribute("pageName", "세션 로그인");

        // Email 중복 체크
        if(memberService.checkMemberEmailDuplicate(joinRequest.getMemberEmail())) {
            bindingResult.addError(new FieldError("joinRequest", "MemberEmail", "이메일이 중복됩니다."));
        }

        // password와 passwordCheck가 같은지 체크
        if(!joinRequest.getMemberPassword().equals(joinRequest.getMemberPasswordCheck())) {
            bindingResult.addError(new FieldError("joinRequest", "MemberPasswordCheck", "비밀번호가 일치하지 않습니다."));
        }

        if(bindingResult.hasErrors()) {
            return "members/createAccount";
        }

        memberService.join(joinRequest);
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
    public String login(@ModelAttribute LoginRequest loginRequest, HttpSession session, Model model, BindingResult bindingResult) {
        MemberEntity loginResult = memberService.login(loginRequest);
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

