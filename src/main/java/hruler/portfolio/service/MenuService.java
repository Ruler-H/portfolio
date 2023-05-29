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

    @Transactional
    public void registerMenu(Menu menu, Cafe cafe) {
        menuRepository.register(menu, cafe);

    }

    public Menu findOne(Long menuId) {
        return menuRepository.findOne(menuId);
    }

    public List<Menu> findMenus(){
        return menuRepository.findAll();
    }
}
