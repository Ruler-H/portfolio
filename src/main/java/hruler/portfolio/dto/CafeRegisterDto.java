package hruler.portfolio.dto;

import hruler.portfolio.domain.Address;
import hruler.portfolio.domain.cafe.Cafe;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CafeRegisterDto {

    private Long id;

    private String name;
    private String city;
    private String street;
    private String zipcode;
    private Address address;


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
