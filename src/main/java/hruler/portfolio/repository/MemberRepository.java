package hruler.portfolio.repository;

import hruler.portfolio.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    /**
     * 회원 저장
     * @param member
     */
    public void save(Member member) {
        em.persist(member);
    }

    /**
     * 회원 조회 By 아이디
     * @param memberId
     * @return
     */
    public Member findOne(Long memberId) {
        return em.find(Member.class, memberId);
    }

    /**
     * 회원 전체 조회
     * @return
     */
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    /**
     * 회원 조회 By 이름
     * @param memberName
     * @return
     */
    public List<Member> findByName(String memberName) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", memberName)
                .getResultList();
    }
}
