package hruler.portfolio.repository;

import hruler.portfolio.domain.cafe.Address;
import hruler.portfolio.domain.cafe.Cafe;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CafeRepositoryTest {
    @Autowired
    CafeRepository cafeRepository;

//    @Test
//    @Transactional
//    public void testCafe() {
//        Address address = new Address("city1", "street1", "zipcode1");
//        Cafe cafe = new Cafe("cafeA", address);
//
//        Long saveId = cafeRepository.save(cafe);
//
//        Cafe findCafe = cafeRepository.findOne(saveId);
//
//        assertThat(findCafe.getId()).isEqualTo(cafe.getId());
//        assertThat(findCafe.getName()).isEqualTo(cafe.getName());
//
//        assertThat(findCafe).isEqualTo(cafe);
//    }

}