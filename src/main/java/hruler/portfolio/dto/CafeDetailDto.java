package hruler.portfolio.dto;

import hruler.portfolio.domain.Address;
import hruler.portfolio.domain.cafe.Cafe;
import hruler.portfolio.domain.cafe.Menu;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class CafeDetailDto {

    private Long id;
    private String name;
    private Address address;
    private List<Menu> menus;

    public CafeDetailDto(Cafe cafe) {
        this.id = cafe.getId();
        this.name = cafe.getName();
        this.address = cafe.getAddress();
        this.menus = cafe.getMenus();

    }
}
