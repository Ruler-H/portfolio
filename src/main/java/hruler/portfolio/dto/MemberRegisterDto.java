package hruler.portfolio.dto;

import hruler.portfolio.domain.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class MemberRegisterDto {

    private Long id;
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;

    @NotBlank
    private String name;

    @NotNull
    private String city;
    @NotNull
    private String street;
    @NotNull
    private String zipcode;

    public static MemberRegisterDto convert(Member member) {
        MemberRegisterDto memberRegisterForm = new MemberRegisterDto();
        memberRegisterForm.setId(member.getId());
        memberRegisterForm.setLoginId(member.getLoginId());
        memberRegisterForm.setPassword(member.getPassword());
        memberRegisterForm.setName(member.getName());
        memberRegisterForm.setCity(member.getAddress().getCity());
        memberRegisterForm.setStreet(member.getAddress().getStreet());
        memberRegisterForm.setZipcode(member.getAddress().getZipcode());

        return memberRegisterForm;
    }
}
