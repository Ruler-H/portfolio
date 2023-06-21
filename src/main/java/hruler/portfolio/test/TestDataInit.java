package hruler.portfolio.test;

import hruler.portfolio.domain.Address;
import hruler.portfolio.domain.Member;
import hruler.portfolio.domain.cafe.Cafe;
import hruler.portfolio.repository.CafeRepository;
import hruler.portfolio.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final CafeRepository cafeRepository;
    private final MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        memberRepository.save(new Member("test1", "test!", "황병헌", new Address()));
        memberRepository.save(new Member("test2", "test@", "손연수", new Address()));

        cafeRepository.save(new Cafe("오브제", new Address("하남", "미사강변동로", "72")));
        cafeRepository.save(new Cafe("스타벅스", new Address("하남", "미사강변동로", "72")));
    }
}
