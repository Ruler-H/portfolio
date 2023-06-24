package hruler.portfolio.domain;

import hruler.portfolio.dto.MemberRegisterDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseEntity implements Persistable<Long> {

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
        this.setCreatedBy(name);
        this.setLastModifiedBy(name);
    }

    /**
     * Member Information Update
     * @param form
     */
    public void updateInfo(MemberRegisterDto form, String memberName) {
        this.name = form.getName();
        this.address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        this.setLastModifiedBy(memberName);
    }

    @Override
    public boolean isNew() {
        return getCreatedDate() == null;
    }
}
