package hruler.portfolio.domain.cafe;

import hruler.portfolio.domain.Address;
import hruler.portfolio.domain.BaseEntity;
import hruler.portfolio.dto.CafeEditDto;
import hruler.portfolio.dto.CafeRegisterDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Persistable;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Slf4j
@Table(name = "cafes")
public class Cafe extends BaseEntity implements Persistable<Long> {

    @Id @GeneratedValue
    @Column(name = "cafe_id")
    private Long id;
    private String name;
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "cafe", cascade = CascadeType.ALL)
    private List<Menu> menus = new ArrayList<>();

    public Cafe(String name, Address address, String memberName) {
        this.name = name;
        this.address = address;
        this.setCreatedBy(memberName);
        this.setLastModifiedBy(memberName);
    }

    /**
     * Cafe Information Update
     * @param form
     */
    public void updateInfo(CafeEditDto form, String memberName) {
        this.name = form.getName();
        this.address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        this.setLastModifiedBy(memberName);
    }

    /**
     * Menu Add
     * @param menu
     */
    public void addMenu(Menu menu) {
        this.menus.add(menu);
    }

    @Override
    public boolean isNew() {
        return getCreatedDate() == null;
    }
}
