package hruler.portfolio.repository;

import hruler.portfolio.domain.Member;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * Search Member By Name
     *
     * @param memberName
     * @return Member Entity as a Collection
     */
    List<Member> findByName(String memberName);

    public Optional<Member> findByLoginId(String loginId);
}
