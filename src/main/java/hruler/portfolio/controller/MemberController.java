package hruler.portfolio.controller;

import hruler.portfolio.domain.Address;
import hruler.portfolio.domain.Member;
import hruler.portfolio.dto.MemberLoginDto;
import hruler.portfolio.dto.MemberRegisterDto;
import hruler.portfolio.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
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
    public String edit(@PathVariable("memberId") Long memberId, @ModelAttribute("form") MemberRegisterDto form) {
        memberService.update(memberId, form);

        return "redirect:/members";
    }

    @GetMapping("/login")
    public String login(Model model, @ModelAttribute("memberLoginDto") MemberLoginDto form) {
        return "members/loginForm";
    }
}
