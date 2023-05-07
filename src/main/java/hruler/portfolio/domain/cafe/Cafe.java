package hruler.portfolio.domain.cafe;

import hruler.portfolio.domain.Address;
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

    public Cafe(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
