package hruler.portfolio.controller;

import hruler.portfolio.domain.Address;
import hruler.portfolio.domain.cafe.Cafe;
import hruler.portfolio.domain.cafe.Menu;
import hruler.portfolio.dto.CafeDetailDto;
import hruler.portfolio.dto.CafeMenuAddDto;
import hruler.portfolio.dto.CafeRegisterDto;
import hruler.portfolio.service.CafeService;
import hruler.portfolio.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
     * @param model
     * @return registerCafeForm.html
     */
    @GetMapping("new")
    public String register(Model model) {
        model.addAttribute("cafeRegisterForm", new CafeRegisterDto());

        return "cafes/registerCafeForm";
    }

    /**
     * Cafe Register PostMapping
     * @param form -> CafeRegisterDto
     * @param result -> BindingResult
     * @return home.html
     */
    @PostMapping("new")
    public String register(@Valid CafeRegisterDto form, BindingResult result) {
        if (result.hasErrors()) {return "cafes/registerCafeForm";}

        cafeService.registerCafe(Cafe.createCafe(form.getName(),
                new Address(form.getCity(), form.getStreet(), form.getZipcode())));

        return "redirect:/";
    }

    /**
     * Cafe List GetMapping
     * @param model
     * @return
     */
    @GetMapping
    public String list(Model model) {
        model.addAttribute("cafes", cafeService.findCafes());

        return "cafes/cafeListForm";
    }

    @GetMapping("{cafeId}/edit")
    public String edit(@PathVariable("cafeId") Long cafeId, Model model) {
        model.addAttribute("form",
                CafeRegisterDto.convert(cafeService.findOne(cafeId)));

        return "cafes/updateCafeForm";
    }

    @PostMapping("{cafeId}/edit")
    public String edit(@PathVariable("cafeId") Long cafeId, @ModelAttribute("form") CafeRegisterDto form) {
        cafeService.update(cafeId, form);

        return "redirect:/cafes";
    }

    @GetMapping("{cafeId}/detail")
    public String detail(@PathVariable("cafeId") Long cafeId, Model model) {
        model.addAttribute("form", new CafeDetailDto(cafeService.findOne(cafeId)));

        return "cafes/cafeDetailForm";
    }

    @GetMapping("{cafeId}/addMenuForm")
    public String addMenu(Model model, @PathVariable Long cafeId) {
        model.addAttribute("menuAddForm", new CafeMenuAddDto());
        model.addAttribute("cafeId", cafeId);
        return "cafes/addMenuForm";
    }

    @PostMapping("{cafeId}/addMenu")
    public String addMenu(@Valid CafeMenuAddDto cafeMenuAddDto, @PathVariable Long cafeId,
                          Model model, BindingResult result) {
        Cafe findCafe = cafeService.findOne(cafeId);
        menuService.registerMenu(new Menu(cafeMenuAddDto, findCafe), findCafe);
        model.addAttribute("form", new CafeDetailDto(findCafe));
        return "redirect:/cafes/" + cafeId + "/detail";
    }
}
