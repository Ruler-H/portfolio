package hruler.portfolio.domain.cafe;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public abstract class Menu {

    @Id @GeneratedValue
    @Column(name = "menu_id")
    private Long id;
    private String name;
    private int price;
    @Enumerated(EnumType.STRING)
    private Type type;

}
