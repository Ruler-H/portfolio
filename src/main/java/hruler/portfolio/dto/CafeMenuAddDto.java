package hruler.portfolio.dto;

import hruler.portfolio.domain.cafe.Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class CafeMenuAddDto {

    private Long id;
    @NotBlank(message = "공백일 수 없습니다.")
    private String name;
    @NotNull(message = "가격을 입력해주세요.")
    @Range(min = 1000, max = 20000)
    private Integer price;
    @NotNull(message = "메뉴 종류를 입력해주세요.")
    private Type type;
}
