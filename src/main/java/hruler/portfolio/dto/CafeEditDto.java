package hruler.portfolio.dto;

import hruler.portfolio.domain.Address;
import hruler.portfolio.domain.cafe.Cafe;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter @Setter
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class CafeEditDto {

    @NotNull
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private String city;
    @NotNull
    private String street;
    @NotNull
    private String zipcode;

    public CafeEditDto(Cafe cafe){
        log.info("cafe id={}", cafe.getId());
        this.id = cafe.getId();
        this.name = cafe.getName();
        this.city = cafe.getAddress().getCity();
        this.street = cafe.getAddress().getStreet();
        this.zipcode = cafe.getAddress().getZipcode();
    }
}
