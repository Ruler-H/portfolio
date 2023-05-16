package hruler.portfolio.controller;

import hruler.portfolio.domain.Address;
import hruler.portfolio.domain.Member;
import hruler.portfolio.form.MemberRegisterForm;
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
    public String createForm(Model model) {
        model.addAttribute("memberRegisterForm", new MemberRegisterForm());
        return "members/createMemberForm";
    }

    @PostMapping(value = "new")
    public String create(@Valid MemberRegisterForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Member member = new Member(form.getName(), address);

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    @GetMapping("/{memberId}/edit")
    public String edit(@PathVariable("memberId") Long memberId, Model model) {
        Member findMember = memberService.findOne(memberId);

        MemberRegisterForm form = MemberRegisterForm.convert(findMember);

        model.addAttribute("form", form);

        return "members/updateMemberForm";
    }

    @PostMapping("/{memberId}/edit")
    public String edit(@PathVariable("memberId") Long memberId,
                       @ModelAttribute("form") MemberRegisterForm form) {
        memberService.update(memberId, form);

        return "redirect:/members";
    }
}
