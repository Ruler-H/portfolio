package hruler.portfolio.controller;

import hruler.portfolio.domain.Address;
import hruler.portfolio.domain.Member;
import hruler.portfolio.dto.MemberLoginDto;
import hruler.portfolio.dto.MemberRegisterDto;
import hruler.portfolio.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    /**
     * Member Create GetMapping
     * @param model
     * @return createMemberForm.html
     */
    @GetMapping(value = "new")
    public String create(Model model) {
        model.addAttribute("memberRegisterForm", new MemberRegisterDto());
        return "members/createMemberForm";
    }

    /**
     * Member Create PostMapping
     * @param form
     * @param result
     * @return home.html
     */
    @PostMapping(value = "new")
    public String create(@Validated @ModelAttribute("memberRegisterForm") MemberRegisterDto form, BindingResult result) {
        if (result.hasErrors()) {return "members/createMemberForm";}

        memberService.join(new Member(form.getLoginId(), form.getPassword(), form.getName(),
                new Address(form.getCity(), form.getStreet(), form.getZipcode())));

        return "redirect:/";
    }

    /**
     * Member List GetMapping
     * @param model
     * @return memberList.html
     */
    @GetMapping
    public String list(Model model) {
        model.addAttribute("members", memberService.findMembers());

        return "members/memberList";
    }

    /**
     * Member Edit GetMapping
     * @param memberId
     * @param model
     * @return updateMemberForm.html
     */
    @GetMapping("/{memberId}/edit")
    public String edit(@PathVariable("memberId") Long memberId, Model model) {
        model.addAttribute("form",
                MemberRegisterDto.convert(memberService.findOne(memberId)));

        return "members/updateMemberForm";
    }

    /**
     * Member Edit PostMapping
     * @param memberId
     * @param form
     * @return members.html
     */
    @PostMapping("/{memberId}/edit")
    public String edit(@PathVariable("memberId") Long memberId, @ModelAttribute("form") MemberRegisterDto form,
                       HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        String memberName = loginMember.getName();
        memberService.update(memberId, form, memberName);

        return "redirect:/members";
    }

    @GetMapping("/login")
    public String login(Model model, @ModelAttribute("memberLoginDto") MemberLoginDto form) {
        return "members/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("memberLoginDto") MemberLoginDto form,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        BindingResult bindingResult, HttpServletRequest request) {
        log.info("redirectURL = {}", redirectURL);
        if (bindingResult.hasErrors()) {
            return "members/loginForm";
        }

        Member loginMember = memberService.login(form.getLoginId(), form.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "members/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }
}
