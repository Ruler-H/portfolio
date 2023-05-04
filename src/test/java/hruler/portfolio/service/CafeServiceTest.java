package hruler.portfolio.service;

import hruler.portfolio.domain.Address;
import hruler.portfolio.domain.cafe.Cafe;
import hruler.portfolio.repository.CafeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CafeServiceTest {

    @Autowired
    CafeService cafeService;
    @Autowired
    CafeRepository cafeRepository;

    @Test
    public void 카페등록() throws Exception {
        //give
        Cafe cafe = new Cafe("cafeA", new Address("cityA", "streetA", "zipcodeA"));

        //when
        Long saveId = cafeService.registerCafe(cafe);

        //then
        assertEquals(cafe, cafeRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_카페_예외() throws Exception {
        //give
        Cafe cafe1 = new Cafe("cafeA", new Address("cityA", "streetA", "zipcodeA"));
        Cafe cafe2 = new Cafe("cafeA", new Address("cityA", "streetA", "zipcodeA"));

        //when
        cafeService.registerCafe(cafe1);
        cafeService.registerCafe(cafe2);

        //then
        fail("중복 카페 등록 예외 발생");
    }

}