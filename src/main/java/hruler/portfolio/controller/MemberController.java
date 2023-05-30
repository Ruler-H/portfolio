package hruler.portfolio.controller;

import hruler.portfolio.domain.Address;
import hruler.portfolio.domain.Member;
import hruler.portfolio.dto.MemberRegisterDto;
import hruler.portfolio.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "new")
    public String create(Model model) {
        model.addAttribute("memberRegisterForm", new MemberRegisterDto());
        return "members/createMemberForm";
    }

    @PostMapping(value = "new")
    public String create(@Valid MemberRegisterDto form, BindingResult result) {
        if (result.hasErrors()) {return "members/createMemberForm";}

        memberService.join(new Member(form.getName(),
                new Address(form.getCity(), form.getStreet(), form.getZipcode())));

        return "redirect:/";
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("members", memberService.findMembers());

        return "members/memberList";
    }

    @GetMapping("/{memberId}/edit")
    public String edit(@PathVariable("memberId") Long memberId, Model model) {
        model.addAttribute("form",
                MemberRegisterDto.convert(memberService.findOne(memberId)));

        return "members/updateMemberForm";
    }

    @PostMapping("/{memberId}/edit")
    public String edit(@PathVariable("memberId") Long memberId, @ModelAttribute("form") MemberRegisterDto form) {
        memberService.update(memberId, form);

        return "redirect:/members";
    }
}
