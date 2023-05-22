package hruler.portfolio.service;

import hruler.portfolio.domain.cafe.Cafe;
import hruler.portfolio.dto.CafeRegisterDto;
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
     * 카페 등록
     */
    @Transactional
    public Long registerCafe(Cafe cafe) {

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
     * 전체 카페 조회
     */
    public List<Cafe> findCafes() {
        return cafeRepository.findAll();
    }

    public Cafe findOne(Long cafeId) {
        return cafeRepository.findOne(cafeId);
    }

    @Transactional
    public void update(Long cafeId, CafeRegisterDto form) {
//        log.info("form info = {}", form.getName());
        Cafe findCafe = findOne(cafeId);
        findCafe.updateInfo(form);
    }
}
