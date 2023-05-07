package hruler.portfolio.domain.cafe;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "cafe_menu")
public abstract class Menu {

    @Id
    @GeneratedValue
    @Column(name = "menu_id")
    private Long id;
    private String name;
    private int price;
    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;
}
