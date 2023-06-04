package hruler.portfolio.dto;

import hruler.portfolio.domain.Address;
import hruler.portfolio.domain.cafe.Cafe;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CafeRegisterDto {

    private Long id;

    @NotBlank
    private String name;
    @NotNull
    private String city;
    @NotNull
    private String street;
    @NotNull
    private String zipcode;


    public static CafeRegisterDto convert(Cafe cafe) {
        CafeRegisterDto cafeRegisterForm = new CafeRegisterDto();
        cafeRegisterForm.setId(cafe.getId());
        cafeRegisterForm.setName(cafe.getName());
        cafeRegisterForm.setCity(cafe.getAddress().getCity());
        cafeRegisterForm.setStreet(cafe.getAddress().getStreet());
        cafeRegisterForm.setZipcode(cafe.getAddress().getZipcode());
        return cafeRegisterForm;
    }
}
