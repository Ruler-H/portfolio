package hruler.portfolio.form;

import hruler.portfolio.domain.Member;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class MemberRegisterForm {

    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String name;

    private String city;
    private String street;
    private String zipcode;

    public static MemberRegisterForm convert(Member member) {
        MemberRegisterForm memberRegisterForm = new MemberRegisterForm();
        memberRegisterForm.setName(member.getName());
        memberRegisterForm.setCity(member.getAddress().getCity());
        memberRegisterForm.setStreet(member.getAddress().getStreet());
        memberRegisterForm.setZipcode(member.getAddress().getZipcode());

        return memberRegisterForm;
    }
}
