package hruler.portfolio.repository;

import hruler.portfolio.domain.cafe.Cafe;
import hruler.portfolio.domain.cafe.Menu;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MenuRepository {

    private final EntityManager em;

    /**
     * Register Menu
     * @param menu
     */
    public void register(Menu menu, Cafe cafe) {
        if (menu.getId() == null) {
            em.persist(menu);
        }else{
            em.merge(menu);
        }
        cafe.addMenu(menu);
    }

    /**
     * Search Menu By MenuId
     * @param id
     * @return Menu Entity
     */
    public Menu findOne(Long id) {
        return em.find(Menu.class, id);
    }

    /**
     * Search Total Menu
     * @return Menu Entity as a Collection
     */
    public List<Menu> findAll() {
        return em.createQuery("select m from Menu", Menu.class)
                .getResultList();
    }


}
