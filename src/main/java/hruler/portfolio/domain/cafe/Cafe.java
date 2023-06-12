package hruler.portfolio.domain.cafe;

import hruler.portfolio.domain.Address;
import hruler.portfolio.dto.CafeMenuAddDto;
import hruler.portfolio.dto.CafeRegisterDto;
import hruler.portfolio.service.MenuService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Slf4j
@Table(name = "cafes")
public class Cafe {

    @Id @GeneratedValue
    @Column(name = "cafe_id")
    private Long id;
    private String name;
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "cafe", cascade = CascadeType.ALL)
    private List<Menu> menus = new ArrayList<>();

    private void setName(String name) {
        this.name = name;
    }

    private void setAddress(Address address) {
        this.address = address;
    }

    public Cafe(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public static Cafe createCafe(String name, Address address) {
        Cafe cafe = new Cafe();
        cafe.setName(name);
        cafe.setAddress(address);

        return cafe;
    }

    /**
     * Cafe Information Update
     * @param form
     */
    public void updateInfo(CafeRegisterDto form) {
        this.name = form.getName();
        this.address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
    }

    /**
     * Menu Add
     * @param menu
     */
    public void addMenu(Menu menu) {
        this.menus.add(menu);
    }
}
