package com.example.lorem.controller;

import com.example.lorem.dto.MemberDTO;
import com.example.lorem.entity.MemberEntity;
import com.example.lorem.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.hibernate.secure.spi.GrantedPermission;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/login")
    public String login() {
        return "login";
    }

    @PostMapping("/member/login")//로그인 된 상태로 사이트 이용가능하게 만들기
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session, HttpServletRequest request, Model model) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            // login 성공
            System.out.println("memberDTO = " + memberDTO + ", session = " + session);
            session = request.getSession();
            session.setAttribute("loggedIn", true);
            session.setAttribute("memberEmail",memberDTO.getMemberEmail());
            model.addAttribute("member", memberDTO);
            return "redirect:/";
        } else {
            // login 실패
            return "alert.msg";
        }
    }

    @GetMapping("/member/join")
    public String join() {
        return "join";
    }

    @PostMapping("/member/join")
    public String join(MemberDTO memberDTO) {
        memberService.save(memberDTO);
        return "redirect:/member/login";
    }

    @PostMapping("/member/email-check")
    //ajax사용할 때 ResponseBody 무조건 사용
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail) {
        System.out.println("memberEmail = " + memberEmail);
        String checkResult = memberService.emailCheck(memberEmail);
        return checkResult;
    }

    //로그아웃하기
    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        //session을 무효화하기
        session.invalidate();
        //HomeController-@GetMapping("/") 코드과정이 없으므로 그냥 html바로 return
        return "redirect:/";
    }

    @GetMapping("/member/userModify")
    public String memberupdate(HttpSession session, Model model) {
        System.out.println(session.getAttribute("memberEmail"));
        String memberEmail = (String) session.getAttribute("memberEmail");
        MemberDTO memberDTO = memberService.findByMemberEmail(memberEmail);
        model.addAttribute("member", memberDTO);
        return "userModify";
    }

    @PostMapping("/member/userModify")
    public String userModify(@ModelAttribute MemberDTO memberDTO, Model model, HttpSession session) {
        memberDTO.setMemberEmail((String) session.getAttribute("memberEmail"));
        MemberDTO loginResult = memberService.login(memberDTO);
        if(loginResult != null) {
            memberDTO = memberService.findByMemberEmail(memberDTO.getMemberEmail());
            model.addAttribute("member", memberDTO);
            return "memberupdate";
        } else {
            return "alert.pwincorrect";
        }
    }

    @PostMapping("/member/changeInfo")
    public String userModify(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        memberDTO.setMemberEmail((String) session.getAttribute("memberEmail"));
        System.out.println(memberDTO.getMemberName());
        memberService.update(memberDTO);
        session.invalidate();
        return "alert.relogin";
    }
}
