package hruler.portfolio.dto;

import hruler.portfolio.domain.cafe.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CafeMenuAddDto {

    private Long id;
    private String name;
    private int price;
    private Type type;
}
