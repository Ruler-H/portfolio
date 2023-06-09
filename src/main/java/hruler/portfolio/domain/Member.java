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
    private String loginId;
    private String password;
    @Embedded
    private Address address;
    private String name;

    public Member(String loginId, String password, String name, Address address) {
        this.loginId = loginId;
        this.password = password;
        this.address = address;
        this.name = name;
    }

    /**
     * Member Information Update
     * @param form
     */
    public void updateInfo(MemberRegisterDto form) {
        this.name = form.getName();
        this.address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
    }
}
