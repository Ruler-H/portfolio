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
     * @param cafe
     */
    @Transactional
    public void registerMenu(Menu menu, Cafe cafe) {
        menuRepository.register(menu, cafe);

    }

    /**
     * Search Menu By MenuId
     * @param menuId
     * @return Searched Menu Entity
     */
    public Menu findOne(Long menuId) {
        return menuRepository.findOne(menuId);
    }

    /**
     * Search Total Menu
     * @return Searched Total Menu Entity as a Collection
     */
    public List<Menu> findMenus(){
        return menuRepository.findAll();
    }
}
