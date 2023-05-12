package hruler.portfolio.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    @Embedded
    private Address address;
    private String name;

    public Member(String name, Address address) {
        this.address = address;
        this.name = name;
    }
}