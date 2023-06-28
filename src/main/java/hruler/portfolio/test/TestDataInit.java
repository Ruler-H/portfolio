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

        cafeRepository.save(
                new Cafe(
                        "오브제커피", new Address("경기도 하남시", "미사강변중앙로", "164"),
                        "황병헌")
        );
        cafeRepository.save(
                new Cafe(
                        "피아커 커피", new Address("경기도 하남시", "미사강변중앙로", "165"),
                        "황병헌")
        );
        cafeRepository.save(
                new Cafe(
                        "카페 휴즈", new Address("경기도 고양시", "고골길", "228"),
                        "황병헌")
        );

        for (int i = 0; i < 100; i++) {
            cafeRepository.save(
                    new Cafe(
                            i + "번 카페", new Address("경기도 하남시", "미사강변중앙로", "164"),
                            "황병헌")
            );
        }
    }
}
