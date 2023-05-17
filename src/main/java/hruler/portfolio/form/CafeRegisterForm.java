package hruler.portfolio.form;

import hruler.portfolio.domain.Member;
import hruler.portfolio.domain.cafe.Cafe;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CafeRegisterForm {

    private Long id;

    private String name;
    private String city;
    private String street;
    private String zipcode;

    public static CafeRegisterForm convert(Cafe cafe) {
        CafeRegisterForm cafeRegisterForm = new CafeRegisterForm();
        cafeRegisterForm.setId(cafe.getId());
        cafeRegisterForm.setName(cafe.getName());
        cafeRegisterForm.setCity(cafe.getAddress().getCity());
        cafeRegisterForm.setStreet(cafe.getAddress().getStreet());
        cafeRegisterForm.setZipcode(cafe.getAddress().getZipcode());

        return cafeRegisterForm;
    }
}
