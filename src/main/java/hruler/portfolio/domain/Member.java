package hruler.portfolio.domain;

import hruler.portfolio.dto.MemberRegisterDto;
import jakarta.persistence.*;
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

    public void updateInfo(MemberRegisterDto form) {
        this.name = form.getName();
        this.address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
    }
}
