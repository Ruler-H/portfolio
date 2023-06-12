package hruler.portfolio.controller;

import hruler.portfolio.domain.Address;
import hruler.portfolio.domain.cafe.Cafe;
import hruler.portfolio.domain.cafe.Menu;
import hruler.portfolio.dto.CafeDetailDto;
import hruler.portfolio.dto.CafeMenuAddDto;
import hruler.portfolio.dto.CafeRegisterDto;
import hruler.portfolio.service.CafeService;
import hruler.portfolio.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public String register(@Validated @ModelAttribute("cafeRegisterForm") CafeRegisterDto form, BindingResult result) {
        if (result.hasErrors()) {return "cafes/registerCafeForm";}

        cafeService.registerCafe(new Cafe(form.getName(),
                new Address(form.getCity(), form.getStreet(), form.getZipcode())));

        return "redirect:/";
    }

    /**
     * Cafe List GetMapping
     * @param model
     * @return updateCafeForm.html
     */
    @GetMapping
    public String list(Model model) {
        model.addAttribute("cafes", cafeService.findCafes());
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
        model.addAttribute("form",
                CafeRegisterDto.convert(cafeService.findOne(cafeId)));

        return "cafes/updateCafeForm";
    }

    /**
     * Cafe edit PostMapping
     * @param cafeId
     * @param form
     * @return cafes.html
     */
    @PostMapping("{cafeId}/edit")
    public String edit(@PathVariable("cafeId") Long cafeId, @ModelAttribute("form") CafeRegisterDto form) {
        cafeService.update(cafeId, form);

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
                          @PathVariable Long cafeId,
                          Model model, BindingResult result) {
        log.info("error = {}", result.hasErrors());
        if (result.hasErrors()) {
            return "cafes/addMenuForm";
        }
        Cafe findCafe = cafeService.findOne(cafeId);
        Menu menu = new Menu(cafeMenuAddDto, findCafe);
        menuService.registerMenu(menu);
        findCafe.addMenu(menu);
        model.addAttribute("form", new CafeDetailDto(findCafe));
        return "redirect:/cafes/" + cafeId + "/detail";
    }
}
