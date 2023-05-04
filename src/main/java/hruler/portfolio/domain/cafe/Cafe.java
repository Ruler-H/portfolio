package hruler.portfolio.domain.cafe;

import hruler.portfolio.domain.Address;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Cafe {

    @Id @GeneratedValue
    @Column(name = "cafe_id")
    private Long id;
    private String name;
    @Embedded
    private Address address;

    public Cafe(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
