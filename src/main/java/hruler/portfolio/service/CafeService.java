package hruler.portfolio.service;

import hruler.portfolio.domain.Address;
import hruler.portfolio.domain.cafe.Cafe;
import hruler.portfolio.domain.cafe.Menu;
import hruler.portfolio.dto.*;
import hruler.portfolio.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CafeService {
    private final CafeRepository cafeRepository;

    /**
     * Save Cafe
     * @param cafeRegisterDto
     * @return Registered Cafe Id
     */
    @Transactional
    public Long registerCafe(CafeRegisterDto cafeRegisterDto, String memberName) {
        Cafe cafe = new Cafe(cafeRegisterDto.getName(), new Address(cafeRegisterDto.getCity(), cafeRegisterDto.getStreet(), cafeRegisterDto.getZipcode()), memberName);
        validateDuplicateCafe(cafe); // 중복 등록 검증
        cafeRepository.save(cafe);
        return cafe.getId();
    }

    private void validateDuplicateCafe(Cafe cafe) {
        List<Cafe> findCafes = cafeRepository.findByName(cafe.getName());
        if (!findCafes.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 카페입니다.");
        }
    }

    /**
     * Search Total Cafe
     * @return Total Cafe Entity as a Collection
     */
    public List<Cafe> findCafes() {
        return cafeRepository.findAll();
    }

    /**
     * Search Cafe By CafeId
     * @param cafeId
     * @return Searched Cafe Entity
     */
    public Cafe findOne(Long cafeId) { return cafeRepository.findById(cafeId).get(); }

    /**
     * Update Cafe Information
     * @param cafeId
     * @param form
     */
    @Transactional
    public void update(Long cafeId, CafeEditDto form, String memberName) {
        Cafe findCafe = findOne(cafeId);
        findCafe.updateInfo(form, memberName);
    }

    @Transactional
    public Cafe addMenu(Long cafeId, CafeMenuAddDto cafeMenuAddDto, String memberName) {
        Cafe findCafe = findOne(cafeId);
        Menu menu = new Menu(cafeMenuAddDto, findCafe, memberName);
        findCafe.addMenu(menu);
        return findCafe;
    }

    public List<Cafe> searchCafe(CafeSearchDto cafeSearchDto) {
        if (cafeSearchDto.getSearchStandard() == SearchStandard.NAME) {
            return cafeRepository.findListByName(cafeSearchDto.getSearchInfo());
        } else if (cafeSearchDto.getSearchStandard() == SearchStandard.ADDRESS) {
            return cafeRepository.findByAddress(cafeSearchDto.getSearchInfo());
        }
        return null;
    }
}
