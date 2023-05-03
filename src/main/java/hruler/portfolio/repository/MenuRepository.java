package hruler.portfolio.repository;

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
     * 메뉴 등록
     * @param menu
     */
    public void register(Menu menu) {
        if (menu.getId() == null) {
            em.persist(menu);
        }else{
            em.merge(menu);
        }
    }

    /**
     * 메뉴 검색
     * @param id
     * @return findMenu
     */
    public Menu findOne(Long id) {
        return em.find(Menu.class, id);
    }

    public List<Menu> findAll() {
        return em.createQuery("select m from Menu", Menu.class)
                .getResultList();
    }


}
