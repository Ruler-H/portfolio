package hruler.portfolio.controller;

import hruler.portfolio.domain.Address;
import hruler.portfolio.domain.cafe.Cafe;
import hruler.portfolio.form.CafeRegisterForm;
import hruler.portfolio.service.CafeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cafes")
@Slf4j
public class CafeController {

    private final CafeService cafeService;

    @GetMapping("new")
    public String registerCafe(Model model) {
        model.addAttribute("cafeRegisterForm", new CafeRegisterForm());
        return "cafes/registerCafeForm";
    }

    @PostMapping("new")
    public String cafeRegister(@Valid CafeRegisterForm form, BindingResult result) {
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
}
