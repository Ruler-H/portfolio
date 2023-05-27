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

    @GetMapping("new")
    public String registerCafe(Model model) {
        model.addAttribute("cafeRegisterForm", new CafeRegisterDto());
        return "cafes/registerCafeForm";
    }

    @PostMapping("new")
    public String cafeRegister(@Valid CafeRegisterDto form, BindingResult result) {
        if (result.hasErrors()) {
            return "cafes/registerCafeForm";
        }
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Cafe cafe = Cafe.createCafe(form.getName(), address);

        cafeService.registerCafe(cafe);

        return "redirect:/";
    }

    @GetMapping
    public String list(Model model) {
        List<Cafe> cafes = cafeService.findCafes();
        model.addAttribute("cafes", cafes);
        return "cafes/cafeListForm";
    }

    @GetMapping("{cafeId}/edit")
    public String edit(@PathVariable("cafeId") Long cafeId, Model model) {
        Cafe findCafe = cafeService.findOne(cafeId);
        CafeRegisterDto form = CafeRegisterDto.convert(findCafe);
        model.addAttribute("form", form);
        return "cafes/updateCafeForm";
    }

    @PostMapping("{cafeId}/edit")
    public String edit(@PathVariable("cafeId") Long cafeId,
                       @ModelAttribute("form") CafeRegisterDto form) {
//        log.info("cafeInfo = {}", form.getName());
        cafeService.update(cafeId, form);

        return "redirect:/cafes";
    }

    @GetMapping("{cafeId}/detail")
    public String detail(@PathVariable("cafeId") Long cafeId,
                         Model model) {
        log.info("cafeId = {}", cafeId);
        Cafe findCafe = cafeService.findOne(cafeId);
        log.info("findCafe = {}", findCafe);
        CafeDetailDto form = new CafeDetailDto(findCafe);
        model.addAttribute("form", form);
        return "cafes/cafeDetailForm";
    }

    @GetMapping("{cafeId}/addMenuForm")
    public String addMenu(Model model,
                          @PathVariable Long cafeId) {
        model.addAttribute("menuAddForm", new CafeMenuAddDto());
        model.addAttribute("cafeId", cafeId);
        return "cafes/addMenuForm";
    }

    @PostMapping("{cafeId}/addMenu")
    public String addMenu(@Valid CafeMenuAddDto cafeMenuAddDto,
                          @PathVariable Long cafeId,
                          Model model,
                          BindingResult result) {
        Cafe findCafe = cafeService.findOne(cafeId);
        Menu menu = new Menu(cafeMenuAddDto, findCafe);
        findCafe.addMenu(menu);
        menuService.registerMenu(menu);
        CafeDetailDto cafeDetailDto = new CafeDetailDto(findCafe);
        model.addAttribute("form", cafeDetailDto);
        return "redirect:/cafes/" + cafeId + "/detail";
    }
}
