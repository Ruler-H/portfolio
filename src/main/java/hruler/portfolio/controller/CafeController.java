package hruler.portfolio.controller;

import hruler.portfolio.domain.Address;
import hruler.portfolio.domain.Member;
import hruler.portfolio.domain.cafe.Cafe;
import hruler.portfolio.domain.cafe.Menu;
import hruler.portfolio.dto.*;
import hruler.portfolio.service.CafeService;
import hruler.portfolio.service.MenuService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cafes")
@Slf4j
public class CafeController {

    private final CafeService cafeService;

    private final MenuService menuService;

    /**
     * Cafe Register GetMapping
     * @param cafeRegisterDto
     * @return registerCafeForm.html
     */
    @GetMapping("new")
    public String register(@ModelAttribute("cafeRegisterForm") CafeRegisterDto cafeRegisterDto) {

        return "cafes/registerCafeForm";
    }

    /**
     * Cafe Register PostMapping
     * @param form -> CafeRegisterDto
     * @param result -> BindingResult
     * @return home.html
     */
    @PostMapping("new")
    public String register(@Validated @ModelAttribute("cafeRegisterForm") CafeRegisterDto form, BindingResult result,
                           HttpServletRequest request) {
        if (result.hasErrors()) {return "cafes/registerCafeForm";}

        HttpSession session = request.getSession(false);
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        String memberName = loginMember.getName();
        cafeService.registerCafe(form, memberName);

        return "redirect:/";
    }

    /**
     * Cafe List GetMapping
     * @param model
     * @return updateCafeForm.html
     */
    @GetMapping
    public String list(Model model, @ModelAttribute("cafeSearchDto")CafeSearchDto cafeSearchDto) {
        List<Cafe> cafes = cafeService.findCafes();
        model.addAttribute("cafes", cafes);
        return "cafes/cafeListForm";
    }

    @PostMapping
    public String searchCafe(@ModelAttribute("cafeSearchDto") CafeSearchDto cafeSearchDto, Model model) {
        List<Cafe> cafes = cafeService.searchCafe(cafeSearchDto);
        model.addAttribute("cafes", cafes);

        return "cafes/cafeListForm";
    }

    /**
     * Cafe edit GetMapping
     * @param cafeId
     * @param model
     * @return updateCafeForm.html
     */
    @GetMapping("{cafeId}/edit")
    public String edit(@PathVariable("cafeId") Long cafeId, Model model) {
        Cafe findCafe = cafeService.findOne(cafeId);
        model.addAttribute("form", new CafeEditDto(findCafe));

        return "cafes/updateCafeForm";
    }

    /**
     * Cafe edit PostMapping
     * @param cafeId
     * @param form
     * @return cafes.html
     */
    @PostMapping("{cafeId}/edit")
    public String edit(@PathVariable("cafeId") Long cafeId, @Validated @ModelAttribute("form") CafeEditDto form,
                       BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {return "cafes/updateCafeForm";}
        HttpSession session = request.getSession(false);
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        String memberName = loginMember.getName();
        cafeService.update(cafeId, form, memberName);

        return "redirect:/cafes";
    }

    /**
     * Cafe Detatil GetMapping
     * @param cafeId
     * @param model
     * @return cafeDetailForm.html
     */
    @GetMapping("{cafeId}/detail")
    public String detail(@PathVariable("cafeId") Long cafeId, Model model) {
        model.addAttribute("form", new CafeDetailDto(cafeService.findOne(cafeId)));

        return "cafes/cafeDetailForm";
    }

    /**
     * Cafe Menu Add GetMapping
     * @param model
     * @param cafeId
     * @return addMenuForm.html
     */
    @GetMapping("{cafeId}/addMenuForm")
    public String addMenu(Model model, @PathVariable Long cafeId,
                          @ModelAttribute("cafeMenuAddDto") CafeMenuAddDto cafeMenuAddDto){
        model.addAttribute("cafeId", cafeId);

        return "cafes/addMenuForm";
    }

    /**
     * Cafe Menu Add PostMapping
     * @param cafeMenuAddDto
     * @param cafeId
     * @param model
     * @param result
     * @return cafeDetailForm.html
     */
    @PostMapping("{cafeId}/addMenu")
    public String addMenu(@Validated @ModelAttribute("cafeMenuAddDto") CafeMenuAddDto cafeMenuAddDto,
                          BindingResult result, @PathVariable Long cafeId, Model model,
                          HttpServletRequest request) {

        if (result.hasErrors()) {return "cafes/addMenuForm";}

        HttpSession session = request.getSession(false);
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        String memberName = loginMember.getName();

        model.addAttribute("form",
                new CafeDetailDto(cafeService.addMenu(cafeId, cafeMenuAddDto, memberName)));

        return "redirect:/cafes/" + cafeId + "/detail";
    }
}
