package hruler.portfolio.domain.cafe;

import hruler.portfolio.domain.Address;
import hruler.portfolio.dto.CafeRegisterDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
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

    public static Cafe createCafe(String name, Address address) {
        Cafe cafe = new Cafe();
        cafe.setName(name);
        cafe.setAddress(address);

        return cafe;
    }

    public void updateInfo(CafeRegisterDto form) {
        this.name = form.getName();
        this.address = new Address(
                form.getCity(),
                form.getStreet(),
                form.getZipcode()
        );
    }
}
