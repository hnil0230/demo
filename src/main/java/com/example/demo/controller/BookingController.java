package com.example.demo.controller;

import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class BookingController {
    @Autowired
    private MemberService memberService;

/*    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        List<ResearchDAO> rsearchResults = boardService.searchResearchByKeyword(keyword);
        model.addAttribute("postList", rsearchResults);
        return "/search-results.html"; // 검색 결과를 나타낼 HTML 페이지의 이름
    }*/

}
