package hruler.portfolio.service;

import hruler.portfolio.domain.cafe.Cafe;
import hruler.portfolio.domain.cafe.Menu;
import hruler.portfolio.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    /**
     * Register Menu
     * @param menu
     */
    @Transactional
    public void registerMenu(Menu menu) {
        menuRepository.save(menu);

    }

    /**
     * Search Menu By MenuId
     * @param menuId
     * @return Searched Menu Entity
     */
    public Menu findOne(Long menuId) {
        return menuRepository.findById(menuId).get();
    }

    /**
     * Search Total Menu
     * @return Searched Total Menu Entity as a Collection
     */
    public List<Menu> findMenus(){
        return menuRepository.findAll();
    }
}
