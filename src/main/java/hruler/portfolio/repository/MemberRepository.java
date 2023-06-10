package hruler.portfolio.repository;

import hruler.portfolio.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    /**
     * Save Member
     * @param member
     */
    public void save(Member member) {
        em.persist(member);
    }

    /**
     * Search Member By MemberId
     * @param memberId
     * @return Member Entity
     */
    public Member findOne(Long memberId) {
        return em.find(Member.class, memberId);
    }

    /**
     * Search Total Member
     * @return Member Entity as a Collection
     */
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    /**
     * Search Member By Name
     * @param memberName
     * @return Member Entity as a Collection
     */
    public List<Member> findByName(String memberName) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", memberName)
                .getResultList();
    }

    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }
}
