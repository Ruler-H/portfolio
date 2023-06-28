package hruler.portfolio.domain.cafe;

import hruler.portfolio.domain.BaseEntity;
import hruler.portfolio.dto.CafeMenuAddDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

@Entity
@Getter
@Table(name = "cafe_menu")
@NoArgsConstructor
public class Menu extends BaseEntity implements Persistable<Long> {

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

    public Menu(CafeMenuAddDto cafeMenuAddDto, Cafe cafe, String memberName) {
        this.name = cafeMenuAddDto.getName();
        this.price = cafeMenuAddDto.getPrice();
        this.type = cafeMenuAddDto.getType();
        this.cafe = cafe;
        this.setCreatedBy(memberName);
        this.setLastModifiedBy(memberName);
    }

    @Override
    public boolean isNew() {
        return getCreatedDate() == null;
    }
}
